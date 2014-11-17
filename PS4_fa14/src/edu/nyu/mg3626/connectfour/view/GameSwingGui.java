package edu.nyu.mg3626.connectfour.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.nyu.mg3626.connectfour.IllegalMoveException;
import edu.nyu.mg3626.connectfour.model.ConnectFourModel;
import edu.nyu.mg3626.connectfour.model.Piece;
import edu.nyu.mg3626.connectfour.player.ComputerPlayer;
import edu.nyu.mg3626.connectfour.player.HumanPlayer;
import edu.nyu.mg3626.connectfour.player.Player;

public class GameSwingGui implements ConnectFourListener {

  private JFrame frame = new JFrame();
  private ConnectFourModel model;
  private JButton[][] pieceButtonMatrix;
  private JPanel piecesPanel;

  private int numberOfRows;
  private int numberOfColumns;

  private ComputerPlayer computerPlayer;

  public GameSwingGui(ConnectFourModel model) {
    this.model = model;
    model.addConnectFourListener(this);

    numberOfRows = model.getNumberOfRows();
    numberOfColumns = model.getNumberOfColumns();
    pieceButtonMatrix = new JButton[numberOfRows][numberOfColumns];

    piecesPanel = new JPanel(new GridLayout(numberOfRows, numberOfColumns));

    initializePiecesPanel();

    frame.getContentPane().add(piecesPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 600);
    frame.setVisible(true);

    promptForNewGameOrExit("Welcome. What would you like to do?");
  }

  private void newGameVersusHuman() {
    Player player1 = new HumanPlayer("Red", Color.RED);
    Player player2 = new HumanPlayer("Yellow", Color.YELLOW);

    computerPlayer = null;

    model.startNewGame(player1, player2);
  }

  // TODO: randomize whether human or computer starts
  private void newGameVersusComputer() {
    Player humanPlayer = new HumanPlayer("Red", Color.RED);

    computerPlayer = new ComputerPlayer("Yellow", Color.YELLOW);

    model.startNewGame(humanPlayer, computerPlayer);
  }

  private void promptForNewGameOrExit(String message) {
    Object[] options =
        { "Play against human", "Play against computer", "Quit" };

    int selection =
        JOptionPane.showOptionDialog(frame, message, "Connect Four",
            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, options, options[0]);

    switch (selection) {
    case -1:
      System.exit(0);
      break;
    case 0:
      newGameVersusHuman();
      break;
    case 1:
      newGameVersusComputer();
      break;
    case 2:
      System.exit(0);
      break;
    }
  }

  private void initializePiecesPanel() {
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
        JButton button = createGridButton(colIndex);
        button.setBackground(Color.GRAY);
        piecesPanel.add(button);

        pieceButtonMatrix[rowIndex][colIndex] = button;
      }
    }
  }

  private void emptyPiecesPanel() {
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
        pieceButtonMatrix[rowIndex][colIndex].setBackground(Color.GRAY);
      }
    }
  }

  private JButton createGridButton(final int columnIndex) {
    JButton button = new JButton();

    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        addPiece(columnIndex);
      };
    });

    return button;
  }

  private void triggerComputerPlayerMove() {
    int nextMoveColumn = computerPlayer.getNextMove(model);

    if (-1 == nextMoveColumn) {
      throw new IllegalStateException(
          "No legal move available for computerPlayer.");
    }

    addPiece(nextMoveColumn);
  }

  private void addPiece(int columnIndex) {
    try {
      model.addPiece(columnIndex);
    } catch (IllegalMoveException e) {
      JOptionPane.showMessageDialog(frame, e.getMessage());
    }
  }

  private void displayPiece(Piece piece) {
    // invert row index for intuitive visual output. In the model, the bottom
    // row is index 0. See Piece.getRowIndex();
    int rowIndex = (numberOfRows - 1) - piece.getRowIndex();
    int columnIndex = piece.getColumnIndex();
    Color pieceColor = piece.getPlayer().getColor();

    pieceButtonMatrix[rowIndex][columnIndex].setBackground(pieceColor);
  }

  @Override
  public void gameStarted() {
    emptyPiecesPanel();
  }

  @Override
  public void pieceAdded(Piece piece) {
    displayPiece(piece);

    if (null != computerPlayer && !piece.getPlayer().equals(computerPlayer)
        && !model.gameIsOver()) {
      triggerComputerPlayerMove();
    }
  }

  @Override
  public void gameWon(Piece piece) {
    displayPiece(piece);
    promptForNewGameOrExit("Game won by " + piece.getPlayer() + ".");
  }

  @Override
  public void gameTied(Piece piece) {
    displayPiece(piece);
    promptForNewGameOrExit("Game is over with no winners. It's a tie!");
  }

}
