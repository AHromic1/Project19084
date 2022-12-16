package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Artwork {
    private int Artwork_id;
    private String Name;
    private String Type;
    private String Era;
    private String Style;
    private double Price;
    private int Customer_FK;
    private int Exhibition_FK;

    public int getArtwork_id() {
        return Artwork_id;
    }


    public void setArtwork_id(int artwork_id) {
        Artwork_id = artwork_id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setEra(String era) {
        Era = era;
    }

    public void setStyle(String style) {
        Style = style;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setCustomer_FK(int customer_FK) {
        Customer_FK = customer_FK;
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

    public String getStyle() {
        return Style;
    }

    public double getPrice() {
        return Price;
    }

    public int getCustomer_FK() {
        return Customer_FK;
    }

    public int getExhibition_FK() {
        return Exhibition_FK;
    }
    @Override
    public String toString() {
        return "Artwork{" +
                "Artwork_id=" + Artwork_id +
                ", Name='" + Name + '\'' +
                ", Type='" + Type + '\'' +
                ", Era='" + Era + '\'' +
                ", Style='" + Style + '\'' +
                ", Price=" + Price +
                ", Customer_FK=" + Customer_FK +
                ", Exhibition_FK=" + Exhibition_FK +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artwork artwork = (Artwork) o;
        return Artwork_id == artwork.Artwork_id && Double.compare(artwork.Price, Price) == 0 && Customer_FK == artwork.Customer_FK && Exhibition_FK == artwork.Exhibition_FK && Name.equals(artwork.Name) && Type.equals(artwork.Type) && Era.equals(artwork.Era) && Style.equals(artwork.Style);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Artwork_id, Name, Type, Era, Style, Price, Customer_FK, Exhibition_FK);
    }

}
