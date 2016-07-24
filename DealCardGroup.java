package Game;

import Deck.Deck;

import java.awt.*;

import Cards.Card;

public class DealCardGroup {

    private Deck deckThree = new Deck();
    private int y = 150;
    private int[] x = new int[]{100, 120, 140};

    public DealCardGroup(Deck d) {

        for (int i = 0; i < 3; i++) {
            deckThree.addCard(d.dealCard(0), i);
            deckThree.getCard(i).setxCentre(x[i]);
        }
        deckThree.setyCentreCards(150);
    }

    public void fillDealCardGroup(Deck d) {
        for (int i = 0; i < 3; i++) {
            deckThree.addCard(d.dealCard(0), i);
            deckThree.getCard(i).setxCentre(x[i]);
        }
        deckThree.setyCentreCards(150);
    }

    public boolean isGroupEmpty() {
        return deckThree.deckEmpty();
    }

    public Card getCardDealCardGroup(int index) {
        return deckThree.getCard(index);
    }

    public Card dealDealCardGroup() {
        return deckThree.dealCard(0);
    }

    public boolean isPointInsideDealCardGroup(int x, int y) {

        if (!isGroupEmpty()) {
            int xLowBounds = 100 - deckThree.getCard(0).getWidth() / 2;
            int xHighBounds = 140 + deckThree.getCard(0).getWidth() / 2;
            int yLowBounds = 150 - deckThree.getCard(0).getHeight() / 2;
            int yHighBounds = 150 + deckThree.getCard(0).getHeight() / 2;

            if (x >= xLowBounds && x <= xHighBounds && y >= yLowBounds && y <= yHighBounds) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public void disperseCardGroup(CardColumn[] c, TextField t) {
        if (!isGroupEmpty()) {

            for (int i = 0; i < 3; i++) {
                getCardDealCardGroup(i).setFront(true);
            }

            for (int i = 0; i < 3; i++) {
                c[i].returnDeck().addCard(dealDealCardGroup());
                c[i].modifyYCentreLastCard(c[i].getYGap());
                c[i].returnDeck().lastCard().setCentre(c[i].returnDeck().topDeck().getxCentre(), c[i].getYCentreLastCard());
            }
        }

        t.setText("There are no more cards to deal!");
    }


    public void drawDealCardGroup(Graphics g) { //draw function ...
        if (!deckThree.deckEmpty()) {
            for (int i = 0; i < 3; i++) {
                deckThree.getCard(i).setFront(false);
                deckThree.getCard(i).draw(g);
            }
        }
    }
}

