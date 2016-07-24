package Cards;

import java.awt.*;

/**
 * Created by SLi on 2016-05-27.
 */
public class Diamond extends Suit {

    public Diamond() {
        super();
        colour = Color.red;
    }

    public Diamond(int w, int h, int x, int y) {
        super(w, h, x, y);
        colour = Color.red;
    }

    public void draw(Graphics g) {
        int[] xPoints = new int[4];
        int[] yPoints = new int[4];

        xPoints[0] = xCentre - width / 2;
        yPoints[0] = yCentre;
        xPoints[1] = xCentre;
        yPoints[1] = yCentre - height / 2;
        xPoints[2] = xCentre + width / 2;
        yPoints[2] = yCentre;
        xPoints[3] = xCentre;
        yPoints[3] = yCentre + height / 2;

        g.setColor(colour);

        g.fillPolygon(xPoints, yPoints, 4);

    }
}
