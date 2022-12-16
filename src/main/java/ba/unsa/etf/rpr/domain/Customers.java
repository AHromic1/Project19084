package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Customers {
    private int Customer_id;
    private String First_name;
    private String Last_name;
    private String Phone_number;
    private String Address;
    private int Art_FK;

    public int getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(int customer_id) {
        Customer_id = customer_id;
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

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
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
        Customers customers = (Customers) o;
        return Customer_id == customers.Customer_id && Art_FK == customers.Art_FK && First_name.equals(customers.First_name) && Last_name.equals(customers.Last_name) && Phone_number.equals(customers.Phone_number) && Address.equals(customers.Address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Customer_id, First_name, Last_name, Phone_number, Address, Art_FK);
    }

    @Override
    public String toString() {
        return "Customers{" +
                "Customer_id=" + Customer_id +
                ", First_name='" + First_name + '\'' +
                ", Last_name='" + Last_name + '\'' +
                ", Phone_number='" + Phone_number + '\'' +
                ", Address='" + Address + '\'' +
                ", Art_FK=" + Art_FK +
                '}';
    }
}
