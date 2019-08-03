package unsw.dungeon;

import java.io.IOException;

import javafx.application.Platform;
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
    
    @FXML
    private Button exitButton;
    
    private DungeonApplication dungeonScreen;

    @FXML
    public void handleStartButton(ActionEvent event) throws IOException {;
    	dungeonScreen.start();
    }
    
    @FXML
    public void handleExitButton(ActionEvent event) throws IOException {;
	    Stage stage = (Stage) exitButton.getScene().getWindow();
	    stage.close();
	    Platform.exit();
	    System.exit(0);
    }    
    
    public void setDungeonScreen(DungeonApplication dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

}
