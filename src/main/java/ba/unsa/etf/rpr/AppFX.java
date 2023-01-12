package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.controllers.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class AppFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{  //prima stage
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));  //u sample su labele, buttons i sl
        ///fxml - usli u fxml , a getResource dobavlja file iz resources
        HomeController homeController = new HomeController();
        fxmlLoader.setController(homeController);
        Parent root = fxmlLoader.load();
       /* Image image = new Image("/img/gallery1.jpg");
        ImageView mv = new ImageView(image);*/
        Scene scena = new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        primaryStage.setTitle("Pictura");
        primaryStage.setScene(scena);  //pravimo scenu i ucitavamo root - tu je sample
        //i ovdje dodati use computed size
        primaryStage.setResizable(false);  //nije dozvoljen resize
        primaryStage.getIcons().add(new Image("/img/The-Starry-Night-1200x630-1.jpg"));
        primaryStage.show();  //prikazemo stage
    }
    public static void main(String[] args) {
        launch(args);
    }
}
