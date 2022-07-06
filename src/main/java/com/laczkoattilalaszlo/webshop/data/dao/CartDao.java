package com.laczkoattilalaszlo.webshop.data.dao;

import java.util.UUID;

public interface CartDao {

    Integer getQuantityOfGivenProductInCart(UUID productId, UUID userId);

    void addProductToCart(UUID productId, UUID userId);

    void removeProductFromCart(UUID productId, UUID userId);

    void updateProductQuantityInCart(Integer quantity, UUID productId, UUID userId);

}
