package com.my.application.black.jack.model.cards;

import com.my.application.black.jack.model.AbstractEntity;
import com.my.application.black.jack.model.Card;
import com.my.application.black.jack.model.Game;

import javax.persistence.*;


/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */
@Entity
@Table(name = "GAME_CARD")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "CARD_TYPE",
        discriminatorType = DiscriminatorType.STRING
)

public class GameCard extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "FK_GAME")
    private Game game;

    @Enumerated(EnumType.STRING)
    @Column(name = "CARD")
    private Card card;
    @Column(name = "order")
    private Integer order;
    @Column(name = "CARD_TYPE", insertable = false, updatable = false)
    protected final String cardType;

    protected GameCard(String cardType) {
        this.cardType = cardType;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Transient
    public boolean isUserCard() {
        return this instanceof UserCard;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getCardType() {
        return cardType;
    }
}
