package com.my.application.black.jack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */
@Entity
@Table(name = "USER")
public class User extends AbstractEntity {
    @NotNull
    @Column(name = "EMAIL", unique = true)
    private String email;
    @NotNull
    @Column(name = "PASSWORD")
    private String password;
    @NotNull
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        amount = BigDecimal.ZERO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
