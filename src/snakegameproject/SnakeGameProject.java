package snakegameproject;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SnakeGameProject extends Applet implements Runnable, KeyListener {
    /* Class can control size of applet as well as background */

    Graphics gfx; 
    Image img;
    Thread thread;
    boolean isRunning;
    Snake snake;
    Food food;
    
    @Override
    public void init() {
        this.resize(500, 500);
        img = createImage(500, 500);
        gfx = img.getGraphics();
        isRunning = true;
        snake = new Snake();
        food = new Food(snake);
        this.addKeyListener(this);
        thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void paint(Graphics g) {
        /* 
        off-screen: gfx
        on-screen: g
        */
        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, 500, 500);
        
        if(isRunning) {
            snake.draw(gfx);
            food.draw(gfx);
        } else {
            /******************* case when game is over *********************/
            gfx.setColor(Color.blue);
            Font f = new Font("Monospaced", Font.PLAIN, 20);
            gfx.setFont(f);
            gfx.drawString("Game over", 190, 100);
            gfx.drawString("Score: " + food.getScore(), 190, 120);
        }
        g.drawImage(img, 0, 0, null);
    }
    
    public void repaint(Graphics g) {
        // this method is needed to call paint because in our game loop,
        // we cannot call paint directly
        paint(g);
    }

    @Override
    public void run() {
        while(true) { // infinite-loop until game ends
            
            snake.moveSnake();
            this.repaint();
            this.isRunning();
            food.eatenBySnake();
            
            try {
                Thread.sleep(30); // set time delay, lower = faster
            } catch (InterruptedException ex) {
                //ex.printStackTrace();
                Logger.getLogger(SnakeGameProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void isRunning() {
        /* 
        We END the game when the snake goes
            1) out of bounds
            2) hits itself
        */
        
        // Case 1, out of bounds
        // for some reason, out of bounds is 4 less than size (500 - 4)
        if(snake.getX() < 0 || snake.getX() > 496) {
            isRunning = false;
        }
        if(snake.getY() < 0 || snake.getY() > 496) {
            isRunning = false;
        }
        
        // Case 2, snake ate itself
        if(snake.ateItself()) {
            isRunning = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // minimum method needed for KeyListener extension
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /* 
        Snake controls for up, down, left, right
        We have to update snake.xDirection and snake.yDirection accordingly
        -1 represents going left or up
        1 represents going right or down
        */
        
        if(!snake.isMoving()) { // if not yet moving, set to move
            // our snake is initially facing left
            // we only want our snake to move if key press is up, down, or right
            if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode()== KeyEvent.VK_DOWN ||
                    e.getKeyCode()== KeyEvent.VK_RIGHT) {
                snake.setIsMoving(true);
            }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if(snake.getYDirection() != 1) { // if going down, can't go up
                snake.setYDirection(-1);
                snake.setXDirection(0); // set to no direction. prevent snake from going diagonally
            }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(snake.getYDirection() != -1) { // if going up, can't go down
                snake.setYDirection(1);
                snake.setXDirection(0); // set to no direction. prevent snake from going diagonally
            }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(snake.getXDirection() != 1) { // if going right, can't go left
                snake.setXDirection(-1);
                snake.setYDirection(0); // set to no direction. prevent snake from going diagonally
            }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(snake.getXDirection() != -1) { // if going down, can't go up
                snake.setXDirection(1);
                snake.setYDirection(0); // set to no direction. prevent snake from going diagonally
            }
        }   
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // minimum method needed for KeyListener extension
    }
}
