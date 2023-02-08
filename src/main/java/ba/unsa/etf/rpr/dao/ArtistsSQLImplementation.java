
package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Artists;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import ba.unsa.etf.rpr.exceptions.DBException;
import java.util.Map;
import java.util.TreeMap;

/**
 *  @author Amina Hromic
 * a class for implementation of methods for Artist table
 */


public class ArtistsSQLImplementation extends AbstractDao<Artists> implements ArtistsDao{
    private static  ArtistsSQLImplementation instance = null;

    /**
     * constructor for Artists
     */
    public ArtistsSQLImplementation() {

        super("Artists");
    }

    /**
     * gets an instance of an Artist
     * @return
     */

    public static ArtistsSQLImplementation getInstance(){
        if(instance==null)
            instance = new ArtistsSQLImplementation();
        return instance;
    }

    /**
     *  removes an instance of an Artist
     */

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    /**
     * maps result set into object of type Artists
     * @param rs result set
     * @return an object of type Artist
     * @throws DBException when something is out of order
     */
    @Override
    public Artists row2object(ResultSet rs) throws DBException {
        try {
            Artists artist = new Artists();
            artist.setId(rs.getInt("Id"));
            artist.setName(rs.getString("Name"));
            return artist;
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    /**
     * maps objects of type Artists into rows
     * @param object to be mapped into row
     * @return Map<String, Object> representing a row
     */

    @Override
    public Map<String, Object> object2row(Artists object) {
        Map<String, Object> row = new TreeMap<>();
        //row.put("Id", object.getId());
        row.put("Name", object.getName());
       //nazivi se moraju poklapati?
        return row;
    }

}
