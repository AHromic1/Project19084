package ba.unsa.etf.rpr.domain;


import java.sql.Date;
import java.util.Objects;


public class Exhibitions implements Idable{
    private int Id;
    private String Exhibition_name;
    private Date Start_date;
    private Date End_date;
    private String Location;



    public String getExhibition_name() {
        return Exhibition_name;
    }

    public void setExhibition_name(String exhibition_name) {
        Exhibition_name = exhibition_name;
    }

    public Date getStart_date() {
        return Start_date;
    }

    public void setStart_date(Date start_date) {
        Start_date = start_date;
    }

    public Date getEnd_date() {
        return End_date;
    }

    public void setEnd_date(Date end_date) {
        End_date = end_date;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exhibitions that = (Exhibitions) o;
        return Id == that.Id && Exhibition_name.equals(that.Exhibition_name) && Start_date.equals(that.Start_date) && End_date.equals(that.End_date) && Location.equals(that.Location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Exhibition_name, Start_date, End_date, Location);
    }

    @Override
    public String toString() {
        return "Exhibitions{" +
                "Exhibition_id=" + Id +
                ", Exhibition_name='" + Exhibition_name + '\'' +
                ", Start_date=" + Start_date +
                ", End_date=" + End_date +
                ", Location='" + Location + '\'' +
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
