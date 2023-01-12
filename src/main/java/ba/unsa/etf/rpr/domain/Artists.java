package ba.unsa.etf.rpr.domain;
//import ba.unsa.etf.rpr.domain.Artists;
//zasto import ovaj?


import java.sql.Date;
import java.util.Objects;

public class Artists implements Idable
{

    //java beans? sta da radim s imenima, da li da mijenjam i ovdje i u bazi podataka ?
    private static int Id ;
    private static String Name;
    private static String Birthplace;
    private static Date Date_of_birth;



    public String getName() {
        return Name;
    }

    public static void setName(String first_name) {
        Name = first_name;
    }


    public String getBirthplace() {
        return Birthplace;
    }

    public static void setBirthplace(String birthplace) {
        Birthplace = birthplace;
    }

    public Date getDate_of_birth() {
        return Date_of_birth;
    }

    public static void setDate_of_birth(Date date_of_birth) {
        Date_of_birth = date_of_birth;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artists artists = (Artists) o;  //objest turned into Artists
        return Id == artists.Id &&  Name.equals(artists.Name) &&
                 Birthplace.equals(artists.Birthplace) && Date_of_birth.equals(artists.Date_of_birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Name, Birthplace, Date_of_birth);
    }

    @Override
    public String toString() {
        return "Artists{" +
                "Artist_id=" + Id +
                ", First_name='" + Name + '\'' +
                ", Birthplace='" + Birthplace + '\'' +
                ", Date_of_birth=" + Date_of_birth +
                "}";
    }

    @Override
    public void setId(int id) {
        Id = id;

    }

    @Override
    public int getId() {
        return Id;
    }
}
