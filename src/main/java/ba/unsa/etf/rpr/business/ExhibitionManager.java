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

public class ExhibitionManager {

    public void validateExhibition_name(String Ename) throws DBException {
        if (Ename == null || Ename.length() > 50 || Ename.length() < 1){
            throw new DBException("Exhibition name cannot be longer than 50 or shorter than 1 character!");
        }
    }
    public void validateLocation(String loc) throws DBException {
        if (loc == null || loc.length() > 50 || loc.length() < 1){
            throw new DBException("Location name cannot be longer than 50 or shorter than 1 character!");
        }
    }

    /**
     * an additional, private function, to help validateStart and validateEnd methods
     * @param d
     * @return
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

    public Exhibitions update(Exhibitions e) throws DBException{
        validateExhibition_name(e.getExhibition_name());
        validateLocation(e.getLocation());
        validateStart_date(e.getStart_date().toLocalDate());
        validateEnd_date(e.getEnd_date().toLocalDate());
        return DaoFactory.exhibitionsDao().update(e);
    }

    public List<Exhibitions> getAll() throws DBException{
        return DaoFactory.exhibitionsDao().getAll();
    }

    public List<Exhibitions> SearchByDate(Date d) throws DBException{
        return DaoFactory.exhibitionsDao().SearchByDate(d);
    }
}


