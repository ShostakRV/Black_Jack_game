package com.my.application.black.jack.model;

import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */
public enum Card {
    SPADES_2(2, false), CLUBS_2(2, false), HEARTS_2(2, false), DIAMONDS_2(2, false),
    SPADES_3(3, false), CLUBS_3(3, false), HEARTS_3(3, false), DIAMONDS_3(3, false),
    SPADES_4(4, false), CLUBS_4(4, false), HEARTS_4(4, false), DIAMONDS_4(4, false),
    SPADES_5(5, false), CLUBS_5(5, false), HEARTS_5(5, false), DIAMONDS_5(5, false),
    SPADES_6(6, false), CLUBS_6(6, false), HEARTS_6(6, false), DIAMONDS_6(6, false),
    SPADES_7(7, false), CLUBS_7(7, false), HEARTS_7(7, false), DIAMONDS_7(7, false),
    SPADES_8(8, false), CLUBS_8(8, false), HEARTS_8(8, false), DIAMONDS_8(8, false),
    SPADES_9(9, false), CLUBS_9(9, false), HEARTS_9(9, false), DIAMONDS_9(9, false),
    SPADES_10(10, false), CLUBS_10(10, false), HEARTS_10(10, false), DIAMONDS_10(10, false),
    SPADES_JACK(10, false), CLUBS_JACK(10, false), HEARTS_JACK(10, false), DIAMONDS_JACK(10, false),
    SPADES_QUEEN(10, false), CLUBS_QUEEN(10, false), HEARTS_QUEEN(10, false), DIAMONDS_QUEEN(10, false),
    SPADES_KING(10, false), CLUBS_KING(10, false), HEARTS_KING(10, false), DIAMONDS_KING(10, false),
    SPADES_ACE(11, true), CLUBS_ACE(11, true), HEARTS_ACE(11, true), DIAMONDS_ACE(11, true);

    private static final Map<Integer, Card> cache;


    static {
        Map<Integer, Card> tmpCache = new HashMap<>();
        for (Card c : Card.values()) {
            tmpCache.put(c.ordinal(), c);
        }
        cache = Collections.unmodifiableMap(tmpCache);
    }

    @Getter
    private final int value;
    @Getter
    private final boolean ace;


    Card(int value, boolean ace) {
        this.value = value;
        this.ace = ace;
    }

    public static Card getByOrdinal(Integer randomBall) {
        return cache.get(randomBall);
    }

    public int add(Card card) {
        return this.getValue() + card.getValue();
    }
}
