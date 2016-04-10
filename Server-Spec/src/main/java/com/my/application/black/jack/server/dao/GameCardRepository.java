package com.my.application.black.jack.server.dao;

import com.my.application.black.jack.model.cards.CroupierCard;
import com.my.application.black.jack.model.cards.GameCard;
import com.my.application.black.jack.model.cards.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
public interface GameCardRepository extends JpaRepository<GameCard, Long> {
    default UserCard newUserCard() {
        return new UserCard();
    }

    default CroupierCard newCroupierCard(){
        return new CroupierCard();
    };
}