package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.CartDao;

import java.util.UUID;

public class CartService {

    // Field(s)
    private CartDao cartDao;

    // Constructor(s)
    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    // Method(s)
    public void addProductToCart(UUID productId, UUID userId) {
        Integer quantityOfGivenProductInCart = cartDao.getQuantityOfGivenProductInCart(productId, userId);
        if (quantityOfGivenProductInCart == null) {
            cartDao.addProductToCart(productId, userId);
        } else if (quantityOfGivenProductInCart != null) {
            Integer increasedQuantity = quantityOfGivenProductInCart + 1;
            cartDao.updateProductQuantityInCart(increasedQuantity, productId, userId);
        }
    }

}
