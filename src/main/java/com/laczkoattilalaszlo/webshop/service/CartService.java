package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.CartDao;
import com.laczkoattilalaszlo.webshop.data.dao.UserDao;
import com.laczkoattilalaszlo.webshop.data.dto.ProductInCartDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CartService {

    // Field(s)
    private CartDao cartDao;
    private UserDao userDao;

    // Constructor(s)
    public CartService(CartDao cartDao, UserDao userDao) {
        this.cartDao = cartDao;
        this.userDao = userDao;
    }

    // Method(s)
    public List<ProductInCartDto> getCart(UUID userId) {
        return cartDao.getCart(userId);
    }

    public BigDecimal getTotalPrice(UUID userId) {
        return cartDao.getTotalPrice(userId);
    }

    public void addProductToCart(UUID productId, String sessionToken) {
        UUID userId = userDao.getUserIdBySessionToken(sessionToken);

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
