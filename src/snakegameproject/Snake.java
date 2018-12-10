package snakegameproject;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    /* This class represents our Snake */
    
    List<Coordinates> snakeCoordinates; // to hold our snake coordinates
    int xDirection, yDirection; // this will be updated based on key presses in our main class
    boolean isMoving, grow;
    
    final int START_SIZE = 25; // starting size of snake
    final int START_X = 250, START_Y = 250; // starting positions
    
    public Snake() {
        snakeCoordinates = new ArrayList<Coordinates>();
        xDirection = 0;
        yDirection = 0;
        isMoving = false;
        grow = false;
        snakeCoordinates.add(new Coordinates(START_X, START_Y)); // starting Coordinates
        for(int i = 1; i < START_SIZE; ++i) {
            snakeCoordinates.add(new Coordinates(START_X - i * 5, START_Y)); // move snake left by 5 pixels
        } 
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.white);
        for(Coordinates c: snakeCoordinates) {
            g.fillRect(c.getX(), c.getY(), 5, 5); // snake is composed of units that are 5x5
            
        }
    }
    
    public int getXDirection() {
        return xDirection;
    }
    
    public int getYDirection() {
        return yDirection;
    }
    
    public void setXDirection(int x) {
        xDirection = x;
    }
    
    public void setYDirection(int y) {
        yDirection = y;
    }
    
    public boolean isMoving() {
        return isMoving;
    }
    
    public void setIsMoving(boolean i) {
        isMoving = i;
    }
    
    public void setGrow(boolean i) {
        grow = i;
    }
    
    public int getX() {
        /* 
        front of snake is always at element 0 of our list snakeCoordinates.
        returns x position of head of snake
        */
        return snakeCoordinates.get(0).getX();
    }
    public int getY() {
        // returns y position of head of snake
        return snakeCoordinates.get(0).getY();
    }
    
    public void moveSnake() {
        /* 
        Moves snake
        1) get current coordinates
        2) move snake coordinates
        3) time-delay for human-eye to process
        */
        if(isMoving) {
            Coordinates temp = snakeCoordinates.get(0);
            Coordinates last = snakeCoordinates.get(snakeCoordinates.size() - 1);

            Coordinates newHead = new Coordinates(temp.getX() + xDirection * 5, temp.getY() + yDirection * 5);

            // at this point we have to update every other snake unit coordinates too
            for(int i = snakeCoordinates.size() - 1; i >= 1; --i) {
                // running backwards, each snakeCoordinate takes the coordinates of the snakeCoordinate before it
                snakeCoordinates.set(i, snakeCoordinates.get(i - 1));
            }
            snakeCoordinates.set(0, newHead);
            if(grow) {
                for(int i = 0; i < 3; ++i) {
                    // grow by 4 * units
                    snakeCoordinates.add(last);
                }
                grow = false;
            }
        }  
    }
    
    public boolean ateItself() {
        // Check if snake's head == any of snake's other coordinates
        int headX = this.getX();
        int headY = this.getY();
        
        for(int i = 1; i < snakeCoordinates.size(); ++i) {
            if(snakeCoordinates.get(i).getX() == headX && snakeCoordinates.get(i).getY() == headY) {
                return true;
            }
        }
        return false;
    }
}
