# Brick Breaker

A classic Brick Breaker arcade game implemented in Java using Swing. This project was originally developed as part of the Human Machine Interaction course and was later refactored to improve organization, maintainability, and object-oriented design.

## Features

* Classic paddle-ball-brick gameplay
* Score tracking system
* Win / lose game states
* Color-changing boundaries for visual feedback
* Collision detection with:
    * Paddle
    * Bricks
    * Game boundaries
* Restart functionality
* Simple keyboard controls

## Controls

| Key    | Action |
| -------- | ------- |
| Left Arrow  | Move paddle left    |
| Right Arrow | Move paddle right    |
| Space  | Start game    |
| Enter  | Restart after win/lose    |

## Game States

The game runs through 4 main states:

* **READY** → Ball is attached to paddle, waiting to start.
* **PLAYING** → Active gameplay.
* **WON** → All bricks have been destroyed.
* **LOST** → The ball falls below the paddle.

## Architecture Overview

The project follows a simple layered structure:

* ui/
    * Game (JPanel - rendering + input handling)

* engine/
    * GameEngine (core game loop logic)
    * CollisionManager (collision handling)

* entities/
    * Ball
    * Paddle
    * Brick
    * Bricks (brick collection + score)
    * Boundary

* util/
    * GameConstants (central configuration)

* enums/
    * GameStatus (game state management)

## Refactoring Notes

This project was refactored from a single-class procedural design into a modular OOP structure.

**Before refactor:**
* All logic inside one Game class
* Manual collision and rendering mixed together
* Hard to maintain and extend

**After refactor:**
* Separation of concerns (UI / Engine / Entities)
* Centralized configuration via constants
* Dedicated collision management
* Clear game state handling