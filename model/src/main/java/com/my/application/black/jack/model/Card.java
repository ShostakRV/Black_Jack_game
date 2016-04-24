package com.my.application.black.jack.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */
public enum Card {
    SPADES_2(2), CLUBS_2(2), HEARTS_2(2), DIAMONDS_2(2),
    SPADES_3(3), CLUBS_3(3), HEARTS_3(3), DIAMONDS_3(3),
    SPADES_4(4), CLUBS_4(4), HEARTS_4(4), DIAMONDS_4(4),
    SPADES_5(5), CLUBS_5(5), HEARTS_5(5), DIAMONDS_5(5),
    SPADES_6(6), CLUBS_6(6), HEARTS_6(6), DIAMONDS_6(6),
    SPADES_7(7), CLUBS_7(7), HEARTS_7(7), DIAMONDS_7(7),
    SPADES_8(8), CLUBS_8(8), HEARTS_8(8), DIAMONDS_8(8),
    SPADES_9(9), CLUBS_9(9), HEARTS_9(9), DIAMONDS_9(9),
    SPADES_10(10), CLUBS_10(10), HEARTS_10(10), DIAMONDS_10(10),
    SPADES_JACK(10), CLUBS_JACK(10), HEARTS_JACK(10), DIAMONDS_JACK(10),
    SPADES_QUEEN(10), CLUBS_QUEEN(10), HEARTS_QUEEN(10), DIAMONDS_QUEEN(10),
    SPADES_KING(10), CLUBS_KING(10), HEARTS_KING(10), DIAMONDS_KING(10),
    SPADES_ACE(11), CLUBS_ACE(11), HEARTS_ACE(11), DIAMONDS_ACE(11);

    private static final Map<Integer, Card> cache;

    static {
        Map<Integer, Card> tmpCache = new HashMap<>();
        for (Card c : Card.values()) {
            tmpCache.put(c.ordinal(), c);
        }
        cache = Collections.unmodifiableMap(tmpCache);
    }

    private final int value;


    Card(int value) {
        this.value = value;
    }

    public static Card getByOrdinal(Integer randomBall) {
        return cache.get(randomBall);
    }

    public int getValue() {
        return value;
    }
}
