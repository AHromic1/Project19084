package ba.unsa.etf.rpr.domain;

import java.util.Objects;



/**
 * @author Amina Hromic
 * domain class for Artwork,implementing idable
 */


public class Artwork implements Idable{
    private int Id;
    private String Name;
    private String Era;
    private double Price;
    private Exhibitions exhibition;
    private Artists artist;  //artists foreign key

    /**
     * constructor
     * @param id
     * @param name
     * @param era
     * @param price
     * @param exhibition
     * @param artist
     */

    public Artwork(int id, String name, String era, double price, Exhibitions exhibition, Artists artist) {
        Id = id;
        Name = name;
        Era = era;
        Price = price;
        this.exhibition = exhibition;
        this.artist = artist;
    }

    /**
     * constructor with no parameters
     */

    public Artwork() {
    }

    /**
     * setter for Name
     * @param name String
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * setter for Era
     * @param era String
     */

    public void setEra(String era) {
        Era = era;
    }

    /**
     * setter for Price
     * @param price double
     */

    public void setPrice(double price) {
        Price = price;
    }

    /**
     * getter for Name
     * @return Name
     */
    public String getName() {
        return Name;
    }

    /**
     * getter for Era
     * @return Era
     */

    public String getEra() {
        return Era;
    }

    /**
     * getter for Price
     * @return Price
     */

    public double getPrice() {
        return Price;
    }

    /**
     * getter for Exhibition
     * @return Exhibition
     */

    public Exhibitions getExhibition() {
        return exhibition;
    }

    /**
     * setter for exhibition
     * @param exhibition Exhibitions
     */

    public void setExhibition(Exhibitions exhibition) {
        this.exhibition = exhibition;
    }

    /**
     * getter for artist
     * @return artist
     */

    public Artists getArtist() {
        return artist;
    }

    /**
     * setter for artist
     * @param artist Artists
     */

    public void setArtist(Artists artist) {
        this.artist = artist;
    }

    /**
     * overriden toString method
     * @return String
     */

    @Override
    public String toString() {
        return getName();
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
        Artwork artwork = (Artwork) o;
        return Id == artwork.Id && Double.compare(artwork.Price, Price) == 0 && Name.equals(artwork.Name) && Era.equals(artwork.Era) && exhibition.equals(artwork.exhibition) && artist.equals(artwork.artist);
    }

    /**
     * overriden hashCode method
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(Id, Name, Era, Price, exhibition, artist);
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
