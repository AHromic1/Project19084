package ba.unsa.etf.rpr.business;


//koje sve validacije?

//objasnjenje za factorydao? zasto ovdje add i te metode?
//ne treba za id?
//treba li validate da su samo chars?

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Exhibitions;
import ba.unsa.etf.rpr.exceptions.DBException;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;


/**
 * business logic class for Exhibitions
 * @author Amina Hromić
 * @version 1.0
 */


public class ExhibitionManager {

    /**
     * a method to make sure that exhibition's name is in a sensible range
     * @param Ename
     * @throws DBException if the name is invalid
     */

    public void validateExhibition_name(String Ename) throws DBException {
        if (Ename.length() > 50 || Ename.length() < 1 || Ename == null){
            throw new DBException("Exhibition name cannot be longer than 50 or shorter than 1 character!");
        }
    }

    /**
     * a method to make sure that exhibition's location is in a sensible range
     * @param loc location to be checked
     * @throws DBException if the location is invalid
     */
    public void validateLocation(String loc) throws DBException {
        if (loc.length() > 50 || loc.length() < 1 || loc == null){
            throw new DBException("Location name cannot be longer than 50 or shorter than 1 character!");
        }
    }

    /**
     * an additional, private function, to help validateStart and validateEnd methods
     * @param d date to be validated
     * @return true if everything is in order, else returns false
     */
    //additionally,validateJavaDate(String) library function could have been user
    private boolean validateDate(LocalDate d){
        int y = d.getYear();
        int m = d.getMonthValue();
        int dd = d.getDayOfMonth();
      //  if(m < 0 || dd < 0 || y < 0 || y > Year.now().getValue()) return false;  //basic conditions

        if(y % 4 == 0 && y % 100 != 0 || y % 400 == 0){//leap year
            if(m == 2 &&  dd > 29) return false;
        }
        else{  //not a leap year
            if(m == 2 &&  dd > 28) return false;
        }

        if((m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) && dd > 31) return false;  //a month has 31 days
        else if(dd > 30) return false;  //a month has 30 days

        return true;
    }


    /**
     * a method to make sure that exhibition's starting date is valid
     * @param start a date to be validated
     * @throws DBException if something is out of order
     */

    public void validateStart_date(LocalDate start) throws DBException {

        //Date d = Date.valueOf(start.getYear() + "-" + start.getMonth() + "-" + start.getDayOfMonth());
        //moze?
        //LocalDate s = start;

        //timezone?
        int y = start.getYear();
        int m = start.getMonthValue();
        int dd = start.getDayOfMonth();
        int day = LocalDate.now().getDayOfMonth();

        if (start == null || !validateDate(start)
                ||  m < 0 || dd < 0 || y < 0) {
            throw new DBException("Invalid date!");
        }
    }

    /**
     * a method to make sure that exhibition's end date is valid
     * @param end a date to be validated
     * @throws DBException if something is out of order
     */

    public void validateEnd_date(LocalDate end) throws DBException {

      //  Date d = Date.valueOf(end.getYear() + "-" + end.getMonth() + "-" + end.getDayOfMonth());
        //moze?
       // LocalDate s = end;
        int y = end.getYear();
        int m = end.getMonthValue();
        int dd = end.getDayOfMonth();



        if (end == null || !validateDate(end)
                || m < 0 || dd < 0 || y < 0 ||
                y < Year.now().getValue()
                ||( y == Year.now().getValue() && m < LocalDate.now().getMonthValue())
                || (y == Year.now().getValue() && m == LocalDate.now().getMonthValue() && dd < LocalDate.now().getDayOfMonth())
        ) {
            throw new DBException("Invalid date!");
        }
    }

    /**
     * a method to ensure valid adding of a new exhibition
     * @param e exhibition to be added
     * @return an instance of a new exhibition
     * @throws DBException if the adding of a new exhibition is invalid
     */


    public Exhibitions add(Exhibitions e) throws DBException {
        if (e.getId() != 0){
            throw new DBException("You cannot add a new exhibition containing ID. ID is autogenerated");
        }
        validateExhibition_name(e.getExhibition_name());
        validateLocation(e.getLocation());
        validateStart_date(e.getStart_date().toLocalDate());
        validateEnd_date(e.getEnd_date().toLocalDate());

        try{
            return DaoFactory.exhibitionsDao().add(e);
        }
        catch (DBException exc){
            System.out.println(exc.getMessage());

            if (exc.getMessage().contains("UQ_NAME")){ //opet, sta staviti?
                throw new DBException("Exhibition with same name exists");
            }
            throw exc;
        }
    }

    /**
     * a method to ensure valid deletion of an exhibition
     * @param exhibitionId id of an exhibition to be deleted
     * @throws DBException if parent-child integrity is compromised
     */

    public void delete(int exhibitionId) throws DBException{
        try{
           DaoFactory.exhibitionsDao().delete(exhibitionId);
        }
        catch (DBException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new DBException("Deletion cannot be completed. First delete related items before deleting these rows.");
            }
            throw e;
        }

    }
    /**
     * a method to ensure valid updating of an exhibition
     * @param e exhibition to be updated to
     * @return instance of an updated exhibition
     * @throws DBException if something is out of order
     */

    public Exhibitions update(Exhibitions e) throws DBException{
        validateExhibition_name(e.getExhibition_name());
        validateLocation(e.getLocation());
        validateStart_date(e.getStart_date().toLocalDate());
        validateEnd_date(e.getEnd_date().toLocalDate());
        return DaoFactory.exhibitionsDao().update(e);
    }

    /**
     * a method to get a list of all exhibitions
     * @return a list of all exhibitions
     * @throws DBException if something is out of order
     */


    public List<Exhibitions> getAll() throws DBException{
        return DaoFactory.exhibitionsDao().getAll();
    }

    /**
     * a method which shows a list of all exhibitions happening on a required date
     * @param d date used to search for exhibitions
     * @return a list of all exhibitions happening on a required date
     * @throws DBException if something is out of order
     */

    public List<Exhibitions> SearchByDate(Date d) throws DBException{
        return DaoFactory.exhibitionsDao().SearchByDate(d);
    }
}


