package Cards;

import java.awt.*;

/**
 * Created by SLi on 2016-05-27.
 */
public class Heart extends Suit {

    public Heart() {
        super();
        colour = Color.red;
    }

    public Heart(int w, int h, int x, int y) {
        super(w, h, x, y);
        colour = Color.red;
    }

    public void draw(Graphics g) {
        int xPoints[] = new int[5];
        int yPoints[] = new int[5];

        xPoints[0] = xCentre - width / 2;
        yPoints[0] = yCentre;
        xPoints[1] = xCentre + width / 2;
        yPoints[1] = yCentre;
        xPoints[2] = xCentre;
        yPoints[2] = yCentre + height / 2;
        xPoints[3] = xCentre - width / 2;
        yPoints[3] = yCentre - height / 4;
        xPoints[4] = xCentre;
        yPoints[4] = yCentre - height / 4;

        g.setColor(colour);

        g.fillArc(xPoints[3], yPoints[3], width / 2, height / 2, 0, 180);
        g.fillArc(xPoints[4], yPoints[4], width / 2, height / 2, 0, 180);
        g.fillPolygon(xPoints, yPoints, 3);

    }
}
