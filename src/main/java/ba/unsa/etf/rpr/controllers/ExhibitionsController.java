package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ExhibitionManager;
import ba.unsa.etf.rpr.domain.Exhibitions;
import ba.unsa.etf.rpr.exceptions.DBException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
  * @author Amina Hromic
  * controller for exhibitions fxml file
   */

public class ExhibitionsController {

    private final ExhibitionManager exhibitionManager = new ExhibitionManager();

    @FXML

    public DatePicker date;
    public TableView<Exhibitions> exhibitionsTable = new TableView<>();
    public TableColumn<Exhibitions, String> Exhibition = new TableColumn<>();
    public TableColumn<Exhibitions, String> Location = new TableColumn<>();
    public TableColumn<Exhibitions, Date> Start = new TableColumn<>();
    public TableColumn<Exhibitions, Date> End = new TableColumn<>();
    public ObservableList<Exhibitions> observableList = FXCollections.observableArrayList();  //treba observable list da bi se moglo proslijediti

    /**
     * constructor
     */
    public ExhibitionsController() {
       try {
            observableList.addAll(exhibitionManager.getAll());
            System.out.println(observableList);
       } catch (DBException e) {
           e.printStackTrace();
          System.out.println("Something went wrong with getAll() method from exhibitionManager!!!");
       }//konstruktor da bi se mogao otvoriti kontroler
    }

    /**
     * method used for initialization to an initial state
     */
    @FXML
    void initialize(){
        Exhibition.setCellValueFactory(new PropertyValueFactory<Exhibitions, String>("Exhibition_name"));  //kol u workbench
        Location.setCellValueFactory(new PropertyValueFactory<Exhibitions, String>("Location"));
        Start.setCellValueFactory(new PropertyValueFactory<Exhibitions, Date>("Start_date"));
        End.setCellValueFactory(new PropertyValueFactory<Exhibitions, Date>("End_date"));
        try {
            exhibitionsTable.setItems(FXCollections.observableList(exhibitionManager.getAll()));
        }
        catch (DBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * date picker event handler - a method which searches for exhbitions by a date set by date picker
     * @param event which sets the action in motion
     */
   public void SearchByDate(ActionEvent event){
        try {
            exhibitionsTable.setItems(FXCollections.observableList(exhibitionManager.SearchByDate(Date.valueOf(date.getValue()))));
            exhibitionsTable.refresh();
        }
        catch (DBException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }






    /**
     * fetch quotes from the database - refresh
     */
    private void refreshExhibitions(){
        try {
            exhibitionsTable.setItems(FXCollections.observableList(exhibitionManager.getAll()));
            exhibitionsTable.refresh();
        } catch (DBException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
