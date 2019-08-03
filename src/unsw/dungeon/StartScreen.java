package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreen {//extends Application{
	   public Stage stage;
	   public String title;
	   public StartController controller;
	   public Scene scene;
	   
       public StartScreen(Stage stage) throws IOException {
		   this.stage = stage;
	       this.title = "Main Menu";
			//primaryStage.setTitle("Main Menu");
	       controller = new StartController();
	       FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
	       loader.setController(controller);
	
	       // load into a Parent node called root
	       Parent root = loader.load();
	       scene = new Scene(root);
	       //stage.setScene(scene);
	       //stage.show();
       }	

	/*@Override
	public void start(Stage primaryStage) throws IOException {
        this.stage = primaryStage;
        this.title = "Main Menu";
		//primaryStage.setTitle("Main Menu");
        controller = new StartController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/
	   
	public void start() {
	    stage.setTitle(title);
	    stage.setScene(scene);
	    stage.show();
	}


	public StartController getController() {
        return controller;
    }
}
