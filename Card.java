package Cards;

import java.awt.*;

/**
 * Created by SLi on 2016-06-08.
 */
public class Card extends Shape {

    boolean front = true;
    int value = 1;
    int suit = 1;

    public Card() {
        super();
        width = 70;
    }

    public Card(int s, int x, int y) {
        super(x, y);
        setSize(s);
    }

    public void setSize(int height) {
        this.height = height;
        width = (height / 10) * 7;
    }

    public void setFront(boolean front) {
        this.front = front;
    }

    public boolean getFront() {
        return front;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public int getSuit() {
        return suit;
    }

    public void draw(Graphics g) {

        Font font = new Font("Serif", Font.BOLD, height / 5);
        String[] Values = new String[13];

        for (int i = 1; i < 10; i++) {
            Values[i] = String.valueOf(i + 1);
        }

        Values[0] = String.valueOf('A');
        Values[10] = Character.toString('J');
        Values[11] = Character.toString('Q');
        Values[12] = Character.toString('K');

        if (front) {
            if (suit == 1) {
                Diamond d = new Diamond();
                d.setHeight(height / 4);
                d.setWidth(height / 4);
                d.setCentre(xCentre, yCentre);
                g.setColor(Color.white);
                g.fillRect(xCentre - (width / 2), yCentre - (height / 2), width, height);
                d.draw(g);
                g.setColor(d.getColour());

            } else if (suit == 2) {
                Heart h = new Heart();
                h.setHeight(height / 4);
                h.setWidth(height / 4);
                h.setCentre(xCentre, yCentre);
                g.setColor(Color.white);
                g.fillRect(xCentre - (width / 2), yCentre - (height / 2), width, height);
                h.draw(g);
                g.setColor(h.getColour());

            } else if (suit == 3) {
                Club c = new Club();
                c.setHeight(height / 4);
                c.setWidth(height / 4);
                c.setCentre(xCentre, yCentre);
                g.setColor(Color.white);
                g.fillRect(xCentre - (width / 2), yCentre - (height / 2), width, height);
                c.draw(g);
                g.setColor(c.getColour());

            } else if (suit == 4) {
                Spade s = new Spade();
                s.setHeight(height / 4);
                s.setWidth(height / 4);
                s.setCentre(xCentre, yCentre);
                g.setColor(Color.white);
                g.fillRect(xCentre - (width / 2), yCentre - (height / 2), width, height);
                s.draw(g);
                g.setColor(s.getColour());

            }

            g.setFont(font);
            g.drawString(Values[value], xCentre - (width / 3), yCentre - (height / 4));
            g.drawString(Values[value], xCentre + (width / 5), yCentre + (height / 3));

            g.setColor(colour); //colour of shape = black
            g.drawRect(xCentre - (width / 2), yCentre - (height / 2), width, height);

        } else {
            g.setColor(Color.cyan);
            g.fillRect(xCentre - (width / 2), yCentre - (height / 2), width, height);
            g.setColor(colour);
            g.drawRect(xCentre - (width / 2), yCentre - (height / 2), width, height);
        }
    }

    public void erase(Graphics g, Color C) {

        g.setColor(C);

        Font font = new Font("Serif", Font.BOLD, height / 5);
        String[] Values = new String[13];

        for (int i = 1; i < 10; i++) {
            Values[i] = String.valueOf(i + 1);
        }

        Values[0] = String.valueOf('A');
        Values[10] = Character.toString('J');
        Values[11] = Character.toString('Q');
        Values[12] = Character.toString('K');

        if (front) {
            if (suit == 1) {
                Diamond d = new Diamond();
                d.setColour(C);
                d.setHeight(height / 4);
                d.setWidth(height / 4);
                d.setCentre(xCentre, yCentre);
                g.fillRect(xCentre - (width / 2), yCentre - (height / 2), width, height);
                d.draw(g);

            } else if (suit == 2) {
                Heart h = new Heart();
                h.setColour(C);
                h.setHeight(height / 4);
                h.setWidth(height / 4);
                h.setCentre(xCentre, yCentre);
                g.fillRect(xCentre - (width / 2), yCentre - (height / 2), width, height);
                h.draw(g);

            } else if (suit == 3) {
                Club c = new Club();
                c.setColour(C);
                c.setHeight(height / 4);
                c.setWidth(height / 4);
                c.setCentre(xCentre, yCentre);
                g.fillRect(xCentre - (width / 2), yCentre - (height / 2), width, height);
                c.draw(g);

            } else if (suit == 4) {
                Spade s = new Spade();
                s.setColour(C);
                s.setHeight(height / 4);
                s.setWidth(height / 4);
                s.setCentre(xCentre, yCentre);
                g.fillRect(xCentre - (width / 2), yCentre - (height / 2), width, height);
                s.draw(g);

            }

            g.setFont(font);
            g.drawString(Values[value], xCentre - (width / 3), yCentre - (height / 4));
            g.drawString(Values[value], xCentre + (width / 5), yCentre + (height / 3));
            g.drawRect(xCentre - (width / 2), yCentre - (height / 2), width, height);

        } else {
            g.fillRect(xCentre - (width / 2), yCentre - (height / 2), width, height);
            g.drawRect(xCentre - (width / 2), yCentre - (height / 2), width, height);
        }
        g.setColor(colour);
    }
}

