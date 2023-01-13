package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ArtworkManager;
import ba.unsa.etf.rpr.domain.Artwork;
import ba.unsa.etf.rpr.domain.Exhibitions;
import ba.unsa.etf.rpr.exceptions.DBException;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.time.LocalDate;
import java.lang.String;

public class ArtworkController {




    private final ArtworkManager artworkManager;

    private ArtworkModel model = new ArtworkModel();


   // private Integer editQuoteId;

    // form fields
    public ChoiceBox<Artwork> artChooser;
    public TextField era;
    public TextField price;
    public TextField artist;
    public TextField exhibition;
    private ObservableList<Artwork> listOfArtworks;


public ArtworkController(){
        artworkManager = new ArtworkManager();
    try {
        listOfArtworks = FXCollections.observableArrayList(artworkManager.getAll());
    } catch (DBException e) {
        throw new RuntimeException(e);
    }
}
public void initialize(){

            artChooser.setItems(listOfArtworks);
            artChooser.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
                model.fromArtwork(newValue);
                if(oldValue != null) {
                    era.textProperty().unbindBidirectional(model.Era);
                    price.textProperty().unbindBidirectional(model.Price);
                    artist.textProperty().unbindBidirectional(model.Artist);
                    exhibition.textProperty().unbindBidirectional(model.Exhibition);
                } else {
                    era.textProperty().bindBidirectional(model.Era);
                    price.textProperty().bindBidirectional(model.Price);
                    artist.textProperty().bindBidirectional(model.Artist);
                    exhibition.textProperty().bindBidirectional(model.Exhibition);
                }
            });


    }




    public class ArtworkModel{
       // public SimpleStringProperty art = new SimpleStringProperty("");
        public SimpleObjectProperty<String> Name = new SimpleObjectProperty<String>();
        public SimpleObjectProperty<String> Era = new SimpleObjectProperty<String>();
        public SimpleStringProperty Price;
        public SimpleObjectProperty<String> Exhibition = new SimpleObjectProperty<String>(); //name
        public SimpleObjectProperty<String> Artist = new SimpleObjectProperty<String>();  //name

        public void fromArtwork(Artwork a){
            this.Name.set(a.getName());
            this.Era.set(a.getEra());
            this.Price = new SimpleStringProperty(String.valueOf(a.getPrice()));
           // this.Artist.set() kako?
           // this.Exhibition.set()  kako?
        }

        public Artwork toArtwork(){
            Artwork a = new Artwork();
            a.setName(this.Name.getValue());
            a.setEra(this.Era.getValue());
            a.setPrice(Double.parseDouble(Price.get()));
          //  a.setE(this.Exhibition.getValue());
            //artist/exhibition kako???
            return a;
        }
    }

}
