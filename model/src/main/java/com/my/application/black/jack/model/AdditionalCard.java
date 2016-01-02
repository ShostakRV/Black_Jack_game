package com.my.application.black.jack.model;

import javax.persistence.*;

/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */
@Entity
@Table(name = "ADDITIONAL_CARD")
public class AdditionalCard extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "FK_GAME")
    private Game game;
    @Column(name = "USER_CARD")
    private Boolean userCard;
    @Column(name = "CARD")
    private Card card;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Boolean getUserCard() {
        return userCard;
    }

    public void setUserCard(Boolean userCard) {
        this.userCard = userCard;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
