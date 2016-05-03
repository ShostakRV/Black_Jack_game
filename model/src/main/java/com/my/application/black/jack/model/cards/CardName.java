package com.my.application.black.jack.model.cards;

import lombok.Getter;

/**
 * Created by Pavlenko Bohdan
 * Date: 03.05.2016.
 */
public enum CardName {
    _2(2), _3(3), _4(4), _5(5), _6(6), _7(7), _8(8), _9(9), _10(10), JACK(10), QUEEN(10), KING(10), ACE(11);
    @Getter
    private int value;

    CardName(int value) {
        this.value = value;
    }
}
