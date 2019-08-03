package unsw.dungeon;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    @FXML
    private Button startButton;
    
    private DungeonApplication dungeonScreen;

    @FXML
    public void handleStartButton(ActionEvent event) throws IOException {;
    	dungeonScreen.start();
    }
    
    public void setDungeonScreen(DungeonApplication dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

}
