package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private Button startButton;
    
    private DungeonApplication dungeonScreen;

    @FXML
    public void handleStartButton(ActionEvent event) throws IOException {
    	Stage stage = new Stage();
    	dungeonScreen.start(stage);
    }
    
    public void setCountScreen(DungeonApplication dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

}
