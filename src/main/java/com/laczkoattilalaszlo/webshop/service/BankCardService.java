package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.BankCardDao;
import com.laczkoattilalaszlo.webshop.data.dto.BankCardDto;

import java.util.UUID;

public class BankCardService {

    // Field(s)
    private BankCardDao bankCardDao;

    // Constructor(s)
    public BankCardService(BankCardDao bankCardDao) {
        this.bankCardDao = bankCardDao;
    }

    // Method(s)
    public BankCardDto getBankCard(UUID userId) {
        return bankCardDao.getBankCard(userId);
    }

}
