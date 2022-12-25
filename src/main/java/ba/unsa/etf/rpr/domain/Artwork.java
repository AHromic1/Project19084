package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Artwork implements Idable{
    private int Id;
    private String Name;
    private String Type;
    private String Era;
    private double Price;
    private int Exhibition_FK;

    private int Art_FK;


    public void setName(String name) {
        Name = name;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setEra(String era) {
        Era = era;
    }

    public int getArt_FK() {
        return Art_FK;
    }

    public void setArt_FK(int art_FK) {
        Art_FK = art_FK;
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

    public String getType() {
        return Type;
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
                ", Type='" + Type + '\'' +
                ", Era='" + Era + '\'' +
                ", Price=" + Price +
                ", Exhibition_FK=" + Exhibition_FK +
                ", Art_FK=" + Art_FK +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artwork artwork = (Artwork) o;
        return Id == artwork.Id && Double.compare(artwork.Price, Price) == 0 && Exhibition_FK == artwork.Exhibition_FK && Art_FK == artwork.Art_FK && Name.equals(artwork.Name) && Type.equals(artwork.Type) && Era.equals(artwork.Era);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Name, Type, Era, Price, Exhibition_FK, Art_FK);
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
