import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import view.MainView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
      MainView mainView = new MainView();
      Group root = mainView.getView();

      Scene scene = new Scene(root, MainView.WIDTH, MainView.HEIGHT);
      scene.setFill(Color.LAVENDER);
      primaryStage.setTitle("A* visualizer");
      primaryStage.setScene(scene);
      primaryStage.show();
    }

    public static void main(String[] args) {
      launch(args);
    }
}
