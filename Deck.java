package Deck;

import Cards.Card;

import java.util.Vector;

public class Deck {

    private Vector<Card> deck = new Vector<>(52, 0); // initial capacity, increment

    public void delay(int delayTime) {
        long finalTime = System.currentTimeMillis() + delayTime;
        do {
        }
        while (finalTime >= System.currentTimeMillis());
    }

    public Deck() {
    }

    public Deck(char deckType) {
        if (deckType == 's') {
            for (int j = 1; j < 5; j++) {
                for (int i = 0; i < 13; i++) {
                    Card c = new Card(100, 100, 100);
                    c.setSuit(j);
                    c.setValue(i);
                    deck.add(c);
                }
            }
        }
    }

    public boolean deckEmpty() {
        return deck.isEmpty();
    }

    public void setyCentreCards(int y) {
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i) != null) {
                Card temp = deck.get(i);
                temp.setyCentre(y);
            }
        }
    }

    public void setxCentreCards(int x) {
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i) != null) {
                Card temp = deck.get(i);
                temp.setxCentre(x);
            }
        }
    }

    public void setAllFronts(boolean b) {
        for (int i = 0; i < getSize(); i++) {
            getCard(i).setFront(b);
        }
    }

    public int getSize() {
        return deck.size();
    }

    public void addCard(Card cardToAdd) {
        deck.insertElementAt(cardToAdd, deck.size());
    }

    public void addCard(Card cardToAdd, int Pos) {
        if (Pos == 0 || deck.size() == 0) { // beginning of deck
            deck.addElement(cardToAdd);
        } else if (Pos > deck.size()) {
            deck.insertElementAt(cardToAdd, deck.size()); // end of deck
        } else {
            deck.insertElementAt(cardToAdd, Pos); // within the deck
        }
    }

    public Card dealCard(int Pos) {
        Card temp;
        temp = (deck.get(Pos));
        deck.removeElementAt(Pos);
        return temp;
    }

    public void removeCard(int Pos) {
        deck.remove(Pos);
    }

    public Card topDeck() {
        return deck.firstElement();
    }

    public Card lastCard() {
        return deck.lastElement();
    }

    public Card getCard(int index) {
        return (deck.get(index));
    }

    public void shuffleDeck() {
        int randomPos;
        for (int i = 0; i < deck.size(); i++) {
            Card temp = dealCard(i);
            randomPos = (int) (Math.random() * deck.size());
            deck.insertElementAt(temp, randomPos);
        }
    }
}
