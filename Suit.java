package Cards;

import java.awt.Graphics;

/**
 * Created by SLi on 2016-05-27.
 */
public abstract class Suit extends Shape {

    public Suit() {
        super();
    }

    public Suit(int w, int h, int x, int y) {
        super(w, h, x, y);
    }

    public void setWidth (int height){
        width = (height/5) * 4;
    }

    public abstract void draw(Graphics g);

}
