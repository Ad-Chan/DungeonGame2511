package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreen {
	   public Stage stage;
	   public String title;
	   public StartController controller;
	   public Scene scene;
	   
       public StartScreen(Stage stage) throws IOException {
		   this.stage = stage;
	       this.title = "Main Menu";
	       controller = new StartController();
	       FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
	       loader.setController(controller);
	       Parent root = loader.load();
	       scene = new Scene(root);
       }	
	   
	public void start() {
	    stage.setTitle(title);
	    stage.setScene(scene);
	    stage.show();
	}


	public StartController getController() {
        return controller;
    }
}
