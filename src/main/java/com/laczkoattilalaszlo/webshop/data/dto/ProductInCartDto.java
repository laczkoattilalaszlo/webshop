package com.laczkoattilalaszlo.webshop.data.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductInCartDto {

    // Field(s)
    private UUID productId;
    private Integer quantity;
    private String name;
    private BigDecimal price;
    private String currency;
    private String picture;

    // Getter(s) and Setter(s)
    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
