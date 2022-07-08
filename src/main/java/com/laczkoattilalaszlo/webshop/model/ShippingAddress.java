package com.laczkoattilalaszlo.webshop.model;

import java.util.UUID;

public class ShippingAddress {

    // Field(s)
    private String zip;
    private String country;
    private String city;
    private String address;
    private UUID userId;

    // Getter(s) and Setter(s)
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

}
