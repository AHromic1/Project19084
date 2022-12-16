package ba.unsa.etf.rpr.domain;
//import ba.unsa.etf.rpr.domain.Artists;
//zasto import ovaj?

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Artists //implements ArtistsDao
{
    private int Artist_id;
    private String First_name;
    private String Last_name;
    private String Birthplace;
    private Date Date_of_birth;
    private int Art_FK;  //da li je i ovo potrebno ?


    public int getArtist_id() {
        return Artist_id;
    }

    public void setArtist_id(int artist_id) {
        Artist_id = artist_id;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public String getBirthplace() {
        return Birthplace;
    }

    public void setBirthplace(String birthplace) {
        Birthplace = birthplace;
    }

    public Date getDate_of_birth() {
        return Date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        Date_of_birth = date_of_birth;
    }

    public int getArt_FK() {
        return Art_FK;
    }

    public void setArt_FK(int art_FK) {
        Art_FK = art_FK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artists artists = (Artists) o;  //objest turned into Artists
        return Artist_id == artists.Artist_id && Art_FK == artists.Art_FK && First_name.equals(artists.First_name) &&
                Last_name.equals(artists.Last_name) && Birthplace.equals(artists.Birthplace) && Date_of_birth.equals(artists.Date_of_birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Artist_id, First_name, Last_name, Birthplace, Date_of_birth, Art_FK);
    }

    @Override
    public String toString() {
        return "Artists{" +
                "Artist_id=" + Artist_id +
                ", First_name='" + First_name + '\'' +
                ", Last_name='" + Last_name + '\'' +
                ", Birthplace='" + Birthplace + '\'' +
                ", Date_of_birth=" + Date_of_birth +
                ", Art_FK=" + Art_FK +
                '}';
    }
}
