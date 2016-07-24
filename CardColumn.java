package Game;

import Deck.Deck;

import java.awt.*;

import Cards.Card;

public class CardColumn {

    private Deck deckColumn = new Deck();
    private int x; //this never changes
    private int[] y = new int[52];
    private int yStart = 150;
    private int emptyWidth, emptyHeight;
    private int yGap = 25;
    private int yCentreLastCard = yStart;

    public CardColumn(Deck d, int x, char s) {

        if (s == 'n') {
            for (int i = 0; i < 7; i++) {
                deckColumn.addCard(d.dealCard(0), i);
                y[i] = yStart + yGap * i;
                deckColumn.getCard(i).setyCentre(y[i]);
            }

        } else if (s == 'v') {
            for (int i = 0; i < 7; i++) {
                deckColumn.addCard(d.dealCard(0), i);
                if (i < 3) {
                    deckColumn.getCard(i).setFront(false);
                }
                y[i] = yStart + yGap * i;
                deckColumn.getCard(i).setyCentre(y[i]);
            }
        }

        deckColumn.setxCentreCards(x);
        this.x = x;
        yCentreLastCard += yGap * (getSizeColumn() - 1);
        emptyWidth = deckColumn.getCard(0).getWidth();
        emptyHeight = deckColumn.getCard(0).getHeight();
    }

    public void fillCardColumn(Deck d, int x, char s) {

        yCentreLastCard = yStart;

        if (s == 'n') {
            for (int i = 0; i < 7; i++) {
                deckColumn.addCard(d.dealCard(0), i);
                y[i] = yStart + yGap * i;
                deckColumn.getCard(i).setyCentre(y[i]);
            }

        } else if (s == 'v') {
            for (int i = 0; i < 7; i++) {
                deckColumn.addCard(d.dealCard(0), i);
                if (i < 3) {
                    deckColumn.getCard(i).setFront(false);
                }
                y[i] = yStart + yGap * i;
                deckColumn.getCard(i).setyCentre(y[i]);
            }
        }

        deckColumn.setxCentreCards(x);
        this.x = x;
        yCentreLastCard += yGap * (getSizeColumn() - 1);

    }

    public int getYCentreLastCard() {
        return yCentreLastCard;
    }


    public void modifyYCentreLastCard(int a) {
        yCentreLastCard += a;
    }

    public void recalculateYCentreLastCard() {
        yCentreLastCard = yStart + yGap * (getSizeColumn() - 1);
    }

    public void addCardsToColumn(Card c) {
        returnDeck().addCard(c);
        recalculateYCentreLastCard();
        returnDeck().lastCard().setCentre(x, yCentreLastCard);
    }

    public int getYGap() {
        return yGap;
    }

    public Deck returnDeck() {
        return deckColumn;
    }

    public int getSizeColumn() {
        return deckColumn.getSize();
    }

    public Card dealCardColumn() {
        return deckColumn.dealCard(0);
    }

    public Card dealCardColumn(int index) {
        return deckColumn.dealCard(index);
    }

    public boolean isMouseInColumn(int x, int y) {
        if (!deckColumn.deckEmpty()) {
            int xLowBounds = this.x - deckColumn.getCard(0).getWidth() / 2;
            int xHighBounds = this.x + deckColumn.getCard(0).getWidth() / 2;
            int yLowBounds = deckColumn.topDeck().getyCentre() - deckColumn.getCard(0).getHeight() / 2;
            int yHighBounds = deckColumn.lastCard().getyCentre() + deckColumn.getCard(0).getHeight() / 2;

            if (x >= xLowBounds && x <= xHighBounds && y >= yLowBounds && y <= yHighBounds) {
                return true;
            } else {
                return false;
            }

        } else {
            if (x >= this.x - emptyWidth / 2 && x <= this.x + emptyWidth / 2 && y >= yStart - emptyHeight / 2 && y <= yStart + emptyHeight / 2) {
                return true;
            } else {
                return false;
            }
        }
    }


    private int identifyWhichCardSelected(int y) {
        int yHighBounds = yCentreLastCard - emptyHeight / 2;
        int indexCardToTransfer = getSizeColumn() - 1;

        for (int i = 0; i < getSizeColumn() + 1; i++) {
            if (y < yHighBounds) {
                indexCardToTransfer -= 1;
                yHighBounds -= 25;
            } else {
                break;
            }
        }

        return indexCardToTransfer;
    }

    public void transferCardsToMobileDeck(MobileDeck d, int y, TextField t) {
        int index = identifyWhichCardSelected(y);

        if (deckColumn.getCard(index).getFront()) {
            d.transferCards(deckColumn, index);
            recalculateYCentreLastCard();
        } else {
            t.setText("You cannot move a flipped card!");
        }
    }

    public boolean checkMoveLegality(MobileDeck m) {
        if (!deckColumn.deckEmpty() && deckColumn.lastCard().getSuit() == m.getCardFromMobileDeck(0).getSuit() && deckColumn.lastCard().getValue() - 1 == m.getCardFromMobileDeck(0).getValue()) {
            return true;
        } else if (deckColumn.deckEmpty() && m.getCardFromMobileDeck(0).getValue() == 12) {
            return true;
        } else {
            return false;
        }

    }

    public void flipFlippedCard() {
        if (!deckColumn.lastCard().getFront()) {
            deckColumn.lastCard().setFront(true);
        }
    }

    public boolean checkIfThereIsPileComplete() {
        boolean temp = false;
        int sizeDeck = deckColumn.getSize();

        if (deckColumn.lastCard().getValue() != 0 || sizeDeck < 13) {
            return false;
        } else {
            for (int i = 0; i < 13; i++) {
                if (deckColumn.getCard(sizeDeck - (1+i)).getValue() == i) {
                    temp = true;
                } else {
                    temp = false;
                }

            }
        }
        return temp;
    }

    public void drawCardColumn(Graphics g) {
        if (!returnDeck().deckEmpty()) {
            for (int i = 0; i < deckColumn.getSize(); i++) {
                if (deckColumn.getCard(i) != null) {
                    deckColumn.getCard(i).draw(g);
                }
            }
        } else {
            g.drawRect(x - emptyWidth / 2, yStart - emptyHeight / 2, emptyWidth, emptyHeight);
        }
    }

}
