package com.my.application.black.jack.server.service;

import com.my.application.black.jack.model.Card;
import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.cards.CroupierCard;
import com.my.application.black.jack.model.cards.UserCard;
import com.my.application.black.jack.server.dao.GameCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Developer: Roman Shostak
 * Date: 27-Oct-15.
 */
@Component
@Scope(value = "prototype")
public class CardGeneratorImpl implements CardGenerator {


    private final Set<Integer> putOutedCards;
    private final Game game;
    private GameCardRepository gameCardRepository;

    public CardGeneratorImpl(Game game) {
        //Collesctions.shuffle
        this.putOutedCards = game.getGameCards().stream().map(gameCard -> gameCard.getCard().ordinal()).collect(Collectors.toSet());
        this.game = game;
    }


    private Card nextCard() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        Integer randomCard;
        do {
            randomCard = threadLocalRandom.nextInt(0, 51 + 1);
        } while (putOutedCards.contains(randomCard));
        putOutedCards.add(randomCard);
        return Card.getByOrdinal(randomCard);
    }

    @Override
    public UserCard nextUserCard() {
        UserCard userCard = gameCardRepository.newUserCard();
        userCard.setCard(nextCard());
        userCard.setGame(game);
        userCard.setSorting(putOutedCards.size());
        return userCard;
    }


    @Override
    public CroupierCard nextCroupierCard() {
        CroupierCard croupierCard = gameCardRepository.newCroupierCard();
        croupierCard.setCard(nextCard());
        croupierCard.setGame(game);
        croupierCard.setSorting(putOutedCards.size());

        return croupierCard;
    }

    @Autowired
    public void setGameCardRepository(GameCardRepository gameCardRepository) {
        this.gameCardRepository = gameCardRepository;
    }
}
