package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Hellobutton {
    public Label labelid;
    public Button Helloid;

    public void Hello(ActionEvent actionEvent) {
        labelid.setText("Hello!");
    }
}
