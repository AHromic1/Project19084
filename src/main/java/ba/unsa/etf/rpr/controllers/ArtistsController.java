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

        public ArtistsController() {
            try {
                observableList.addAll(artistManager.getAll());
                System.out.println(observableList);
            } catch (DBException e) {
                e.printStackTrace();
                System.out.println("Something went wrong with getAll() method from artistsManager!!!");
            }
        }

        @FXML
        void initialize(){
            refreshArtists();
            artistsList.getSelectionModel().selectedItemProperty().addListener((obs, Old, New)->{
                if (New != null){
                    artistName.setText(New.getLast_name());
                }
            });
            catch (DBException e) {
                throw new RuntimeException(e);
            }

        }

    public void addArtist(ActionEvent event){
        try {
            Artists a = new Artists();
            a.setFirst_name(addArtist.getText());
            a = artistManager.add(a);
            categoriesList.getItems().add(a);
            addArtist.setText("");
            refreshArtists();
        }
        catch (DBException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void updateArtist(ActionEvent event){
        try {
            Artists cat = categoriesList.getSelectionModel().getSelectedItem();
            cat.setName(categoryName.getText());
            cat = manager.update(cat);
            refreshArtists();
        }catch (DBException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void deleteArtist(ActionEvent event){
        try {
            Category cat = categoriesList.getSelectionModel().getSelectedItem();
            manager.delete(cat.getId());
            //refreshCategories();
            categoriesList.getItems().remove(cat); // perf optimization
        }catch (QuoteException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }




        private void refreshArtists(){
            try {
                artistsTable.setItems(FXCollections.observableList(artistManager.getAll()));
                artistsTable.refresh();
            } catch (DBException e) {
                new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
            }
        }
    }




