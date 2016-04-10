package com.my.application.black.jack.server.dao;

import com.my.application.black.jack.model.cards.GameCard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
public interface GameCardRepository extends JpaRepository<GameCard, Long> {
    default GameCard newEntity() {
        return new GameCard();
    }
}