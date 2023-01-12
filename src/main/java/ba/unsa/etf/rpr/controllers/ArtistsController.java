package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ArtistsManager;
import ba.unsa.etf.rpr.business.ExhibitionManager;
import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.domain.Exhibitions;
import ba.unsa.etf.rpr.exceptions.DBException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;

public class ArtistsController {


        private final ArtistsManager artistManager = new ArtistsManager();

        @FXML


        public ListView<Artists> artistsList = new ListView<>();

        public TextField artistName = new TextField();


        //public ObservableList<Artists> observableList = FXCollections.observableArrayList();

        @FXML
        void initialize() {
            //try catch?
            //try{
            refreshArtists();
            artistsList.getSelectionModel().selectedItemProperty().addListener((obs, Old, New) -> {
                if (New != null) {
                    artistName.setText(New.getName());
                }
            });

        //} catch (DBException e) {
            //            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
            //        }

        }
        //2 listenera?

    public void addArtist(ActionEvent event){
        try {
            Artists a = new Artists();
            a.setName(artistName.getText());
            a = artistManager.add(a);
            artistsList.getItems().add(a);
            artistName.setText("");
            refreshArtists();
        }
        catch (DBException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void updateArtist(ActionEvent event){
        try {
            Artists a = artistsList.getSelectionModel().getSelectedItem();
            a.setName(artistName.getText());
            a = artistManager.update(a);
            refreshArtists();
        }
        catch (DBException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void deleteArtist(ActionEvent event){
        try {
            Artists a = artistsList.getSelectionModel().getSelectedItem();
            ArtistsManager.delete(a.getId());
            refreshArtists();
            artistsList.getItems().remove(a); // performance optimization
            //refresh?
        }
        catch (DBException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }


        private void refreshArtists(){
            try {
                artistsList.setItems(FXCollections.observableList(artistManager.getAll()));
                artistName.setText("");
            } catch (DBException e) {
                new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
            }
        }
    }




