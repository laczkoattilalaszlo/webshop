package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.AddressDao;
import com.laczkoattilalaszlo.webshop.data.dto.AddressDto;

import java.util.UUID;

public class AddressService {

    // Field(s)
    private AddressDao addressDao;

    // Constructor(s)
    public AddressService(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    // Method(s)
    public AddressDto getAddress(String tableName, UUID userId){
        return addressDao.getAddress(tableName,userId);
    };

}
