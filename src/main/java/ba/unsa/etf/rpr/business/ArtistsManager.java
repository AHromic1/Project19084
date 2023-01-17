package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.domain.Exhibitions;
import ba.unsa.etf.rpr.exceptions.DBException;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public class ArtistsManager {


        public void validateName(String name) throws DBException {
            if (name == null || name.length() > 50 ||name.length() < 1){
                throw new DBException("Artist's first name cannot be longer than 50 or shorter than 1 character1!");
            }
        }




    private boolean validateDate(LocalDate d){
        int y = d.getYear();
        int m = d.getMonthValue();
        int dd = d.getDayOfMonth();
        if(m < 0 || dd < 0 || y < 0 || y > Year.now().getValue()) return false;  //basic conditions

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






    public Artists add(Artists a) throws DBException {
        if (a.getId() != 0){
            throw new DBException("You cannot add a new artist containing ID. ID is autogenerated");
        }
        validateName(a.getName());

        try{
              return DaoFactory.artistsDao().add(a);
        }
        catch (DBException exc){
            //ovo???
            if (exc.getMessage().contains("UQ_NAME")){
                throw new DBException("Artist with same name exists");
            }
            throw exc;
        }
    }

    public static void delete(int artistId) throws DBException{
        try{
             DaoFactory.artistsDao().delete(artistId);
        }
        catch (DBException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new DBException("First delete related items before deleting these rows.");
            }
            throw e;
        }

    }

    public Artists update(Artists a) throws DBException{
        validateName(a.getName());
        validateBirthplace(a.getBirthplace());
        validateBirth_date(a.getDate_of_birth().toLocalDate());
         return DaoFactory.artistsDao().update(a);
    }

    public List<Artists> getAll() throws DBException{
        return DaoFactory.artistsDao().getAll();
    }


}

