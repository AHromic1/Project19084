package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Artwork;
import ba.unsa.etf.rpr.exceptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Amina Hromic
 * a class for implementation of methods for Artwork table
 */

public class ArtworkSQLImplementation extends AbstractDao<Artwork> implements ArtworkDao {
    private static ArtworkSQLImplementation instance = null;

    /**
     * constructor for Artwork
     */

    public ArtworkSQLImplementation() {

        super("Artwork");
    }

    /**
     * gets an instance of an Artwork
     * @return
     */

    public static ArtworkSQLImplementation getInstance(){ //jedna instanca klase
        if(instance==null)
            instance = new ArtworkSQLImplementation();
        return instance;
    }

    /**
     *  removes an instance of an Artwork
     */

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    /**
     * maps result set into object of type Artwork
     * @param rs result set
     * @return an object of type Artwork
     * @throws DBException when something is out of order
     */

    @Override
    public Artwork row2object(ResultSet rs) throws DBException {
        try {
            Artwork art = new Artwork();
            art.setId(rs.getInt("Id"));
            art.setName(rs.getString("Name"));
            art.setEra(rs.getString("Era"));
            art.setPrice(rs.getDouble("Price"));
            art.setExhibition(DaoFactory.exhibitionsDao().getById(rs.getInt("Exhibition_FK")));
            art.setArtist(DaoFactory.artistsDao().getById(rs.getInt("Artists_FK")));
            return art;
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    /**
     * maps objects of type Exhibitions into rows
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(Artwork object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("Id", object.getId());
        item.put("Name", object.getName());
        item.put("Era", object.getEra());
        item.put("Price", object.getPrice());
        item.put("Exhibition_FK", object.getExhibition().getId());
        item.put("Artists_FK", object.getArtist().getId());
        return item;
    }

}
