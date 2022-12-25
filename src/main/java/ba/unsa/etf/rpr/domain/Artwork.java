package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Artwork implements Idable{
    private int Id;
    private String Name;
    private String Era;
    private double Price;
    private int Exhibition_FK;
    private int Artists_FK;  //artists foreign key


    public void setName(String name) {
        Name = name;
    }

    public void setEra(String era) {
        Era = era;
    }

    public int getArtists_FK() {
        return Artists_FK;
    }

    public void setArtists_FK(int art_FK) {
        Artists_FK = art_FK;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setExhibition_FK(int exhibition_FK) {
        Exhibition_FK = exhibition_FK;
    }

    public String getName() {
        return Name;
    }

    public String getEra() {
        return Era;
    }


    public double getPrice() {
        return Price;
    }

    public int getExhibition_FK() {
        return Exhibition_FK;
    }
    @Override
    public String toString() {
        return "Artwork{" +
                "Artwork_id=" + Id +
                ", Name='" + Name + '\'' +
                ", Era='" + Era + '\'' +
                ", Price=" + Price +
                ", Exhibition_FK=" + Exhibition_FK +
                ", Artists_FK=" + Artists_FK +
                "}";  //zasto ' '
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artwork artwork = (Artwork) o;
        return Id == artwork.Id && Double.compare(artwork.Price, Price) == 0 && Exhibition_FK == artwork.Exhibition_FK && Artists_FK == artwork.Artists_FK && Name.equals(artwork.Name) && Era.equals(artwork.Era);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Name, Era, Price, Exhibition_FK, Artists_FK);
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
