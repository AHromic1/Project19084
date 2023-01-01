
package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Artists;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import ba.unsa.etf.rpr.exceptions.DBException;
import java.util.Map;
import java.util.TreeMap;


public class ArtistsSQLImplementation extends AbstractDao<Artists> implements ArtistsDao{

    /**
     * constructor for Artists
     */
    public ArtistsSQLImplementation() {
        super("Artists");
    }
    @Override
    public Artists row2object(ResultSet rs) throws DBException {
        try {
            Artists artist = new Artists();
            artist.setId(rs.getInt("Id"));
            artist.setFirst_name(rs.getString("First_name"));
            artist.setLast_name(rs.getString("Last_name"));
            artist.setBirthplace(rs.getString("Birthplace"));
            artist.setDate_of_birth(rs.getDate("Date_od_Birth"));
            return artist;
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Artists object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("Id", object.getId());
        row.put("First_name", object.getFirst_name());
        row.put("Last_name", object.getLast_name());
        row.put("Birthplace", object.getBirthplace());
        row.put("Date_of_Birth", object.getDate_of_birth()); //nazivi se moraju poklapati?
        return row;
    }

}
