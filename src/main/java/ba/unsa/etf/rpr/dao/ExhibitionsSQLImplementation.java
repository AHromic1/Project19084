package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.domain.Exhibitions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExhibitionsSQLImplementation implements Dao<Exhibitions> {
    private Connection connection;

    /**
     * constructor for Artists
     */
    public ExhibitionsSQLImplementation(){
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

    @Override
    public Exhibitions getById(int id) {
        String query = "SELECT * FROM Exhibitions WHERE Exhibition_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){ // result set is iterator.
                Exhibitions exhibition = new Exhibitions();
                exhibition.setExhibition_id(rs.getInt(1));
                exhibition.setExhibition_name(rs.getString(2));  //pokušaj
                exhibition.setStart_date(rs.getDate(3));
                exhibition.setEnd_date(rs.getDate(4));
                exhibition.setLocation(rs.getString(5));

                rs.close();
                return exhibition;
            }
            else{
                return null; // if there is no elements in the result set return null
            }
        }
        catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;

    }

    @Override
    public Exhibitions add(Exhibitions item) {
        String insert = "INSERT INTO Exhibitions(Exhibition_name, Start_date, End_date, Location) VALUES(?,?,?,?)";

        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, item.getExhibition_name());
            stmt.setDate(2,  item.getStart_date());
            stmt.setDate(3,  item.getEnd_date());
            stmt.setString(4, item.getLocation());


            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            rs.next(); // we know that there is one key

            item.setExhibition_id(rs.getInt(1)); //set id to return it back
            return item;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;   //ZASTO???  ybog exception
    }

    @Override
    public Exhibitions update(Exhibitions item) {
        String update = "UPDATE Exhibitions SET Exhibition_name = ?, Start_date = ?, End_date  = ?, Location = ?  WHERE Exhibition_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getExhibition_name());
            stmt.setObject(2, item.getStart_date());
            stmt.setObject(3, item.getEnd_date());
            stmt.setObject(4, item.getLocation());

            stmt.executeUpdate();

            return item;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }


    }
    @Override
    public void delete(int id) {
        String delete = "DELETE FROM Exhibitions WHERE Exhibition_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            //ustvari, puni se ova statement, koja će zatim nesto uraditi

            stmt.executeUpdate();
            /*Invokes the SQL command contained within the prepared statement. This must be INSERT, UPDATE, DELETE, or a command that returns nothing.*/
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Exhibitions> getAll() {
        String query = "SELECT * FROM Exhibitions";
        List<Exhibitions> exhibitions = new ArrayList<Exhibitions>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Exhibitions exhibition = new Exhibitions();
                exhibition.setExhibition_id(rs.getInt(1));
                exhibition .setExhibition_name(rs.getString(2));  //pokušaj
                exhibition.setStart_date(rs.getDate(3));
                exhibition.setEnd_date(rs.getDate(4));
                exhibition.setLocation(rs.getString(5));

                exhibitions.add(exhibition);
            }
            rs.close();
        }
        catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return exhibitions;
    }

}
