package com.my.application.black.jack.service;

import com.my.application.black.jack.dao.GameRepository;
import com.my.application.black.jack.dao.UserRepository;
import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.service.converter.GameConverter;
import com.my.application.black.jack.service.dto.GameDto;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
@Service
@Transactional
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private UserRepository userRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GameDto createGameForUser(String userEmail, BigDecimal rate) {

        User user = userRepository.findByEmail(userEmail);
        Validate.notNull(user, "User has not been found");

        CardGenerator generator = applicationContext.getBean(CardGenerator.class);

        Game game = new Game();
        game.setUser(user);
        game.setRate(rate);
        game.setUserCard1(generator.nextCard());
        game.setUserCard2(generator.nextCard());
        game.setCroupierCard1(generator.nextCard());
        game.setCroupierCard2(generator.nextCard());
        game = gameRepository.saveAndFlush(game);
        return GameConverter.convert(game);
    }
}
