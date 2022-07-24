package com.laczkoattilalaszlo.webshop.data.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PaidOrderDto {

    // Field(s)
    UUID id;
    private LocalDateTime successfulPaymentStartTimestamp;

    // Getter(s) and Setter(s)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getSuccessfulPaymentStartTimestamp() {
        return successfulPaymentStartTimestamp;
    }

    public void setSuccessfulPaymentStartTimestamp(LocalDateTime successfulPaymentStartTimestamp) {
        this.successfulPaymentStartTimestamp = successfulPaymentStartTimestamp;
    }
}
