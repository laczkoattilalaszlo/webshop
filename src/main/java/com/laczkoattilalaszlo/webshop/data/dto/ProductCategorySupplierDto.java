package com.laczkoattilalaszlo.webshop.data.dto;

import java.util.UUID;

public class ProductCategorySupplierDto {

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
        return "ProductCategorySupplierDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
