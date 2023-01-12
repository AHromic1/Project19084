package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.ArtistsSQLImplementation;
import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.exceptions.DBException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static javafx.application.Application.launch;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Hello world!
 *
 */
public class App
{
   public static void main( String[] args )
   {

       Artists artist = new Artists();
       artist.setId(20);
       //artist.setFirst_name("Gustav");
       //artist.setLast_name("Klimt");
       artist.setBirthplace("Austria");
       // Date firstDate1 = new Date(2001, 11, 1);  //koristiti calendar

       Calendar c = new GregorianCalendar();
       c.set(1812, 12, 12);
       Date firstDate1 =  c.getTime();
       ////artist.setDate_of_birth(firstDate1);



       ArtistsSQLImplementation t = new ArtistsSQLImplementation();
       //t.add(artist);  unhandled exception?
       System.out.println( "Hello World!" );



   }


}
