package com.my.application.black.jack.server.service;

import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.server.dao.GameRepository;
import com.my.application.black.jack.server.dao.UserRepository;
import com.my.application.black.jack.server.exception.GameException;
import com.my.application.black.jack.server.service.converter.GameConverter;
import com.my.application.black.jack.server.service.dto.GameDto;
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
    private ApplicationContext applicationContext;
    private GameConverter gameConverter;
    private AmountService amountService;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserRepository userRepository,
                           GameConverter gameConverter, AmountService amountService,
                           ApplicationContext applicationContext) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.gameConverter = gameConverter;
        this.amountService = amountService;
        this.applicationContext = applicationContext;
    }

    @Override
    public GameDto createGameForUser(final String userEmail, final BigDecimal rate) {

        User user = userRepository.findByEmail(userEmail);
        Validate.notNull(user, "User has not been found");

        CardGenerator generator = applicationContext.getBean(CardGenerator.class);
        if (user.getAmount().compareTo(rate) < 0) {
            throw new GameException("Not enough money!");
        } else if (BigDecimal.ZERO.compareTo(rate) > 0) {
            throw new GameException("Rate should be more then 0 !");
        }
        Game game = gameRepository.newEntity();
        game.setUser(user);
        game.setRate(rate);
//        game.setUserCard1(generator.nextCard());
//        game.setUserCard2(generator.nextCard());
//        game.setCroupierCard1(generator.nextCard());
//        game.setCroupierCard2(generator.nextCard());
        game = gameRepository.saveAndFlush(game);
        amountService.withdrawForNewGame(game);
        return gameConverter.convert(game);
    }

    public GameDto hitCard(long gameId) {
        return null;
    }
}
