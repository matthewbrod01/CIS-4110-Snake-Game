package snakegameproject;

import java.awt.Color;
import java.awt.Graphics;

public class Food {
    private int x, y; // x and y positions of food will hold random coordinates
    private int score;
    private Snake snake; // to see x-y position of snake
    
    public Food(Snake s) {
        x = (int)(Math.random() * 492);
        y = (int)(Math.random() * 492);
        snake = s;
    }
    
    public void createNewFood() {
        // call this function when snake eats current food
        x = (int)(Math.random() * 492);
        y = (int)(Math.random() * 492);
        score++;
    }
    
    public int getScore() {
        return score;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, y, 8, 8);    
    }
    
    public boolean eatenBySnake() {
        int snakeHeadX = snake.getX() + 3; // center of snakeHeadX rect
        int snakeHeadY = snake.getY() + 3; // center of snakeHeadY rect
        
        // basically check if any portion of the snake hits any portion of the food
        if(snakeHeadX >= x && snakeHeadX <= (x + 8)) {
            if(snakeHeadY >= y && snakeHeadY <= (y + 8)) {
                createNewFood();
                snake.setGrow(true);
                return true;
            }
        }
        return false;
    }
}
