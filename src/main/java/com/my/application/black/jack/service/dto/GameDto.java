package com.my.application.black.jack.service.dto;

import java.math.BigDecimal;

/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
public class GameDto {
    private Long id;

    private BigDecimal rate;

    private String userCard1;

    private String userCard2;

    private String croupierCard1;

    private String croupierCard2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getUserCard1() {
        return userCard1;
    }

    public void setUserCard1(String userCard1) {
        this.userCard1 = userCard1;
    }

    public String getUserCard2() {
        return userCard2;
    }

    public void setUserCard2(String userCard2) {
        this.userCard2 = userCard2;
    }

    public String getCroupierCard1() {
        return croupierCard1;
    }

    public void setCroupierCard1(String croupierCard1) {
        this.croupierCard1 = croupierCard1;
    }

    public String getCroupierCard2() {
        return croupierCard2;
    }

    public void setCroupierCard2(String croupierCard2) {
        this.croupierCard2 = croupierCard2;
    }
}
