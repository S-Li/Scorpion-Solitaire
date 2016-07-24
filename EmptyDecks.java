package Game;

import Deck.Deck;

import java.awt.*;

public class EmptyDecks {

    private int h, w;
    private int x = 1200;
    private int[] y = new int[]{150, 260, 370, 480};
    private Deck[] emptyDecks = new Deck[4];
    private int fullDeckCount = 0;

    public EmptyDecks(int h, int w) {

        for (int i = 0; i < 4; i++) { //initialize four empty decks
            emptyDecks[i] = new Deck();
        }

        this.h = h;
        this.w = w;
    }

    public Deck returnDeck(int index) {
        return emptyDecks[index];
    }

    public void fillEmptyDeck(CardColumn c) {
        //take the last 13 cards of the card column and put it in the card deck, LIFO manner
        for (int i = 0; i < 13; i++) {
            emptyDecks[fullDeckCount].addCard(c.dealCardColumn(c.getSizeColumn() - 1));
        }
        if (emptyDecks[fullDeckCount].getSize() == 13){
            fullDeckCount ++;
        }
    }

    public boolean checkForVictory (){
        if (fullDeckCount == 4){
            return true;
        }
        else{
            return false;
        }
    }

    public void drawEmptyDecks(Graphics g) {
        if (fullDeckCount == 0) {
            g.setColor(Color.black);
            for (int i = 0; i < 4; i++) {
                g.drawRect(x - w / 2, y[i] - h / 2, w, h);
            }
        } else {
            for (int i = 0; i < fullDeckCount; i++) {
                emptyDecks[i].lastCard().setCentre(1200, y[i]);
                emptyDecks[i].lastCard().draw(g);
            }
            for (int i = fullDeckCount; i < 4; i++){
                g.drawRect(x - w / 2, y[i] - h / 2, w, h);
            }
        }
    }
}


