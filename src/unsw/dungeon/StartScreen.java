package unsw.dungeon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreen {
	   private Stage stage;
	   private String title;
	   private StartController controller;
	   private Scene scene;
	   
       public StartScreen(Stage stage) throws IOException {
		   this.stage = stage;
	       this.title = "Main Menu";
	       controller = new StartController();
	       FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
	       loader.setController(controller);
	       Parent root = loader.load();
	       scene = new Scene(root);
	        ArrayList<String> levels = new ArrayList<String>();
			File folder = new File("./dungeons/");
			File[] listOfFiles = folder.listFiles();			
			for (File file : listOfFiles) {
			    if (file.isFile()) {
			        if (file.getName().contains(".json")) {
				    	levels.add(file.getName());
			        }
			    }
			}
	       controller.setLevels(levels);
	       controller.populateChoiceBox(levels);
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
