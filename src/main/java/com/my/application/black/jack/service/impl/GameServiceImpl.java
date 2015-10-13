package com.my.application.black.jack.service.impl;

import com.my.application.black.jack.dao.GameRepository;
import com.my.application.black.jack.dao.UserRepository;
import com.my.application.black.jack.model.Card;
import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.service.GameService;
import com.my.application.black.jack.service.converter.GameConverter;
import com.my.application.black.jack.service.dto.GameDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
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

    @Inject
    public GameServiceImpl(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GameDto createGameForUser(String userEmail, BigDecimal rate) {
        User user = userRepository.findByEmail(userEmail);
        Game game = new Game();
        game.setUser(user);
        game.setRate(rate);
        game.setUserCard1(Card.CLUBS_2);
        game.setUserCard2(Card.DIAMONDS_2);
        game.setCroupierCard1(Card.CLUBS_10);
        game.setCroupierCard2(Card.DIAMONDS_ACE);
        game = gameRepository.saveAndFlush(game);
        return GameConverter.convert(game);
    }
}
