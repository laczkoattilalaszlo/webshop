package com.laczkoattilalaszlo.webshop.data.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderPaymentDto {

    // Field(s)
    private String payment_id;
    private String payment_state;
    private LocalDateTime startTimestamp;

    // Getter(s) and Setter(s)
    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getPayment_state() {
        return payment_state;
    }

    public void setPayment_state(String payment_state) {
        this.payment_state = payment_state;
    }

    public LocalDateTime getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(LocalDateTime startTimestamp) {
        this.startTimestamp = startTimestamp;
    }
}
