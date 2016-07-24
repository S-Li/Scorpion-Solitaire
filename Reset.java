package Game;

import Deck.Deck;

public class Reset {

    public void resetAllCards(CardColumn[] cardColumns, DealCardGroup deckOfThree, EmptyDecks emptyDecks) {

        Deck tempDeck = new Deck();
        int sizeColumn;

        //transfer all the cards from each card column to the temporary deck
        for (int k = 0; k < 7; k++) {
            sizeColumn = cardColumns[k].getSizeColumn();
            for (int a = 0; a < sizeColumn; a++) {
                if (!cardColumns[k].returnDeck().deckEmpty()) {
                    tempDeck.addCard(cardColumns[k].dealCardColumn());
                }
            }
        }

        //get the cards from the deck of three if it is not empty
        if (!deckOfThree.isGroupEmpty()) {
            for (int i = 0; i < 3; i++) {
                if (deckOfThree.getCardDealCardGroup(0) != null) {
                    tempDeck.addCard(deckOfThree.dealDealCardGroup());
                }
            }
        }

        //get the cards from the decks at the side "empty decks"
        for (int i = 0; i < 4; i++) {
            if (!emptyDecks.returnDeck(i).deckEmpty()) {
                for (int j = 0; j < 13; j++)
                    tempDeck.addCard(emptyDecks.returnDeck(i).dealCard(0));
            }
        }

        //////// Add all the cards in temp deck back into the card columns and the deck of three

        if (tempDeck.getSize() == 52) {
            tempDeck.shuffleDeck();
            tempDeck.setAllFronts(true);
            tempDeck.setxCentreCards(0);
            tempDeck.setyCentreCards(0);

            deckOfThree.fillDealCardGroup(tempDeck);

            for (int i = 0; i < 7; i++) {
                if (i < 4) {
                    cardColumns[i].fillCardColumn(tempDeck, 300 + 80 * i, 'v');
                } else if (i > 3) {
                    cardColumns[i].fillCardColumn(tempDeck, 300 + 80 * i, 'n');
                }
            }
        }

    }
}
