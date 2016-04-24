package com.my.application.black.jack.server.service.dto;

import com.my.application.black.jack.model.GameState;
import lombok.Getter;
import lombok.Setter;

/**
 * Created: Shostak Roman
 * Date: 24.04.2016.
 */
@Getter
@Setter
public class GameResult {
    private int userPoints = 0;
    private int croupierPoints = 0;
    private GameState gameState = GameState.ON_PROGRESS;
}
