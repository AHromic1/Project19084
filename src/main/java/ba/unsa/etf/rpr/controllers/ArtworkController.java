package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ArtworkManager;
import ba.unsa.etf.rpr.domain.Artists;
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

/**
 * @author Amina Hromic
 * controller for artwork fxml file
 */

public class ArtworkController {
    /**
     * ids
     */

    private final ArtworkManager artworkManager;

    private ArtworkModel model = new ArtworkModel();

    public ChoiceBox<Artwork> artChooser;
    public TextField era;
    public TextField price;
    public TextField artist;
    public TextField exhibition;
    private ObservableList<Artwork> listOfArtworks;

    /**
     * a constructor
     */
    public ArtworkController(){
        artworkManager = new ArtworkManager();
    try {
        listOfArtworks = FXCollections.observableArrayList(artworkManager.getAll());
    } catch (DBException e) {
        throw new RuntimeException(e);
    }
}

    /**
     * method used for initialization to an initial state
     */
public void initialize(){

            artChooser.setItems(listOfArtworks);
            era.textProperty().bindBidirectional(model.Era);
            price.textProperty().bindBidirectional(model.Price);
            artist.textProperty().bindBidirectional(model.Artist);

            exhibition.textProperty().bindBidirectional(model.Exhibition);

            artChooser.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
                model.fromArtwork(newValue);
            });


    }

    /**
     * model class used to help with data binding
     */


    public class ArtworkModel{
       // public SimpleStringProperty art = new SimpleStringProperty("");
        public SimpleObjectProperty<String> Name = new SimpleObjectProperty<String>();
        //ne moze obican string  - pravilo
        public SimpleObjectProperty<String> Era = new SimpleObjectProperty<String>();
        public SimpleStringProperty Price = new SimpleStringProperty();
        public SimpleStringProperty Exhibition = new SimpleStringProperty();
       // public SimpleObjectProperty<Exhibitions> Exhibition = new SimpleObjectProperty<Exhibitions>(); //name
       public SimpleStringProperty Artist = new SimpleStringProperty();
       // public SimpleObjectProperty<Artists> Artist = new SimpleObjectProperty<Artists>();  //name

        //kao objtorow i obnuto

        /**
         * a method which transforms an instance of artwork into object to be shown
         * @param a
         */
        public void fromArtwork(Artwork a){
            this.Name.set(a.getName());
            this.Era.set(a.getEra());
            this.Price.set(String.valueOf(a.getPrice()));
            this.Artist.set(String.valueOf(a.getArtist()));
            //this.Artist.set(a.getArtist());
            this.Exhibition.set(String.valueOf(a.getExhibition().getExhibition_name()));
            //this.Exhibition.set(a.getExhibition()); //  kako?
        }

        /**
         * transforms shown objects into instances of Artwork
         * @return an instance of Artwork
         */
        public Artwork toArtwork(){
            Artwork a = new Artwork();
            a.setName(this.Name.getValue());
            a.setEra(this.Era.getValue());
            a.setPrice(Double.parseDouble(Price.get()));
            a.setExhibition(new Exhibitions(Exhibition.get()));
         //   a.setArtist(this.Artist.getValue());
            return a;
        }
    }

}
