
package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Artists;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class ArtistsSQLImplementation  implements Dao<Artists>{
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

    @Override
    public Artists getById(int id) {
        String query = "SELECT * FROM Artists WHERE Artist_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            //ZASTO RADIMO OVO? - prelazimo u iduci red (iz kojeg citamo, tj ako postoji red, onda...)
            //pravimo novi ibjekar artists, u koji upisujemo redom ćelije iz reda, koji ćemo ONDA proslijediti presentation layer i u interface

            //DA LI JE POTREBNA METODA ZA SVAKU KOLONU - setfirstname, last name, itd DA


            if (rs.next()){ // result set is iterator. uslov?
                Artists artists = new Artists();
                Artists.setArtist_id(rs.getInt("Artist_id"));
                Artists.setFirst_name(rs.getString(2));  //pokušaj
                Artists.setLast_name(rs.getString(3));
                Artists.setBirthplace(rs.getString(4));
                Artists.setDate_of_birth(rs.getDate(5));
                Artists.setArt_FK(rs.getInt(6));
                rs.close();
                return artists;
            }
            else{
                return null; // if there is no elements in the result set return null
            }
        }
        catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
        //ZBOG ECXEPTION, on ce ispisati tekst stack trace, ali treba da nesto vrati
    }

    @Override
    public Artists add(Artists item) {
        String insert = "INSERT INTO Artists(First_name, Last_name, Birthplace, Date_of_birth, Art_FK) VALUES(?,?,?,?, ?)";
        //da li se art fk uopce uzima u obzir?
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            //generated keys slicno kao auto increment??? - da, to je id



            stmt.setString(1, item.getFirst_name());
            stmt.setString(2, item.getLast_name());
            stmt.setString(1, item.getBirthplace());
            stmt.setDate(1, item.getDate_of_birth());
            stmt.setInt(1, item.getArt_FK());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            //zasto rs.next
            rs.next(); // we know that there is one key
            //prelazimo u željeni red i dodajemo
            item.setArtist_id(rs.getInt(1)); //set id to return it back

            //setamo samo id ?
            return item;  //zašto smo morali da vraćamo item?
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;   //ZASTO???  ybog exception
    }

    @Override
    public Artists update(Artists item) {
        String update = "UPDATE Artists SET First_name = ?, Last_name = ?, Birthplace = ?, Date_of_birth = ?, Art_FK = ?   WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getFirst_name());
            stmt.setObject(2, item.getLast_name());
            stmt.setObject(3, item.getBirthplace());
            stmt.setObject(4, item.getDate_of_birth());
            stmt.setObject(5, item.getArt_FK());

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
        String delete = "DELETE FROM Artists WHERE id = ?";
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
    public List<Artists> getAll() {
        String query = "SELECT * FROM Artists";
        List<Artists> artists = new ArrayList<Artists>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Artists A = new Artists();
                A.setArtist_id(rs.getInt("Artist_id"));
                A.setFirst_name(rs.getString(2));  //pokušaj
                A.setLast_name(rs.getString(3));
                A.setBirthplace(rs.getString(4));
                A.setDate_of_birth(rs.getDate(5));
                A.setArt_FK(rs.getInt(6));
            }
            rs.close();
        }
        catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return artists;
    }


    //javadoc, dodavati odmah ili ?

}
