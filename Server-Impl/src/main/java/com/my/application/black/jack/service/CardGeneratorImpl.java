package com.my.application.black.jack.service;

import com.my.application.black.jack.model.Card;
import com.my.application.black.jack.service.CardGenerator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Developer: Roman Shostak
 * Date: 27-Oct-15.
 */
@Component//(value = "cardGenerator")
@Scope(value = "prototype")
public class CardGeneratorImpl implements CardGenerator {

    private final Set<Integer> putOutedCards;





    public CardGeneratorImpl() {
        putOutedCards = new HashSet<>();
    }

    public CardGeneratorImpl(Collection<Integer> existingCards) {
        putOutedCards = new HashSet<>(existingCards == null ? Collections.emptySet() : existingCards);
    }

    @Override
    public Card nextCard() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        Integer randomCard;
        do {
            randomCard = threadLocalRandom.nextInt(0, 51 + 1);
        } while (putOutedCards.contains(randomCard));
        putOutedCards.add(randomCard);
        return Card.getByOrdinal(randomCard);
    }
}
