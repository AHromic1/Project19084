
package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Artists;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class ArtistsSQLImplementation  {
    private Connection connection;

    /**
     * constructor for Artists
     */
    public ArtistsSQLImplementation(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net", "sql7582777", "RUnD8aSmIE");
            //connects to a database
            //url: jdbc:mysql:// + my link
        }
        catch (Exception e){
            e.printStackTrace();
            //shows where the exception occured
        }
    }






}
