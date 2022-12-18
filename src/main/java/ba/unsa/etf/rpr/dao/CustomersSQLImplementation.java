package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersSQLImplementation implements Dao<Customers> {
    private Connection connection;

    public CustomersSQLImplementation(){
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
    public Customers getById(int id) {
        String query = "SELECT * FROM Customers WHERE Customer_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){ // result set is iterator. uslov?
                Customers customer= new Customers();
                customer.setCustomer_id(rs.getInt(1));
                customer.setFirst_name(rs.getString(2));  //pokušaj
                customer.setLast_name(rs.getString(3));
                customer.setPhone_number(rs.getString(4));
                customer.setAddress(rs.getString(5));
                customer.setArt_FK(rs.getInt(6));
                rs.close();
                return customer;
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
    public Customers add(Customers item) {
        String insert = "INSERT INTO Customers(First_name, Last_name, Phone_number, Address, Art_FK) VALUES(?,?,?,?,?)";

        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            //generated keys slicno kao auto increment??? - da, to je id

            stmt.setString(1, item.getFirst_name());
            stmt.setString(2, item.getLast_name());
            stmt.setString(3, item.getPhone_number());
            stmt.setString(4, item.getAddress());
            stmt.setInt(5, item.getArt_FK());

            stmt.executeUpdate(); //ovdje treba jer nismo rs preko toga uradili?

            ResultSet rs = stmt.getGeneratedKeys();
            //zasto rs.next
            rs.next(); // we know that there is one key
            //prelazimo u željeni red i dodajemo
            item.setCustomer_id(rs.getInt(1)); //set id to return it back

            //setamo samo id ?
            return item;  //zašto smo morali da vraćamo item?
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customers update(Customers item) {
        String update = "UPDATE Customers SET First_name = ?, Last_name = ?, Phone_number = ?, Address = ?, Art_FK = ?   WHERE Customer_id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getFirst_name());
            stmt.setObject(2, item.getLast_name());
            stmt.setObject(3, item.getPhone_number());
            stmt.setObject(4, item.getAddress());
            stmt.setObject(5, item.getArt_FK());

            stmt.executeUpdate();
            //isto kao i prethodno, ali bez rs jer nam ne treb ijedna nova informacija, već znamo id

            return item;  //zasto vracamo ista?
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public void delete(int id) {
        String delete = "DELETE FROM Customers WHERE Customer_id = ?";
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
    public List<Customers> getAll() {
        String query = "SELECT * FROM Customers";
        List<Customers> customers = new ArrayList<Customers>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Customers customer = new Customers();
                customer.setCustomer_id(rs.getInt(1));
                customer.setFirst_name(rs.getString(2));  //pokušaj
                customer.setLast_name(rs.getString(3));
                customer.setPhone_number(rs.getString(4));
                customer.setAddress(rs.getString(5));
                customer.setArt_FK(rs.getInt(6));
                customers.add(customer);
            }
            rs.close();
        }
        catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return customers;
    }




}
