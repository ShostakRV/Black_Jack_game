package com.my.application.black.jack.service.dto;

import java.math.BigDecimal;

/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
public class UserDto {
    private Long id;
    private String email;
    private BigDecimal amount = BigDecimal.ZERO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
