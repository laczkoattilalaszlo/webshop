package com.laczkoattilalaszlo.webshop.model;

import java.util.UUID;

public class ProductSupplier {

    // Field(s)
    protected UUID id;
    protected String name;

    // Getter(s) and Setter(s)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Overridden method(s)
    @Override
    public String toString() {
        return "ProductSupplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
