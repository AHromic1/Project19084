package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.domain.Exhibitions;
import ba.unsa.etf.rpr.exceptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExhibitionsSQLImplementation extends AbstractDao<Exhibitions> implements ExhibitionsDao {

    private static ExhibitionsSQLImplementation instance = null;
    public ExhibitionsSQLImplementation() {

        super("Exhibitions");
    }

    public static ExhibitionsSQLImplementation getInstance(){
        if(instance==null)
            instance = new ExhibitionsSQLImplementation();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Exhibitions row2object(ResultSet rs) throws DBException {
        try{
            Exhibitions exhibitions = new Exhibitions();
            exhibitions.setId(rs.getInt("Id"));
            exhibitions.setExhibition_name(rs.getString("Exhibition_name"));
            //exhibitions.setQuote(DaoFactory.quoteDao().getById(rs.getInt("quote_id"))); zasto daofactory?
            exhibitions.setStart_date(rs.getDate("Start_date"));
            exhibitions.setEnd_date(rs.getDate("End_date"));
            exhibitions.setLocation(rs.getString("Location"));
            return exhibitions;
        }
        catch (SQLException e){
            throw new DBException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Exhibitions object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("Id", object.getId());
        item.put("Exhibition_name", object.getExhibition_name());
        item.put("Start_date", object.getStart_date());
        item.put("End_date", object.getEnd_date());
        item.put("Location", object.getLocation());
        return item;
    }

   @Override
    public List<Exhibitions> SearchByDate(Date d) throws DBException {
        return executeQuery("SELECT * FROM Exhibitions WHERE ? > Start_date AND ? < End_date", new Object[]{d, d});
    }


}
