package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.AddressDto;

import java.util.UUID;

public interface AddressDao {

    AddressDto getAddress(String tableName, UUID userId);

}
