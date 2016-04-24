package com.my.application.black.jack.server.service;

import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.cards.GameCard;

import java.util.List;

/**
 * Created: Shostak Roman
 * Date: 10.04.2016.
 */
public interface GameCardService {
    CardGenerator createCardGenerator(Game game);


}
