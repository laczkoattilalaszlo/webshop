package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.BankCardDto;

import java.util.UUID;

public interface BankCardDao {

    BankCardDto getBankCard(UUID userId);

}
