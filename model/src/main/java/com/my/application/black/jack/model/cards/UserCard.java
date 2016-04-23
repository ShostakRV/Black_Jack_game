package com.my.application.black.jack.model.cards;

import com.my.application.black.jack.model.Card;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created: Shostak Roman
 * Date: 10.04.2016.
 */
@Entity
@Table(name = "GAME_CARD")
@DiscriminatorValue(value = "USER")
public class UserCard extends GameCard {//

    public UserCard() {
        super(CardType.USER);
    }

    public UserCard( Card card) {
        this();
        this.card = card;
    }
}
