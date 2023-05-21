package main;
import View.DeepSpaceView;
import deepspace.GameUniverse;
import View.GUI.MainWindow;
import controller.Controller;
/**
 *
 * @author moises
 */
public class PlayWithGUI {
    public static void main(String[] args) {
        GameUniverse game = new GameUniverse();
        DeepSpaceView view=MainWindow.getInstance();
        Controller controller = Controller.getInstance();
        controller.setModelView(game,view);
        controller.start();
        
    }
}
