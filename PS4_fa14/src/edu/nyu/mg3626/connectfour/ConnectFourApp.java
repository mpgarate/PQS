package edu.nyu.mg3626.connectfour;

import edu.nyu.mg3626.connectfour.model.ConnectFourGame;
import edu.nyu.mg3626.connectfour.model.ConnectFourModel;
import edu.nyu.mg3626.connectfour.view.ConnectFourSwingGui;

public class ConnectFourApp {
  private void go() {
    ConnectFourModel model = new ConnectFourGame();
    ConnectFourSwingGui view = new ConnectFourSwingGui(model);
  }

  public static void main(String[] args) {
    new ConnectFourApp().go();
  }
}
