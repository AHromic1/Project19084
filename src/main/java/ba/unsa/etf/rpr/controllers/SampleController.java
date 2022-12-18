package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SampleController {


    public Button OKId;
    public TextField UsernameId;

    @FXML
    public void initialize() {

    }

    public void OKAction(ActionEvent actionEvent) {
        UsernameId.setText("Hello");
    }
}
