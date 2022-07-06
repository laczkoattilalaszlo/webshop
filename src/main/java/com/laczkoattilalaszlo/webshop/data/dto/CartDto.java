package com.laczkoattilalaszlo.webshop.data.dto;

import java.util.UUID;

public class CartDto {

    // Field(s)
    private UUID productId;
    private UUID userId;

    // Getter(s) and Setter(s)
    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
