package com.my.application.black.jack.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */
public enum Card {
    SPADES_2, CLUBS_2, HEARTS_2, DIAMONDS_2,
    SPADES_3, CLUBS_3, HEARTS_3, DIAMONDS_3,
    SPADES_4, CLUBS_4, HEARTS_4, DIAMONDS_4,
    SPADES_5, CLUBS_5, HEARTS_5, DIAMONDS_5,
    SPADES_6, CLUBS_6, HEARTS_6, DIAMONDS_6,
    SPADES_7, CLUBS_7, HEARTS_7, DIAMONDS_7,
    SPADES_8, CLUBS_8, HEARTS_8, DIAMONDS_8,
    SPADES_9, CLUBS_9, HEARTS_9, DIAMONDS_9,
    SPADES_10, CLUBS_10, HEARTS_10, DIAMONDS_10,
    SPADES_JACK, CLUBS_JACK, HEARTS_JACK, DIAMONDS_JACK,
    SPADES_QUEEN, CLUBS_QUEEN, HEARTS_QUEEN, DIAMONDS_QUEEN,
    SPADES_KING, CLUBS_KING, HEARTS_KING, DIAMONDS_KING,
    SPADES_ACE, CLUBS_ACE, HEARTS_ACE, DIAMONDS_ACE;

    private static final Map<Integer, Card> cache;

    static {
        Map<Integer, Card> tmpCache = new HashMap<>();
        for (Card c : Card.values()) {
            tmpCache.put(c.ordinal(), c);
        }
        cache = Collections.unmodifiableMap(tmpCache);
    }

    public static Card getByOrdinal(Integer randomBall) {
        return cache.get(randomBall);
    }
}
