package com.my.application.black.jack.server.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
@Data
public class GameDto {
    private Long id;

    private BigDecimal rate;

    private String gameStatus;

    private List<String> userCards = new ArrayList<>();

    private List<String> croupierCard = new ArrayList<>();

}
