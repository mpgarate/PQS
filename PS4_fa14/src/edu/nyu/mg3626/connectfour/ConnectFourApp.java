package edu.nyu.mg3626.connectfour;

import edu.nyu.mg3626.connectfour.model.ConnectFourGame;
import edu.nyu.mg3626.connectfour.model.ConnectFourModel;
import edu.nyu.mg3626.connectfour.view.GameSwingGui;

/**
 * Run the connect four game with a Swing GUI.
 * 
 * @author mg3626
 *
 */
public class ConnectFourApp {
  private static final ConnectFourApp INSTANCE = new ConnectFourApp();

  private ConnectFourApp() {
  }

  /**
   * Get the singleton instance of the ConnectFourApp
   * 
   * @return the instance
   */
  private static ConnectFourApp getInstance() {
    return INSTANCE;
  }

  private void go() {
    ConnectFourModel model = new ConnectFourGame();
    GameSwingGui view = new GameSwingGui(model);
  }

  /**
   * The primary execution.
   * 
   * @param args
   *          array of command line arguments
   */
  public static void main(String[] args) {
    ConnectFourApp.getInstance().go();
  }
}
