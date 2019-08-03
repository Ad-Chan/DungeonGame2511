package unsw.dungeon;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ChooseController {

    @FXML
    private Button chooseButton;
    
    @FXML
    private ChoiceBox levelChoice;
    
    private ChooseScreen chooseScreen;

    @FXML
    public void handleChooseButton(ActionEvent event) throws IOException {;

    }
        
    public void setChooseScreen(ChooseScreen chooseScreen) {
        this.chooseScreen = chooseScreen;
    }

}