package com.my.application.black.jack.server.dao;

import com.my.application.black.jack.model.cards.GameCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Created: Shostak Roman
 * Date: 10.04.2016.
 */
@Repository
public class GameCardRepositoryImpl extends SimpleJpaRepository<GameCard, Long> implements GameCardRepository {

    @Autowired
    public GameCardRepositoryImpl( EntityManager entityManager) {
        super(GameCard.class, entityManager);
    }
}
