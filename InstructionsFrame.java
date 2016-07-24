package Game;

import Deck.Deck;

import java.awt.*;

public class InstructionsFrame {

    private Deck exampleDeck = new Deck('s');

    public void drawInstructionAnimations(Graphics g, Color c) {

        int xColumn = 200;
        int yStart = 300;
        int yGap = 25;
        Font font = new Font("Century Gothic", Font.ITALIC, 15);
        g.setFont(font);

        for (int i = 5; i > 0; i--) {
            exampleDeck.getCard(i).setCentre(xColumn, yStart - yGap * i);
        }


        g.drawString("Cards must be strategically placed under another column with its last card of the same suit and one higher value.", 100, 500);
        g.drawString("You may move any card that is face up, regardless of the arrangement of cards underneath it.", 100, 520);

        for (int i = 5; i > 0; i--) {
            exampleDeck.getCard(i).draw(g);
        }

        //draw an animated card moving to the left towards the pile (x = 200, y = 300)
        exampleDeck.getCard(0).setCentre(250, 400);
        int amountSteps = 25;
        int stepHorizontalIntegral = (xColumn - 250) / amountSteps;
        int stepVerticalIntegral = (yStart - 400) / amountSteps;

        for (int i = 0; i < amountSteps; i++) {
            exampleDeck.getCard(0).draw(g);
            exampleDeck.delay(100);
            exampleDeck.getCard(0).erase(g, c);
            exampleDeck.setxCentreCards(exampleDeck.getCard(0).getxCentre() + stepHorizontalIntegral);
            exampleDeck.setyCentreCards(exampleDeck.getCard(0).getyCentre() + stepVerticalIntegral);
        }
        exampleDeck.topDeck().draw(g);

        g.setFont(font);
        g.drawString("Empty decks may be filled with Kings, or groups headed by a King.", 100, 540);

        g.setColor(Color.black);
        g.drawRect(400, 125, exampleDeck.topDeck().getWidth(), exampleDeck.topDeck().getHeight());
        g.setColor(c);

        //Already used card 0-6
        amountSteps = 25;
        exampleDeck.getCard(12).setCentre(535, 300);
        exampleDeck.getCard(45).setCentre(535, 300 + yGap);
        exampleDeck.getCard(32).setCentre(535, 300 + yGap * 2);
        stepHorizontalIntegral = (435 - 535) / amountSteps;
        stepVerticalIntegral = (175 - 300) / amountSteps;

        for (int i = 0; i < amountSteps; i++) {
            exampleDeck.getCard(12).draw(g);
            exampleDeck.getCard(45).draw(g);
            exampleDeck.getCard(32).draw(g);
            exampleDeck.delay(100);
            exampleDeck.getCard(12).erase(g, c);
            exampleDeck.getCard(45).erase(g, c);
            exampleDeck.getCard(32).erase(g, c);
            exampleDeck.setxCentreCards(exampleDeck.getCard(12).getxCentre() + stepHorizontalIntegral);
            exampleDeck.getCard(12).setyCentre(exampleDeck.getCard(12).getyCentre() + stepVerticalIntegral);
            exampleDeck.getCard(45).setyCentre(exampleDeck.getCard(45).getyCentre() + stepVerticalIntegral);
            exampleDeck.getCard(32).setyCentre(exampleDeck.getCard(32).getyCentre() + stepVerticalIntegral);
        }

        exampleDeck.getCard(12).draw(g);
        exampleDeck.getCard(45).draw(g);
        exampleDeck.getCard(32).draw(g);

        g.setFont(font);
        g.drawString("The objective of this game is to complete four sequences of the same suit descending from King to Ace!", 100, 560);
        exampleDeck.setxCentreCards(600);

        for (int i = 0; i < 13; i++) {
            exampleDeck.getCard(i).setyCentre(175);
        }
        for (int i = 13; i < 26; i++) {
            exampleDeck.getCard(i).setyCentre(280);
        }

        for (int i = 26; i < 39; i++) {
            exampleDeck.getCard(i).setCentre(700, 175);
        }
        for (int i = 39; i < 52; i++) {
            exampleDeck.getCard(i).setCentre(700, 280);
        }

        for (int i = 0; i < 13; i++) {
            exampleDeck.getCard(i).draw(g);
            exampleDeck.getCard(i + 13).draw(g);
            exampleDeck.getCard(i + 26).draw(g);
            exampleDeck.getCard(i + 39).draw(g);
            exampleDeck.delay(200);
        }

        g.setFont(font);
        g.drawString("Tap to play instructions again!", 100, 580);
    }

}
