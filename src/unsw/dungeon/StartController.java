package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private Button startButton;
    
    @FXML
    private Button chooseLevelButton;
    
    @FXML
    private Button exitButton;
    
    @FXML
    private ChoiceBox<String> levelChoice;
    
    private DungeonApplication dungeonScreen;
    
    private String playLevel;

    @FXML
    public void handleStartButton(ActionEvent event) throws IOException {;
	    if (playLevel != null) {	
	    	Stage stage = new Stage();
	    	dungeonScreen = new DungeonApplication(stage, playLevel);
	    	dungeonScreen.start();
	    } else {
	    	JOptionPane.showMessageDialog(null, "Please select a level!");
	    }
    }
    
    @FXML
    public void handleChooseLevel(ActionEvent event) throws IOException {;
		this.playLevel = (String) levelChoice.getValue();
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
    
    public void setPlayLevel(String s) {
    	this.playLevel = s;
    }
    
    public void populateChoiceBox(ArrayList<String> dungeons) {
    	for (String s: dungeons) {
    		levelChoice.getItems().add(s);
    	}
    }

}
