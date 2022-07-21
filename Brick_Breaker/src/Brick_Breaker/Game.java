package Brick_Breaker;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Game extends JPanel implements KeyListener,ActionListener{
   //genika to Timer ine enas etimos tipos tis java kai mia metavliti aftou tou tipou xrisimopiite gia na pirodotei gegonota
    private Timer timer;//orizoume mia metavliti timer tipou Timer
    
    private Bricks theBricks;//metavliti theBricks tipou Bricks
    
    //domitis gia na valo tis parapano times otan dimiourgithi adikimeno apo aftin tin klasi
    public Game(){
        theBricks=new Bricks(5,6); // dimiourgi grammes kai stiles gia ta bricks
        addKeyListener(this);//gia na anixnevei pliktra
        setFocusable(true);//to parathiro tha pairnei focus
        timer = new Timer(7,this); //vazoume ena timer gia na pirodoti gegonota ana 7 delay 
        timer.start();
    }
    
    private boolean play = false; //gia na min ksekinai mono tou to pexnidi
    
    private int platformΧ=300;//arxiki thesi tis platformas
    
    private Color ballColor=Color.WHITE;//arxiko xroma balas
    
    //ta epomena 2 gia tin arxiki thesi tis balas        
    private int ballX=340; 
    private int ballY=530;
    
    //ta epomena 2 gia tin kateuthisni tis balas
    private int ballVelocityX=1;
    private int ballVelocityY=2;
    
    private int score=0; //arxiko score
    
    private int totalBricks=30;//sinolika bricks
    
    //dimiourgo 3 metavlites typou Color gia kathena apo 3 border tou paixnidiou. Arxiko xroma prasino
    private Color rightColor=Color.GREEN;
    private Color topColor=Color.GREEN;
    private Color leftColor=Color.GREEN;

    //i parakato methodos ine gia ton sxediasmo adikimenon (bala,platforma klp)
    @Override
    public void paint(Graphics graph){
        //backgroung xroma mavro kai diastaseis  
        graph.setColor(Color.black);
        graph.fillRect(1,1,1000,650);//(x,y,width,height)
        
        //kaleitai methodos draw gia ta bricks
        theBricks.draw((Graphics2D)graph);
        
        //xroma thesi diastaseis platformas
        graph.setColor(Color.green);
        graph.fillRect(platformΧ,550,100,15);
     
        //xroma thesi diastaseis balas
        graph.setColor(ballColor);
        graph.fillOval(ballX, ballY, 20, 20);

        Graphics2D graph2 = (Graphics2D) graph;
        //gia na prosthesoume stroke kanoume metabliti graph2 typou Graphics2D
        
        //perigramata paixnidiou xroma prasino
        graph.setColor(Color.GREEN);
        graph2.setStroke(new BasicStroke(10));//paxos giro apo tis grammes kai rectangles pou the dimiourgithoun
        graph.drawLine(700,100,1000,100);//grammi ksekinaei simeio (x1,y1)teleinonei se (x2,y2)
        graph.drawLine(700,200,1000,200);
        graph.drawRect(700,6,289,610);
        
        //perigrama me xromata
        graph.setColor(rightColor);
        graph.drawLine(700,6,700,616);
        
        graph.setColor(topColor);
        graph.drawLine(6,6,690,6);
        
        graph.setColor(leftColor);
        graph.drawLine(6,6,6,616);

        //score - remaining bricks
        graph.setColor(Color.white);
        graph.setFont(new Font("Courier",Font.BOLD, 25));
        graph.drawString("Score:"+score, 800,65);
        graph.drawString("Remaining Bricks:"+totalBricks, 723,160);
        
        //created by
        graph.setColor(new Color(179, 0, 0));
        graph.setFont(new Font("Courier",Font.BOLD, 25));
        graph.drawString("Created by: ", 780, 250);
        graph.drawString("Karachristos Georgios", 710, 300);
        graph.drawString("Tsantilis Vasileios", 740,350);
        graph.drawString("Fotopoulou Nikoletta", 726,400);

        //how to start
        if (!play && totalBricks >0 && ballY< 650){
            graph.setColor(Color.magenta);
            graph.setFont(new Font("Courier",Font.BOLD, 25));
            graph.drawString("Press Space to start!", 230,300); 
        }

        //Minima an xasis i kerdisis
        if(totalBricks <=0 || ballY> 650){ 
            play=false; //stamataei to paixnidi
            ballVelocityX=0; //stamataei na kineitai i bala
            ballVelocityY=0;
            graph.setColor(Color.red); 
            graph.setFont(new Font("Courier",Font.BOLD, 30)); 
            if (totalBricks <=0) //an exeis spasei ola ta bricks
                graph.drawString("Congratulations!!! Score:"+score, 165, 300);    
            if (ballY> 650) //an i bala pesei kato
                graph.drawString("Game Over! Score:"+score, 205, 300);
            graph.drawString("Press Enter to Restart", 205, 330);
        }
        graph.dispose(); //katastrefei to jframe    
    }
    
    int i,j,brickX,brickY,brickWidth,brickHeight;
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        //gia tin kinisi tis balas
        if(play){
            if( new Rectangle(ballX,ballY,20,20).intersects(new Rectangle(platformΧ,550,100,15))){ //an ta oria balas xtipisoun oria platformas
                //an i bala xtipisei plagia, na allaksei x, ara na pesei kato. Allios an xtipisei apo pano, na allaksei y
                if(ballY>535)
                    ballVelocityX= -ballVelocityX;
                else
                    ballVelocityY= -ballVelocityY;
            }    
            //oi parakato edoles ine gia na anixnevei i bala ta bricks
            LOOP:  
            for (i=0; i<theBricks.TheBricks.length; i++) {
                for (j = 0;j<theBricks.TheBricks[0].length;j++) {
                    if (theBricks.TheBricks[i][j]>0) {
                        //dimiourgoume ta oria gia kathe brick
                        brickX= j * theBricks.brickWidth + 80;
                        brickY= i * theBricks.brickHeight + 50;
                        brickWidth=theBricks.brickWidth;
                        brickHeight=theBricks.brickHeight;  
                        //pali dimiourgoume ena Rectangle apo giro
                        Rectangle brickRect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        //kai giro apo tin bala tora
                        Rectangle ballRect=new Rectangle(ballX,ballY,20,20);
                        if (ballRect.intersects(brickRect)) { //an ta oria tis balas xtipisoun ta oria ton bricks
                            theBricks.setBrickValue(0,i,j); // thetoume 0 oste na min to ksanazografisei i repaint
                            totalBricks--; //meionoume arithmo brick
                            score +=5; //enimeronoume to score
                            
                            if (i==j || i+2==j || i+4==j || i-2==j || i-4==j) //sinthiki gia na allazei xroma i bala
                                ballColor=Color.orange;
                            else
                                ballColor=Color.cyan;
                            
                            // afto ine gia ta dexia kai ta aristera meroi ton bricks
                            //ta x gekina einai i aristeri pleura tou sximatos. diametros ballas 20, ara to x einai to proto pixel kai meta +19 i ipolypi bala
                            //stin aristeri pleura tou || les an i dexia pleura tis balas akoubisei tin aristeri pleura tou brick
                            //stin dexia pleura tou || les an i aristeri pleura tis balas akoubisei tin dexia tou brick, pou einai to x tou brick+to platos tou
                            if (ballX + 19 <=brickRect.x || ballX+1 >=brickRect.x + brickRect.width) 
                                ballVelocityX = -ballVelocityX;
                            else  //an diladi xtipisei apo pano i kato to brick
                                ballVelocityY = -ballVelocityY;
                            break LOOP;//ama akoubisei 2 toubla mazi stamataei to loop stin mia allagi foras 
                        }
                    }
                }
            }
            //analoga me tin timi toy Velocity allazoun ta x,y tis balas
            ballX += ballVelocityX;
            ballY += ballVelocityY;
            
            //an akoubisei kapio border na pari tin aditheti poria apo aftin pou ixe
            //to proto if ine gia to aristero border  to deutero gia to pano to trito gia to dexi
            if(ballX <10){
                ballVelocityX = -ballVelocityX;
                //kodikas pou epanalambanetai alles 2 fores apo kato, an einai to ena xroma allakse to se allo
                if(leftColor==Color.green)
                    leftColor=Color.yellow;
                else if(leftColor==Color.yellow)
                    leftColor=Color.cyan;
                else if(leftColor==Color.cyan)
                    leftColor=Color.green;
                
            }
            if(ballY < 10){
                ballVelocityY = -ballVelocityY;
                if(topColor==Color.green)
                    topColor=Color.yellow;
                else if(topColor==Color.yellow)
                    topColor=Color.cyan;
                else if(topColor==Color.cyan)
                    topColor=Color.green;
            }
            if(ballX >675){
                ballVelocityX = -ballVelocityX;
                if(rightColor==Color.green)
                    rightColor=Color.yellow;
                else if(rightColor==Color.yellow)
                    rightColor=Color.cyan;
                else if(rightColor==Color.cyan)
                    rightColor=Color.green;
            }
        }
        repaint(); //afto tha ksanazigrafisi otidipote vriskete stin puclib void paint
    }
    
    //override apo KeyListen interface pou kaname implement
    //ta dio prota den tha xrisimopoiithoun alla an ta vgaloume tha petaksi error
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyReleased(KeyEvent e){}
    
    //gia tis kinisis tis platformas
    @Override
    public void keyPressed(KeyEvent e) { //methodos gia otan patithei pliktro
    //an patao to deksi velaki kai den exi figi i platforma ekso apo ta borders kounisou dexia
        if (e.getKeyCode()== KeyEvent.VK_RIGHT){
            if(platformΧ+20>595) //gia na einai sto oria
                platformΧ=595;
            else
                platformΧ+=20;
            if (!play && totalBricks >0) //etsi den tha kounietai kai i bala mazi an exei teleiosei to paixnidi
                ballX=platformΧ+40;
        }  //ta idia gia aristera tora
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(platformΧ-20<11)
                platformΧ=11;
            else
                platformΧ-=20;
            if (!play && totalBricks >0)
                ballX=platformΧ+40;   
        }
        //gia na ksekinisei i bala na kineitai
        if(e.getKeyCode()==KeyEvent.VK_SPACE ){
            play=true;
        }
        
        // gia na boris na kaneis retry patontas ENTER
        //ksanarxikopoioume tis metablites
        if(e.getKeyCode()==KeyEvent.VK_ENTER ){
            if(!play && totalBricks ==0 || ballY > 650){
                ballColor=Color.WHITE;
                ballX=340;
                ballY=530;
                ballVelocityX=1;
                ballVelocityY=2;
                platformΧ=300;
                score=0;
                totalBricks=30;
                theBricks= new Bricks(5,6);
                rightColor=Color.GREEN;
                topColor=Color.GREEN;
                leftColor=Color.GREEN;
                
                repaint(); //ksanazografizoume to paixnidi me tis apo pano metablites
            }
        }
    }
}