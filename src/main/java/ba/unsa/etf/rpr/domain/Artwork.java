package ba.unsa.etf.rpr.domain;

import java.util.Objects;
import ba.unsa.etf.rpr.domain.Exhibitions;

public class Artwork implements Idable{
    private int Id;
    private String Name;
    private String Era;
    private double Price;
    private Exhibitions exhibition;
    private Artists artist;  //artists foreign key


    public void setName(String name) {
        Name = name;
    }

    public void setEra(String era) {
        Era = era;
    }

    public void setPrice(double price) {
        Price = price;
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

    @Override
    public String toString() {
        return getName();
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
        return Objects.hash(Id, Name, Era, Price);
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
