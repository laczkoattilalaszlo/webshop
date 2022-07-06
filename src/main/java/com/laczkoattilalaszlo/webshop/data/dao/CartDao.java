package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.ProductInCartDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CartDao {

    List<ProductInCartDto> getCart(UUID userId);

    BigDecimal getTotalPrice(UUID userId);

    Integer getQuantityOfGivenProductInCart(UUID productId, UUID userId);

    void addProductToCart(UUID productId, UUID userId);

    void removeProductFromCart(UUID productId, UUID userId);

    void updateProductQuantityInCart(Integer quantity, UUID productId, UUID userId);

}
