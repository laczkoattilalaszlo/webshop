package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.AddressDto;

import java.util.UUID;

public interface AddressDao {

    AddressDto getAddress(String tableName, UUID userId);

    void updateAddress(String tableName, UUID userId, AddressDto addressDto);

}
