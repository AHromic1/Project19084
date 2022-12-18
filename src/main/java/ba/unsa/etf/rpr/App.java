package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.ArtistsSQLImplementation;
import ba.unsa.etf.rpr.domain.Artists;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Date;

import static javafx.application.Application.launch;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Hello world!
 *
 */
public class App extends Application
{
  /* public static void main( String[] args )
   {
      // NE RADI KONEKCIJA
        Artists artist = new Artists();
        artist.setArtist_id(20);
        artist.setFirst_name("Frida");
        artist.setLast_name("Kahlo");
        artist.setBirthplace("Spain");
        Date firstDate1 = new Date(2001, 11, 1);
        artist.setDate_of_birth(firstDate1);
        artist.setArt_FK(20);


        ArtistsSQLImplementation t = new ArtistsSQLImplementation();
        t.add(artist);
        System.out.println( "Hello World!" );


   }*/

   @Override
    public void start(Stage primaryStage) throws Exception{  //prima stage
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));  //u sample su labele, buttons i sl
       ///fxml - usli u fxml , a getResource dobavlja file iz resources

        primaryStage.setTitle("Registration");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));  //pravimo scenu i ucitavamo root - tu je sample
       //i ovdje dodati use computed size
        primaryStage.show();  //prikazemo stage
    }
    public static void main(String[] args) {
        launch(args);
    }

}
