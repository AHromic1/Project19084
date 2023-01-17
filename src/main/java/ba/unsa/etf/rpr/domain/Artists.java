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


    public String getName() {
        return Name;
    }

    public static void setName(String first_name) {
        Name = first_name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artists artists = (Artists) o;  //objest turned into Artists
        return Id == artists.Id &&  Name.equals(artists.Name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Name);
    }

    @Override
    public String toString() {
        return "Artists{" +
                "Artist_id=" + Id +
                ", First_name='" + Name + '\'' +
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
