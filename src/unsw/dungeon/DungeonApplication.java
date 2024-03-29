package unsw.dungeon;

import java.io.IOException;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class DungeonApplication {

	   private Stage stage;
	   private String title;
	   private Scene scene;	
	
    public DungeonApplication(Stage primaryStage, String Level) throws IOException {
    	this.stage = primaryStage;
    	this.title = ("Dungeon");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(Level);
        DungeonController controller = dungeonLoader.loadController();
	    controller.setDungeonScreen(stage);
        EntityController eController = new EntityController(dungeonLoader, controller);
        controller.getDungeon().setEntityController(eController);       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
   	
	}

	public void start() {
	    stage.setTitle(title);
	    stage.setScene(scene);
	    stage.show();
    }
	

}
