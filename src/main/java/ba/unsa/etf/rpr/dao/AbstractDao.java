package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.DBException;

import java.sql.*;
import java.util.*;


/**
 * Abstract class that implements core DAO CRUD methods for every entity
 * @author Amina Hromic
 * @version 1.0
 */

public abstract class AbstractDao <T extends Idable> implements Dao<T>{
    private static Connection connection;
    private String tableName;

    /**
     * constructor
     * @param tableName name of the table
     */

    public AbstractDao(String tableName) {
        this.tableName = tableName;
        if(connection==null) createConnection();
}

    /**
     * a method which create a connection to the database, also uses threads
     */
    private static void createConnection(){
        if(AbstractDao.connection==null) {
            try {
                Properties p = new Properties();
                p.load(ClassLoader.getSystemResource("database.properties").openStream());
                String url = p.getProperty("url");
                String username = p.getProperty("username");
                String password = p.getProperty("password");
                AbstractDao.connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
                //System.exit(0);
            }finally {
                Runtime.getRuntime().addShutdownHook(new Thread(){
                    @Override
                    public void run(){
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    /**
     * @return connection to the database
     */

    public Connection getConnection(){

        return AbstractDao.connection;
    }

    /**
     * For singleton pattern there is only one connection to the database which will be closed automatically when our program ends
     * This method enables for a connection to be closed manually, and should be called from a finally block
     */

    public static void closeConnection() {
        System.out.println("Closing connection");
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                //throw new RuntimeException(e);
                e.printStackTrace();
                System.out.println("REMOVE CONNECTION METHOD ERROR: Unable to close connection on a database");
            }
        }
    }

    /**
     * setter for connection
     * @param connection
     */

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    /**
     * mapping ResultSet into Object
     * @param rs result set
     * @return Object of type T
     * @throws DBException if something is out of order
     */

    public abstract T row2object(ResultSet rs) throws DBException;//from database to an object
    //implementation in each separate dao

    /**
     * mapping from Object to Map
     * @param object to be mapped
     * @return a map with values <String,Object>
     */



    public abstract Map<String, Object> object2row(T object);
    //from object to database

    /**
     * gets a value from a table for a corresponding id
     * @param id primary key of entity
     * @return Object of type T
     * @throws DBException if something is out of order
     */
    public T getById(int id) throws DBException {
        return executeQueryUnique("SELECT * FROM " + this.tableName + " WHERE id = ?", new Object[]{id}); //niz objekata
        //moze i executeQuery
    }

    /**
     * returns all valuee from a table
     * @return List of objects of type T
     * @throws DBException when something is out of order
     */
    public List<T> getAll() throws DBException {
        return executeQuery("SELECT * FROM " + tableName, null);
    }

    /**
     * deletes a value from a table for a corresponding id
     * @param id - primary key of entity
     * @throws DBException when something is out of order
     */
    public void delete(int id) throws DBException {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //vraca sve jedinstvene kljuceve iz baze, autogenerisani, u id = ?
            stmt.setObject(1, id);//1 se odnosi na prvi ?
            stmt.executeUpdate();
        }
        catch (SQLException e){
            throw new DBException(e.getMessage(), e);
        }
    }

    /**
     * adds item to a database
     * @param item bean for saving to database
     * @return Object of type T
     * @throws DBException
     */
    public T add(T item) throws DBException {
        Map<String, Object> row = object2row(item); //
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") "); //getKey vraca naziv kolone
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            // bind params. IMPORTANT treeMap is used to keep columns sorted so params are bind correctly
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {  //pr
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys(); //pr
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back */

            return item;  //obicno se vraca
        }
        catch (SQLException e){
            throw new DBException(e.getMessage(), e);
        }
    }

    /**
     * updates an item in a database
     * @param item - bean to be updated. id must be populated
     * @return Object of type T
     * @throws DBException
     */

    public T update(T item) throws DBException{
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }
        catch (SQLException e){
            throw new DBException(e.getMessage(), e);
        }
    }
//return null???

    /**
     * Utility method for executing any kind of query
     * @param query - SQL query
     * @param param - parameters for query
     * @return List of objects from database
     * @throws DBException in case of an error with the database
     */
    public List<T> executeQuery(String query, Object[] param) throws DBException{  //pokrece upit
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            //šta je ovo ispod?
            if (param != null){
                for(int i = 1; i <= param.length; i++){  //zasto od 1?
                    stmt.setObject(i, param[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> result = new ArrayList<>();
            while (rs.next()) {
                result.add(row2object(rs));
            }
            return result;
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    /**
     * Utility for query execution that always return single record
     * @param query - query that returns single record
     * @param param - list of parameters for sql query
     * @return Object of type T
     * @throws DBException in case when object is not found
     */
    public T executeQueryUnique(String query, Object[] param) throws DBException{
        List<T> result = executeQuery(query, param);
        if (result != null && result.size() == 1){
            return result.get(0);
        }
        else {
            throw new DBException("There is no object");
        }
    }


    /**
     * Accepts KV storage of column names and return CSV of columns and question marks for insert statement
     * Example: (id, name, date) ?,?,?
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip insertion of id due autoincrement
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<String,String>(columns.toString(), questions.toString());
    }

    /**
     * Prepare columns for update statement id=?, name=?, ...
     * @param row - represented with a Map
     * @return String
     */
    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip update of id due where clause
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }




}
