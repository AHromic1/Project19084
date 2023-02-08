package ba.unsa.etf.rpr.domain;

import java.util.Objects;


/**
 * @author Amina Hromic
 * domain class for Artists,implementing idable
 */

public class Artists implements Idable
{
    private   int Id ;
    private   String Name;

    /**
     * constructor
     * @param name String
     */
    public Artists(String name) {
        this.Name = name;
    }

    /**
     * constructor with no parameters
     */

    public Artists() {
    }

    /**
     * getter for Exhibiton_name
     * @return Name
     */
    public String getName() {
        return Name;
    }

    /**
     * setter for Name
     * @param name String
     */

    public void setName(String name) {
        Name = name;
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
        Artists artists = (Artists) o;  //objest turned into Artists
        return Id == artists.Id &&  Name.equals(artists.Name) ;
    }

    /**
     * overriden hashCode method
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(Id, Name);
    }

    /**
     * overriden toString method
     * @return String
     */

    @Override
    public String toString() {
        return Name;
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
     * @return int
     */

    @Override
    public int getId() {
        return Id;
    }
}
