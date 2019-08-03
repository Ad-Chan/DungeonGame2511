package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreen extends Application{

    private StartController controller;

	@Override
	public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Main Menu");
        controller = new StartController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	public static void main(String[] args) {
		launch(args);
	}

    public StartController getController() {
        return controller;
    }
}
