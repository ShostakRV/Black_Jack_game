package com.my.application.black.jack.server.service;

import com.my.application.black.jack.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created: Shostak Roman
 * Date: 10.04.2016.
 */
@Service
public class GameCardServiceImpl implements GameCardService {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public CardGenerator createCardGenerator(Game game) {
        return applicationContext.getBean(CardGenerator.class, game);
    }

}
