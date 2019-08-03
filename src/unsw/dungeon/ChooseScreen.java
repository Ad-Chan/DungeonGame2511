package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChooseScreen {
	   public Stage stage;
	   public String title;
	   public ChooseController controller;
	   public Scene scene;
	   
       public ChooseScreen(Stage stage) throws IOException {
		   this.stage = stage;
	       this.title = "Choose Your Level";
	       controller = new ChooseController();
	       FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseLevel.fxml"));
	       loader.setController(controller);
	       Parent root = loader.load();
	       scene = new Scene(root);
       }	
	   
	public void start() {
	    stage.setTitle(title);
	    stage.setScene(scene);
	    stage.show();
	}


	public ChooseController getController() {
        return controller;
    }
}
