package ba.unsa.etf.rpr.domain;




import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Amina Hromic
 * domain class for Exhibitions,implementing idable
 */

public class Exhibitions implements Idable{
    //ne mora kao u bazi
    private int Id;
    private String Exhibition_name;
    private Date Start_date;
    private Date End_date;
    private String Location;

    /**
     * constructor
     * @param id
     * @param exhibition_name
     * @param start_date
     * @param end_date
     * @param location
     */

    public Exhibitions(int id, String exhibition_name, Date start_date, Date end_date, String location) {
        Id = id;
        Exhibition_name = exhibition_name;
        Start_date = start_date;
        End_date = end_date;
        Location = location;
    }

    /**
     * constructor with no parameters
     */
    public Exhibitions() {
    }

    public Exhibitions(String name) {
        this.Exhibition_name = name;
    }


    /**
     * getter for Exhibiton_name
     * @return Exhibition_name
     */
    public String getExhibition_name() {
        return Exhibition_name;
    }

    /**
     * setter for Exhibiton_name
     * @param exhibition_name String
     */

    public void setExhibition_name(String exhibition_name) {
        Exhibition_name = exhibition_name;
    }

    /**
     * getter for Start_date
     * @return Start_date
     */
    public Date getStart_date() {
        return Start_date;
    }

    /**
     * setter for Start_date
     * @param start_date Date
     */
    public void setStart_date(Date start_date) {
        Start_date = start_date;
    }

    /**
     * getter for End_date
     * @return End_date
     */
    public Date getEnd_date() {
        return End_date;
    }

    /**
     * setter for End_date
     * @param end_date Date
     */
    public void setEnd_date(Date end_date) {
        End_date = end_date;
    }

    /**
     * getter for Location
     * @return Location
     */
    public String getLocation() {
        return Location;
    }

    /**
     * setter for Location
     * @param location String
     */
    public void setLocation(String location) {
        Location = location;
    }

    /**
     * overriden equals method
     * @param o Object
     * @return 0, <0 or >0
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exhibitions that = (Exhibitions) o;
        return Id == that.Id && Exhibition_name.equals(that.Exhibition_name) && Start_date.equals(that.Start_date) && End_date.equals(that.End_date) && Location.equals(that.Location);
    }

    /**
     * overriden hashCode method
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(Id, Exhibition_name, Start_date, End_date, Location);
    }

    /**
     * overriden toString method
     * @return String
     */
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

    /**
     * setter for Id
     * @param id int
     */
    @Override
    public void setId(int id) {
        Id = id;
    }

    /**
     * getter for Id
     * @return Id
     */

    @Override
    public int getId() {
        return Id;
    }
}
