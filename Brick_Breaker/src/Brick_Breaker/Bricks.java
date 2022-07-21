package Brick_Breaker;

import java.awt.*;

class Bricks {
    private Color brickColor;//metabliti gia ta xromata ton bricks
    public int TheBricks[][]; //aftos o pinakas ine gia ola ta bricks
    public int brickWidth; //dilonoume tin metavliti tou platous ton touvlon
    public int brickHeight; //dilonoume tin metavliti tou ipsous ton touvlon
    int i,j;
    public Bricks(int row,int col){//enas constructor gia na paroume poses grames kai stiles prepi na dimiourgithoune
        TheBricks=new int[row][col]; //dilono ton pinaka me tis diastasis tou 
        
        for(i=0;i<TheBricks.length; i++){ //ena for gia na afksanei ton metriti i tou pinaka map kata 1
            for(j=0; j < TheBricks[0].length; j++ ){ //ena for gia na afksanei ton metriti j tou pinaka map kata 1
                TheBricks[i][j]=1;//kanoume tin timi olon ton bricks 1 
                //an i timi enos sigekrimenou brick ine 1 tote den exi erthi se epafi me tin bala
                // otan spaei ena brick tha kanoume na allazei i timi tou brick se 0
            }
        }
        //eisago stixia gia ta bricks me arxikopoiisi
        brickWidth = 540/col; //platos
        brickHeight= 150/row; //ipsos
    }
    public void draw(Graphics2D graph) { //zografizei bricks mono eki pou i timi ton bricks ine 1 ara den akoubise i bala
        for(i=0;i<TheBricks.length; i++){ //ena for gia na afksanei ton metriti i tou pinaka TheBricks kata 1
            for(j=0; j < TheBricks[0].length; j++ ){ //ena for gia na afksanei ton metriti j tou pinaka TheBricks kata 1
                
                if(i==j || i+2==j || i+4==j || i-2==j || i-4==j)//sinthiki gia omoiomorfa xromata bricks
                    brickColor=Color.orange;
                else
                    brickColor=Color.cyan;
                
                if (TheBricks[i][j]>0){ //an i timi tous ine pano apo 0 diladi 1 
                    graph.setColor(brickColor);//xroma toublou analoga me tin pano sinthiki
                    graph.fillRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);//o typos einai:(x,y,width,height)
                    //gia to x:to rect dimiourgitai sti thesi: brick platos 540/6=90 kai to sbroxno 80 pixels dexia, oses fores einai to j+1
                    //gia to y:to rect dimiourgitai sti thesi: brick ipsos 150/5=30 kai to sproxno 50 pixels kato, oses fores einai to i+1
                    //width, height tou rect: 90 kai 50
                    //ara to proto brick dimiourgeitai stin thesi (x,y)(0*90+80,0*30+50)me (width,height)(90,30)
                    //Το j*brickWidth+80 sbroxnei 80 pixels dexia kai to i*brickHeight+50 ta sbroxnei 50 pixels kato
                    //an den valoume tis parakato edoles ta bricks tha emfanizontai os ena megalo brick
                    //aftes tis edoles vazoume grames gia na xorisoume ta bricks to xromma tha ine mavro
                    graph.setStroke(new BasicStroke(4)); //gia to poso paxes tha ine oi grammes anamesa sta bricks
                    graph.setColor(Color.black); //to xroma ton grammon
                    graph.drawRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
                }
            }
        }
    }
    //o parakato kodikas ine gia ta pragmata pou ginontai otan vriskei i bala ta bricks 
    public void setBrickValue(int value,int row,int col){ 
        TheBricks[row][col] = value; 
        //an akoubisi bala brick alazoume tin timi enos brick kathe fora se 0 ara opos exoume pei kai pio pano den ksanazografizete
    }
}