package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.CartDao;
import com.laczkoattilalaszlo.webshop.data.dto.ProductInCartDto;

import java.util.List;
import java.util.UUID;

public class CartService {

    // Field(s)
    private CartDao cartDao;

    // Constructor(s)
    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    // Method(s)

    public List<ProductInCartDto> getCart(UUID userId) {
        return cartDao.getCart(userId);
    }

    public void addProductToCart(UUID productId, UUID userId) {
        Integer quantityOfGivenProductInCart = cartDao.getQuantityOfGivenProductInCart(productId, userId);
        if (quantityOfGivenProductInCart == null) {
            cartDao.addProductToCart(productId, userId);
        } else {
            Integer increasedQuantity = quantityOfGivenProductInCart + 1;
            cartDao.updateProductQuantityInCart(increasedQuantity, productId, userId);
        }
    }

    public void removeProductFromCart(UUID productId, UUID userId) {
        Integer quantityOfGivenProductInCart = cartDao.getQuantityOfGivenProductInCart(productId, userId);
        if (quantityOfGivenProductInCart.equals(1)) {
            cartDao.removeProductFromCart(productId, userId);
        } else if (quantityOfGivenProductInCart.intValue() > 1) {
            Integer decreasedQuantity = quantityOfGivenProductInCart - 1;
            cartDao.updateProductQuantityInCart(decreasedQuantity, productId, userId);
        }
    }

}
