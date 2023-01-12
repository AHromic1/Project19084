package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ExhibitionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {

    public void openHelp(ActionEvent actionEvent){
        openDialog("Help", "/fxml/help.fxml", null);

    }

    public void openExhibitions(ActionEvent actionEvent){
        openDialog("Exhibitions", "/fxml/exhibitions.fxml", new ExhibitionsController()); //konstruktor
    }

    public void openArtists(ActionEvent actionEvent){
        openDialog("Artists", "/fxml/artists.fxml", new ArtistsController());
    }

    public void openSuggestions(ActionEvent actionEvent){
        openDialog("Artists", "/fxml/artists.fxml", new ArtistsController());
    }


    public void openArtwork(ActionEvent actionEvent){
        openDialog("Artwork", "/fxml/artwork.fxml", new ArtworkController());
    }



    public void openEmail(ActionEvent actionEvent){
        openDialog("Email", "/fxml/email.fxml", new EmailController());
    }





    private void openDialog(String title, String file, Object controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }
        catch (Exception e){
           new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
          alert.setHeaderText(e.getMessage());

           alert.showAndWait();
            e.printStackTrace();
        }
    }



}
