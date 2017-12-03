package ua.artcode.billapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by serhii on 03.12.17.
 */
@Entity
public class Address extends IdEntity {

    @Column
    private String city;
    @Column
    private String street;
    @Column
    private String number;

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
