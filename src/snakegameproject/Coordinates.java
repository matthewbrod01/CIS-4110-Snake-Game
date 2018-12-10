package snakegameproject;

public class Coordinates {
    /* This class represents our coordinates on screen */
    
    private int x, y;
    
    public Coordinates() {
        x = 0;
        y = 0;
    }
    
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}

