package Cards;

import java.awt.*;

/**
 * Created by SLi on 2016-05-27.
 */

public abstract class Shape {

    protected int xCentre, yCentre = 100;
    protected Color colour = Color.black;
    protected int height, width = 100;

    public Shape() {
    } // empty constructor

    public Shape (int x, int y){
        xCentre = x;
        yCentre = y;
    }

    public Shape(int w, int h, int x, int y) { // specific constructor
        this(x,y);
        width = w;
        height = h;
    }

    public void delay(int delayTime) {
        long finalTime = System.currentTimeMillis() + delayTime;
        do {
        }
        while (finalTime >= System.currentTimeMillis());
    }

    public int getxCentre() {
        return xCentre;
    }

    public void setxCentre(int xCentre) {
        this.xCentre = xCentre;
    }

    public int getyCentre() {
        return yCentre;
    }

    public void setyCentre(int yCentre) {
        this.yCentre = yCentre;
    }

    public void setCentre (int x, int y){
        xCentre = x;
        yCentre = y;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public abstract void draw(Graphics g);

    public void erase(Graphics g, Color c) {
        Color oldColour = colour;
        colour = c;
        draw(g);
        colour = oldColour;
    }
}
