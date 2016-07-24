package Cards;

import java.awt.*;

/**
 * Created by SLi on 2016-05-27.
 */
public class Club extends Suit {

    public Club() {
        super();
        colour = Color.black;
    }

    public Club(int w, int h, int x, int y) {
        super(w, h, x, y);
        colour = Color.black;
    }

    public void draw(Graphics g) {
        int xPoints[] = new int[5];
        int yPoints[] = new int[5];

        xPoints[0] = xCentre - width / 2;
        yPoints[0] = yCentre;
        xPoints[1] = xCentre + width / 2;
        yPoints[1] = yCentre;
        xPoints[2] = xCentre;
        yPoints[2] = yCentre - height / 2;
        xPoints[3] = xCentre - width / 2;
        yPoints[3] = yCentre - height / 4;
        xPoints[4] = xCentre;
        yPoints[4] = yCentre - height / 4;

        int triPointsX[] = new int[3];
        int triPointsY[] = new int[3];

        triPointsX[0] = xCentre;
        triPointsY[0] = yCentre - height / 4;
        triPointsX[1] = xCentre - width / 8;
        triPointsY[1] = yCentre + height / 2;
        triPointsX[2] = xCentre + width / 8;
        triPointsY[2] = yCentre + height / 2;

        g.setColor(colour);

        g.fillOval(xPoints[3], yPoints[3], width / 2, height / 2);
        g.fillOval(xPoints[4], yPoints[4], width / 2, height / 2);
        g.fillOval(xCentre - width / 4, yCentre - 4 * (height / 7), width / 2, height / 2);
        g.fillPolygon(triPointsX, triPointsY, 3);
    }
}
