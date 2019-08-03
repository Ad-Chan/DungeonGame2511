package unsw.dungeon;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class StartApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        StartScreen startScreen = new StartScreen(primaryStage);
        Stage newStage = new Stage();
        DungeonApplication dungeonScreen = new DungeonApplication(newStage);

        startScreen.start();
        startScreen.controller.setDungeonScreen(dungeonScreen);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
