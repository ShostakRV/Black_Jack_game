package com.my.application.black.jack.model.cards;

import com.my.application.black.jack.model.AbstractEntity;
import com.my.application.black.jack.model.Game;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


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

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class GameCard extends AbstractEntity {
    @NotNull
    @Column(name = "CARD_TYPE", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    protected final CardType cardType;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "CARD_MASK")
    protected CardMask cardMask;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "CARD_HEIGHT")
    protected CardName cardName;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "FK_GAME")
    private Game game;
    @NotNull
    @Column(name = "SORTING")
    private Integer sorting;

    GameCard(CardType cardType) {
        this.cardType = cardType;
    }

    GameCard(CardType cardType, CardName cardName, CardMask cardMask) {
        this(cardType);
        this.cardMask = cardMask;
        this.cardName = cardName;
    }


    @Override
    public String toString() {
        return "GameCard{" +
                "cardType=" + cardType +
                ", game=" + game +
                ", cardMask=" + cardMask +
                ", cardHeight=" + cardName +
                ", sorting=" + sorting +
                '}';
    }
}
