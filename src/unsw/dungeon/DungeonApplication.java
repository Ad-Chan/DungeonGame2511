package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DungeonApplication {

	   public Stage stage;
	   public String title;
	   public Scene scene;	
	   public DungeonController controller;
	
    public DungeonApplication(Stage primaryStage) throws IOException {
    	this.stage = primaryStage;
    	this.title = ("Dungeon");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("advanced.json");
        DungeonController controller = dungeonLoader.loadController();
        EntityController eController = new EntityController(dungeonLoader, controller);
        controller.getDungeon().setEntityController(eController);       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();    	
	}

	public void start() {
	    stage.setTitle(title);
	    stage.setScene(scene);
	    stage.show();
    }

}
