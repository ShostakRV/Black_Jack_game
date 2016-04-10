package com.my.application.black.jack.server.service;

import com.my.application.black.jack.model.Game;

/**
 * Created: Shostak Roman
 * Date: 10.04.2016.
 */
public interface GameCardService {
    CardGenerator createCardGenerator(Game game);
}
