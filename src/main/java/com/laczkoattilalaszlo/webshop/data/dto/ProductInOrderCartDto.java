package com.laczkoattilalaszlo.webshop.data.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductInOrderCartDto {

    // Field(s)
    private UUID productId;
    private String name;
    private BigDecimal unitPrice;
    private String currency;
    private Integer quantity;

    // Getter(s) and Setter(s)
    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
