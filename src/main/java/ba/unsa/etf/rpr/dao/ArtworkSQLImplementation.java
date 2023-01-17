package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Artwork;
import ba.unsa.etf.rpr.exceptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ArtworkSQLImplementation extends AbstractDao<Artwork> implements ArtworkDao {
    private static ArtworkSQLImplementation instance = null;

    public ArtworkSQLImplementation() {

        super("Artwork");
    }

    public static ArtworkSQLImplementation getInstance(){
        if(instance==null)
            instance = new ArtworkSQLImplementation();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }
    @Override
    public Artwork row2object(ResultSet rs) throws DBException {
        try {
            Artwork art = new Artwork();
            art.setId(rs.getInt("Id"));
            art.setName(rs.getString("Name"));
            art.setEra(rs.getString("Era"));
            art.setPrice(rs.getDouble("Price"));
           // art.setCategory(DaoFactory.categoryDao().getById(rs.getInt("category_id")));  //daofactory ovdje radi sta ?
            return art;
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    /**
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
        return item;
    }

    /**
     * a method for finding artwork by era
     * @param   era
     * @return list of artwork
     * @author ahromic1
     */

   // @Override   ?
    public List<Artwork> searchByEra(String era) throws DBException{
        return executeQuery("SELECT * FROM Artwork WHERE Era LIKE concat('%', ?, '%')", new Object[]{era});  //zasto %
    }

    /**
     * a method for finding artwork using artist's last name
     * @param artist_name
     * @return list of artowek
     * @throws DBException
     */

    //provjeri
    //@Override ?
    public List<Artwork> searchByArtist(String artist_name) throws DBException{
        return executeQuery("SELECT * FROM Artwork a1, Artists a2 WHERE a1.Artist_FK = a2.Id AND a2.Last_Name LIKE concat('%', ?, '%')", new Object[]{artist_name});  //zasto %
    }



}
