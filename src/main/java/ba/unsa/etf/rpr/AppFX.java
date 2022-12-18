package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AppFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{  //prima stage
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));  //u sample su labele, buttons i sl
        ///fxml - usli u fxml , a getResource dobavlja file iz resources

        primaryStage.setTitle("Registration");
        primaryStage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));  //pravimo scenu i ucitavamo root - tu je sample
        //i ovdje dodati use computed size
        primaryStage.setResizable(false);  //nije dozvoljen resize
        primaryStage.getIcons().add(new Image("/img/The-Starry-Night-1200x630-1.jpg"));
        primaryStage.show();  //prikazemo stage
    }
    public static void main(String[] args) {
        launch(args);
    }
}
