package com.laczkoattilalaszlo.webshop.data.dto;

public class AddressDto {

    // Field(s)
    private String zip;
    private String country;
    private String city;
    private String address;

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

}
