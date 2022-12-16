package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.domain.Artwork;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtworkSQLImplementation implements Dao<Artwork> {

    private Connection connection;

    public ArtworkSQLImplementation(){
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
    public Artwork getById(int id) {
        String query = "SELECT * FROM Artwork WHERE Artwork_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){ // result set is iterator.
                Artwork art = new Artwork();
                art.setArtwork_id(rs.getInt(1));
                art.setName(rs.getString(2));
                art.setType(rs.getString(3));
                art.setEra(rs.getString(4));
                art.setStyle(rs.getString(5));
                art.setPrice(rs.getDouble(6));
                art.setCustomer_FK(rs.getInt(7));
                art.setExhibition_FK(rs.getInt(8));
                rs.close();
                return art;
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
    public Artwork add(Artwork item) {
        String insert = "INSERT INTO Artwork(Name, Type, Era, Style, Price, Customer_FK, Exhibition_FK ) VALUES(?,?,?,?, ?, ? , ?)";
        //da li se art fk uopce uzima u obzir?
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            //generated keys slicno kao auto increment??? - da, to je id



            stmt.setString(1, item.getName());
            stmt.setString(2, item.getType());
            stmt.setString(3, item.getEra());
            stmt.setString(4, item.getStyle());
            stmt.setDouble(5, item.getPrice());
            stmt.setInt(6, item.getCustomer_FK());
            stmt.setInt(7, item.getExhibition_FK());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            //zasto rs.next
            rs.next(); // we know that there is one key
            //prelazimo u željeni red i dodajemo
            item.setArtwork_id(rs.getInt(1)); //set id to return it back

            //setamo samo id ?
            return item;  //zašto smo morali da vraćamo item?
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;   //ZASTO???  ybog exception
    }


    @Override
    public Artwork update(Artwork item) {
        String update = "UPDATE Artwork SET Name = ?, Type = ?, Era = ?, Style = ?, Price = ?, Customer_FK = ?, Exhibition_FK = ?   WHERE Artwork_id = ?";
        try{
            ///zasto je ovdje setobject???
            PreparedStatement stmt = this.connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            //prepare je s onim sto se salje
            stmt.setObject(1, item.getName());
            stmt.setObject(2, item.getType());
            stmt.setObject(3, item.getEra());
            stmt.setObject(4, item.getStyle());
            stmt.setObject(5, item.getPrice());
            stmt.setObject(6,item.getCustomer_FK());
            stmt.setObject(7, item.getExhibition_FK());
            //result set ne radimo - ništa ne vraća novo
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
        String delete = "DELETE FROM Artwork WHERE Artwork_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
            //RETURN GENERATED KEYS  - kad god nemamo id?
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Artwork> getAll() {
        String query = "SELECT * FROM ARTWORK";
        List<Artwork> art = new ArrayList<>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            //nema nista da se prepare
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Artwork artwork = new Artwork();
                artwork.setArtwork_id(rs.getInt(1));
                artwork.setName(rs.getString(2));
                artwork.setType(rs.getString(3));
                artwork.setEra(rs.getString(4));
                artwork.setStyle(rs.getString(5));
                artwork.setPrice(rs.getDouble(6));
                artwork.setCustomer_FK(rs.getInt(7));
                artwork.setExhibition_FK(rs.getInt(8));

                art.add(artwork);
            }
            rs.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return art;
    }

    public List<Artwork> getByEra(String era){
        String query = "SELECT * FROM Artwork WHERE Era = ?";
        List<Artwork> art = new ArrayList<>();

        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setObject(1, era);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Artwork artwork = new Artwork();
                if (era.equals(rs.getString(4))) {  //ne moze getera direktno na rs!
                    artwork.setArtwork_id(rs.getInt(1));
                    artwork.setName(rs.getString(2));
                    artwork.setType(rs.getString(3));
                    artwork.setEra(rs.getString(4));
                    artwork.setStyle(rs.getString(5));
                    artwork.setPrice(rs.getDouble(6));
                    artwork.setCustomer_FK(rs.getInt(7));
                    artwork.setExhibition_FK(rs.getInt(8));
                    art.add(artwork);
                }
            }

            rs.close();
        }
            catch(SQLException e){
                e.printStackTrace();  //zasto bas printstacktrace
            }
        return art;
    }
}
