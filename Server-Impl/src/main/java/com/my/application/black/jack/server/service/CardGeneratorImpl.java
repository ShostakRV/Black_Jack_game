package com.my.application.black.jack.server.service;

import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.cards.CardMask;
import com.my.application.black.jack.model.cards.CardName;
import com.my.application.black.jack.model.cards.CroupierCard;
import com.my.application.black.jack.model.cards.UserCard;
import com.my.application.black.jack.server.dao.GameCardRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Developer: Roman Shostak
 * Date: 27-Oct-15.
 */
@Component
@Scope(value = "prototype")
public class CardGeneratorImpl implements CardGenerator {
    private final Game game;
    private final List<CardDto> deck;
    private GameCardRepository gameCardRepository;
    private int cardPointer = -1;
    public CardGeneratorImpl(Game game) {
        //Collesctions.shuffle
        this.game = game;
        Set<CardDto> putOutedCards = game.getGameCards().stream().map(gameCard -> new CardDto(gameCard.getCardName(), gameCard.getCardMask())).collect(Collectors.toSet());
        this.deck = generateDeck();
        this.deck.removeAll(putOutedCards);
        Collections.shuffle(this.deck);
    }

    private CardDto nextCard() {
        return deck.get(++this.cardPointer);
    }

    @Override
    public UserCard nextUserCard() {
        CardDto nCard = nextCard();
        UserCard userCard = gameCardRepository.newUserCard();

        userCard.setCardName(nCard.getCardName());
        userCard.setCardMask(nCard.getCardMask());
        userCard.setGame(this.game);
        userCard.setSorting(this.cardPointer);
        return userCard;
    }

    @Override
    public CroupierCard nextCroupierCard() {
        CardDto nCard = nextCard();
        CroupierCard croupierCard = gameCardRepository.newCroupierCard();

        croupierCard.setCardName(nCard.getCardName());
        croupierCard.setCardMask(nCard.getCardMask());
        croupierCard.setGame(this.game);
        croupierCard.setSorting(this.cardPointer);
        return croupierCard;
    }

    @Autowired
    public void setGameCardRepository(GameCardRepository gameCardRepository) {
        this.gameCardRepository = gameCardRepository;
    }

    private List<CardDto> generateDeck() {
        List<CardDto> cardList = new ArrayList<>();
        for (CardName cardName : CardName.values()) {
            for (CardMask cardMask : CardMask.values()) {
                cardList.add(new CardDto(cardName, cardMask));
            }
        }
        return cardList;
    }

    @Data
    private class CardDto {
        CardMask cardMask;
        CardName cardName;

        CardDto(CardName cardName, CardMask cardMask) {
            this.cardName = cardName;
            this.cardMask = cardMask;
        }
    }
}
