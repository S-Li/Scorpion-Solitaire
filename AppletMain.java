package Game;

import java.applet.*;

import java.awt.*;

import java.awt.event.*;

import Deck.Deck;

/**
 * Created by SLi on 2016-06-10.
 */

public class AppletMain extends Applet implements ActionListener, MouseListener, MouseMotionListener {

    //Instance variables here/////////////////////////////////////////////////
    private Graphics g;
    private Color backColor = new Color(0, 115, 50);
    private Deck fullDeck;
    private MobileDeck mobileDeck;
    private EmptyDecks emptyDecks;
    private DealCardGroup dealCardGroup;
    private CardColumn[] cardColumns;
    private Reset resetGame;
    private InstructionsFrame instructionsFrame;
    private int disperseCardGroup = 0;
    private int numberMoves = 0;
    private int columnMouseIn = 0;
    private boolean OkToMove = false;
    private int columnMouseHover;
    private boolean instructionsAreHit = false;
    private boolean inInstructionScreen = false;
    private Image dbImage;
    private Graphics dbg;

    ///Buttons
    private Button buttonInstructions = new Button("Instructions");
    private Button buttonReset = new Button("Reset");
    private Button buttonPlay = new Button("Play");

    ///Texts Fields
    private TextField updateBar = new TextField(100);
    private TextField moveCount = new TextField(10);

    //Labels
    private Label moveCountLabel = new Label("Number of Moves: ");

    ///Layout
    private BorderLayout layout = new BorderLayout();
    private Panel topPanel = new Panel();
    private Panel bottomPanel = new Panel();
    private GridBagLayout buttonLayout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private GridBagLayout bottomSection = new GridBagLayout();

    //initialization method////////////////////////////////////////////////////
    public void init() {

        g = getGraphics();
        resetGame = new Reset();
        mobileDeck = new MobileDeck();
        instructionsFrame = new InstructionsFrame();
        fullDeck = new Deck('s');
        fullDeck.shuffleDeck();
        dealCardGroup = new DealCardGroup(fullDeck);
        emptyDecks = new EmptyDecks(fullDeck.topDeck().getHeight(), fullDeck.topDeck().getWidth());
        cardColumns = new CardColumn[7];

        for (int i = 0; i < 7; i++) {
            if (i < 4) {
                cardColumns[i] = new CardColumn(fullDeck, 300 + 80 * i, 'v');
            } else if (i > 3) {
                cardColumns[i] = new CardColumn(fullDeck, 300 + 80 * i, 'n');
            }
        }

        //establishment of main screen's layout
        setLayout(layout); //the applet layout is a border layout
        topPanel.setLayout(buttonLayout);
        bottomPanel.setLayout(bottomSection);

        //topPanel Layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonLayout.setConstraints(updateBar, gbc);
        topPanel.add(updateBar);

        gbc.gridx = 1;
        buttonLayout.setConstraints(buttonPlay, gbc);
        topPanel.add(buttonPlay);

        gbc.gridx = 2;
        buttonLayout.setConstraints(buttonInstructions, gbc);
        topPanel.add(buttonInstructions);

        gbc.gridx = 3;
        buttonLayout.setConstraints(buttonReset, gbc);
        topPanel.add(buttonReset);

        //Bottom Panel Layout
        bottomPanel.add(moveCountLabel);
        bottomPanel.add(moveCount);

        //Put all the components together on the Applet's border Layout
        add("North", topPanel);
        add("South", bottomPanel);
        //add("Center", centreGame);

        ///implementation of ActionListener
        buttonInstructions.addActionListener(this);
        buttonReset.addActionListener(this);
        buttonPlay.addActionListener(this);

        addMouseListener(this);
        addMouseMotionListener(this);

    }

    //methods///////////////////////////////////////////////////////////////////

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonInstructions) {
            updateBar.setText("Become a Master!");
            instructionsAreHit = true;
            inInstructionScreen = true;
        } else if (e.getSource() == buttonReset) {
            resetGame.resetAllCards(cardColumns, dealCardGroup, emptyDecks);
            updateBar.setText("Your game has been reset.");
            numberMoves = 0;
            moveCount.setText(String.valueOf(numberMoves));
            disperseCardGroup = 0;
            instructionsAreHit = false;
            inInstructionScreen = false;
        } else if (e.getSource() == buttonPlay) {
            updateBar.setText("Welcome to Scorpion Solitaire!");
            instructionsAreHit = false;
            inInstructionScreen = false;
        }
        repaint();
    }

    public void mouseClicked(MouseEvent e) {

        if (dealCardGroup.isPointInsideDealCardGroup(e.getX(), e.getY()) && disperseCardGroup == 0) {
            dealCardGroup.disperseCardGroup(cardColumns, updateBar);
            disperseCardGroup = 1;
            numberMoves += 1;
            moveCount.setText(String.valueOf(numberMoves));
        }
        repaint();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {

        for (int i = 0; i < 7; i++) {
            if (cardColumns[i].isMouseInColumn(e.getX(), e.getY())) {
                columnMouseIn = i;
                OkToMove = true;
                updateBar.setText("Card selected.");
                cardColumns[columnMouseIn].transferCardsToMobileDeck(mobileDeck, e.getY(), updateBar);

            }
        }
    }

    public void mouseReleased(MouseEvent e) { // no more drag, if it is released too far, cards return. If released on another deck, check.

        OkToMove = false;

        // check if the mouse is within the boundaries of any of the other decks.
        //if the mobile deck is occupied, and the mouse is in any of the columns' boundaries, and that column is not empty if the first
        //card of the mobile deck is not a king.

        if (mobileDeck.getSizeMobileDeck() != 0) {
            for (int i = 0; i < 7; i++) {
                if (cardColumns[i].isMouseInColumn(e.getX(), e.getY())) {
                    columnMouseHover = i;
                    break;
                }
            }
            if (cardColumns[columnMouseHover].checkMoveLegality(mobileDeck)) {
                // if it is legal, add the cards from this to the deck
                mobileDeck.transferCardsBack(cardColumns[columnMouseHover]);
                numberMoves++;
                moveCount.setText(String.valueOf(numberMoves));

                if (!cardColumns[columnMouseIn].returnDeck().deckEmpty() && !cardColumns[columnMouseIn].returnDeck().lastCard().getFront()) {
                    cardColumns[columnMouseIn].flipFlippedCard();
                }

                if (cardColumns[columnMouseHover].checkIfThereIsPileComplete()) {
                    //move these cards to the empty decks
                    emptyDecks.fillEmptyDeck(cardColumns[columnMouseHover]);
                }

                if (emptyDecks.checkForVictory()) {
                    updateBar.setText("HOORAAAYYY! YOU ARE VICTORIOUS!");
                }

            } else {
                mobileDeck.transferCardsBack(cardColumns[columnMouseIn]);
            }
        }

        if (!inInstructionScreen) {
            if (disperseCardGroup == 0) {
                updateBar.setText("Click on a card to drag it, or deal the three cards if you are stuck :(");
            } else {
                updateBar.setText("Click on a card to drag it. :)");
            }
        }

        repaint();
    }

    public void mouseDragged(MouseEvent e) {
        if (OkToMove) {
            mobileDeck.relocateCards(e.getX(), e.getY(), cardColumns[0].getYGap());
            if (mobileDeck.getSizeMobileDeck() != 0) {
                updateBar.setText("You may only place this card under another of the same suit and a higher denomination!");
            }
        }

        repaint();
    }

    public void mouseMoved(MouseEvent e) {
    }

    /*public void update(Graphics g) {
        Graphics offgc;
        Image offscreen = null;
        //Dimension d = size();

        // create the offscreen buffer and associated Graphics
        offscreen = createImage(getWidth(), getHeight());
        offgc = offscreen.getGraphics();
        // clear the exposed area
        offgc.setColor(getBackground());
        offgc.fillRect(0, 0, getWidth(), getHeight());
        offgc.setColor(getForeground());
        // do normal redraw
        paint(offgc);
        // transfer offscreen to window
        g.drawImage(offscreen, 0, 0, this);
    }*/

    public void paint(Graphics g) {

        if (!instructionsAreHit) {

            setBackground(backColor);

            if (disperseCardGroup == 1) {                                              //group of three cards are used
                disperseCardGroup = 2;
                dealCardGroup.drawDealCardGroup(g);
                emptyDecks.drawEmptyDecks(g);
                for (int i = 0; i < 7; i++) {
                    cardColumns[i].drawCardColumn(g);
                }

            } else {                                                    // disperseCardGroup != 1 (cannot disperse again)
                dealCardGroup.drawDealCardGroup(g);
                emptyDecks.drawEmptyDecks(g);
                for (int i = 0; i < 7; i++) {
                    cardColumns[i].drawCardColumn(g);
                }
                mobileDeck.drawMobileDeck(g);
            }
        } else {

            instructionsFrame.drawInstructionAnimations(g, backColor);
        }
    }

}