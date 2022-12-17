package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.ArtistsSQLImplementation;
import ba.unsa.etf.rpr.domain.Artists;
import java.sql.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Artists artist = new Artists();
        artist.setFirst_name("Frida");
        artist.setLast_name("Kahlo");
        artist.setBirthplace("Spain");
        Date firstDate1 = new Date(2001, 11, 1);
        artist.setDate_of_birth(firstDate1);

        ArtistsSQLImplementation t = new ArtistsSQLImplementation();
        t.add(artist);
        ///TESTIRATI!
        System.out.println( "Hello World!" );
    }
}
