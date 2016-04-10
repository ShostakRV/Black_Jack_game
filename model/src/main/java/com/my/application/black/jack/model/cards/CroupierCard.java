package com.my.application.black.jack.model.cards;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created: Shostak Roman
 * Date: 10.04.2016.
 */
@Entity
@Table(name = "GAME_CARD")

@DiscriminatorValue(value = "croupier")
public class CroupierCard extends GameCard {
    public CroupierCard() {
//        cardType = "croupier";
        super("croupier");
    }
}
