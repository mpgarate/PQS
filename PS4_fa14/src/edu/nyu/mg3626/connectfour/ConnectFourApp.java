package edu.nyu.mg3626.connectfour;

import edu.nyu.mg3626.connectfour.model.ConnectFourGame;
import edu.nyu.mg3626.connectfour.model.ConnectFourModel;
import edu.nyu.mg3626.connectfour.view.GameSwingGui;

public class ConnectFourApp {
  private static final ConnectFourApp INSTANCE = new ConnectFourApp();

  private ConnectFourApp() {
  }

  public static ConnectFourApp getInstance() {
    return INSTANCE;
  }

  private void go() {
    ConnectFourModel model = new ConnectFourGame();
    GameSwingGui view = new GameSwingGui(model);
  }

  public static void main(String[] args) {
    ConnectFourApp.getInstance().go();
  }
}
