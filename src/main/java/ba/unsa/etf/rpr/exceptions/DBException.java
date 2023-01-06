package ba.unsa.etf.rpr.exceptions;

/**
 * custom exception with two different versions
 */
public class DBException extends Exception{
    public DBException(String message, Exception e){

        super(message, e);
    }
    public DBException(String message){
        super(message);
    }
}
