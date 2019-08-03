package unsw.dungeon;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;

public class StartApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        StartScreen startScreen = new StartScreen(primaryStage);
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
		for (String s: levels) {
			System.out.println(s);
		}
        startScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
