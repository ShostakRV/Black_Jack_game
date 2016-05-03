package com.my.application.black.jack.server.service.dto;

import lombok.Data;

/**
 * Created by Pavlenko Bohdan
 * Date: 03.05.2016.
 */
@Data
public class CardDto {
    String cardMask;
    String cardName;

    public CardDto(String cardName, String cardMask) {
        this.cardMask = cardMask;
        this.cardName = cardName;
    }
}
