package com.my.application.black.jack.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */
@Entity
@Table(name = "GAME")
public class Game extends AbstractEntity {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "FK_USER")
    private User user;
    @NotNull
    @Column(name = "START_ON")
    private LocalDateTime start;

    @Column(name = "FINISHED_ON")
    private LocalDateTime finish;
    @NotNull
    @Column(name = "RATE")
    private BigDecimal rate;
    @NotNull
    @Column(name = "USER_CARD_1", updatable = false)
    @Enumerated(EnumType.STRING)
    private Card userCard1;
    @NotNull
    @Column(name = "USER_CARD_2", updatable = false)
    @Enumerated(EnumType.STRING)
    private Card userCard2;
    @NotNull
    @Column(name = "CROUPIER_CARD_1",updatable = false)
    @Enumerated(EnumType.STRING)
    private Card croupierCard1;
    @NotNull
    @Column(name = "CROUPIER_CARD_2", updatable = false)
    @Enumerated(EnumType.STRING)
    private Card croupierCard2;

    public Game() {
        this.start = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Card getUserCard1() {
        return userCard1;
    }

    public void setUserCard1(Card userCard1) {
        this.userCard1 = userCard1;
    }

    public Card getUserCard2() {
        return userCard2;
    }

    public void setUserCard2(Card userCard2) {
        this.userCard2 = userCard2;
    }

    public Card getCroupierCard1() {
        return croupierCard1;
    }

    public void setCroupierCard1(Card croupierCard1) {
        this.croupierCard1 = croupierCard1;
    }

    public Card getCroupierCard2() {
        return croupierCard2;
    }

    public void setCroupierCard2(Card croupierCard2) {
        this.croupierCard2 = croupierCard2;
    }
}
