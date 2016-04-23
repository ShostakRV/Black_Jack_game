package com.my.application.black.jack.server.service;

import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.GameState;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.model.cards.CroupierCard;
import com.my.application.black.jack.model.cards.UserCard;
import com.my.application.black.jack.server.dao.GameRepository;
import com.my.application.black.jack.server.dao.UserRepository;
import com.my.application.black.jack.server.exception.GameException;
import com.my.application.black.jack.server.service.converter.GameConverter;
import com.my.application.black.jack.server.service.dto.GameDto;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
@Service
@Transactional
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private UserRepository userRepository;

    private GameConverter gameConverter;
    private AmountService amountService;

    private GameCardService gameCardService;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository,
                           UserRepository userRepository,
                           GameConverter gameConverter,
                           AmountService amountService,
                           GameCardService gameCardService) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.gameConverter = gameConverter;
        this.amountService = amountService;
        this.gameCardService = gameCardService;
    }

    @Override
    public GameDto createGameForUser(final String userEmail, final BigDecimal rate) {

        User user = userRepository.findByEmail(userEmail);
        Validate.notNull(user, "User has not been found");

        if (user.getAmount().compareTo(rate) < 0) {
            throw new GameException("Not enough money!");
        } else if (BigDecimal.ZERO.compareTo(rate) > 0) {
            throw new GameException("Rate should be more then 0 !");
        }
        Game game = gameRepository.newEntity();
        game.setUser(user);
        game.setRate(rate);
        game.setState(GameState.ON_PROGRESS);
        CardGenerator cardGenerator = gameCardService.createCardGenerator(game);
        UserCard userCard1 = cardGenerator.nextUserCard();
        CroupierCard croupierCard1 = cardGenerator.nextCroupierCard();
        UserCard userCard2 = cardGenerator.nextUserCard();
        CroupierCard croupierCard2 = cardGenerator.nextCroupierCard();
        game.getGameCards().add(userCard1);
        game.getGameCards().add(userCard2);
        game.getGameCards().add(croupierCard1);
        game.getGameCards().add(croupierCard2);
        game = gameRepository.saveAndFlush(game);


        amountService.withdrawForNewGame(game);
        return gameConverter.convert(game);
    }

    @Override
    public GameDto hitUserCard(String requestedUser, long gameId) {
        User user = userRepository.findByEmail(requestedUser);
        Game game = gameRepository.findOne(gameId);

        if (!Objects.equals(game.getUser(), user)) {
            throw new GameException("WTF? Are you cheater?");
        }

        CardGenerator cardGenerator = gameCardService.createCardGenerator(game);
        game.getGameCards().add(cardGenerator.nextUserCard());
        game = gameRepository.saveAndFlush(game);

        return gameConverter.convert(game);
    }

    @Override
    public GameDto finishGame(String requestedUser, long gameId) {
        return null;
    }
}
