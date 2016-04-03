package com.my.application.black.jack.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */
@Entity
@Table(name = "AMOUNT_HISTORY")
public class AmountHistory extends AbstractEntity {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "FK_USER")
    private User user;
    @NotNull
    @Column(name = "BEFORE")
    private BigDecimal before;
    @NotNull
    @Column(name = "AFTER")
    private BigDecimal after;
    @NotNull
    @Column(name = "SOURCE")
    @Enumerated(EnumType.STRING)
    private AmountSource source;

    @ManyToOne
    @JoinColumn(name = "FK_GAME")
    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getBefore() {
        return before;
    }

    public void setBefore(BigDecimal before) {
        this.before = before;
    }

    public BigDecimal getAfter() {
        return after;
    }

    public void setAfter(BigDecimal after) {
        this.after = after;
    }

    public AmountSource getSource() {
        return source;
    }

    public void setSource(AmountSource source) {
        this.source = source;
    }
}
