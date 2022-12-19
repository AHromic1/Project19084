package ba.unsa.etf.rpr.domain;
//import ba.unsa.etf.rpr.domain.Artists;
//zasto import ovaj?


import java.util.Date;
import java.util.Objects;

public class Artists implements Idable
{

    //java beans? sta da radim s imenima, da li da mijenjam i ovdje i u bazi podataka ?
    private static int Id ;
    private static String First_name;
    private static String Last_name;
    private static String Birthplace;
    private static Date Date_of_birth;
    private static int Art_FK;  //da li je i ovo potrebno ?


    public String getFirst_name() {
        return First_name;
    }

    public static void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public static void setLast_name(String last_name) {
        Last_name = last_name;
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

    public int getArt_FK() {
        return Art_FK;
    }

    public static void setArt_FK(int art_FK) {
        Art_FK = art_FK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artists artists = (Artists) o;  //objest turned into Artists
        return Id == artists.Id && Art_FK == artists.Art_FK && First_name.equals(artists.First_name) &&
                Last_name.equals(artists.Last_name) && Birthplace.equals(artists.Birthplace) && Date_of_birth.equals(artists.Date_of_birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, First_name, Last_name, Birthplace, Date_of_birth, Art_FK);
    }

    @Override
    public String toString() {
        return "Artists{" +
                "Artist_id=" + Id +
                ", First_name='" + First_name + '\'' +
                ", Last_name='" + Last_name + '\'' +
                ", Birthplace='" + Birthplace + '\'' +
                ", Date_of_birth=" + Date_of_birth +
                ", Art_FK=" + Art_FK +
                '}';
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
