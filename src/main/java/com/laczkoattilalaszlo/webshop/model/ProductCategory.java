package com.laczkoattilalaszlo.webshop.model;

public class ProductCategory {

    // Field(s)
    protected int id;
    protected String name;

    // Getter(s) and Setter(s)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return "ProductCategoryController{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
