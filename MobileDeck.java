package Game;

import Deck.Deck;

import Cards.Card;

import java.awt.*;

public class MobileDeck {

    Deck mobileDeck = new Deck();

    public int getSizeMobileDeck (){
        return mobileDeck.getSize();
    }

    public void transferCards (Deck d, int index){
        int sizeDeck = d.getSize();
        for (int i = index; i < sizeDeck; i++){
            mobileDeck.addCard(d.dealCard(index));
        }
    }

    public void transferCardsBack (CardColumn c){
        int amountToTransfer = mobileDeck.getSize();
        for (int i = 0; i < amountToTransfer; i++){
            c.addCardsToColumn(dealCardFromMobileDeck());
        }
    }

    public Card dealCardFromMobileDeck (){
        return mobileDeck.dealCard(0);
    }

    public Card getCardFromMobileDeck (int index){
        return mobileDeck.getCard(index);
    }

    public void relocateCards (int x, int y, int yGap){
        for (int i = 0; i < getSizeMobileDeck(); i++){
            mobileDeck.getCard(i).setCentre(x, y + yGap*i);
        }
    }

    public void drawMobileDeck (Graphics g){
        for (int i = 0; i < getSizeMobileDeck(); i++){
            mobileDeck.getCard(i).draw(g);
        }
    }

}
