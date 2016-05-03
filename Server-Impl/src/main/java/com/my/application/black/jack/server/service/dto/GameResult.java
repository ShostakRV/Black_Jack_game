package com.my.application.black.jack.server.service.dto;

import com.my.application.black.jack.model.GameState;
import lombok.Data;

/**
 * Created: Shostak Roman
 * Date: 24.04.2016.
 */
@Data
public class GameResult {
    private int userPoints = 0;
    private int croupierPoints = 0;
    private GameState gameState = GameState.ON_PROGRESS;
}
