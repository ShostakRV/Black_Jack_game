package com.my.application.black.jack.server.service;

import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.GameState;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.model.cards.CardType;
import com.my.application.black.jack.model.cards.CroupierCard;
import com.my.application.black.jack.model.cards.GameCard;
import com.my.application.black.jack.model.cards.UserCard;
import com.my.application.black.jack.server.dao.GameRepository;
import com.my.application.black.jack.server.dao.UserRepository;
import com.my.application.black.jack.server.exception.GameException;
import com.my.application.black.jack.server.service.converter.GameConverter;
import com.my.application.black.jack.server.service.dto.GameDto;
import com.my.application.black.jack.server.service.dto.GameResult;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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

        int sumOfUserCards = userCard1.getCardName().getValue() + userCard2.getCardName().getValue();
        if (sumOfUserCards == 21) {
            return finishGame(game, false);
        }

        return gameConverter.convert(game, sumOfUserCards, croupierCard1.getCardName().getValue());
    }

    private GameDto finishGame(Game game, boolean stand) {
        int croupierPoints = 0;
        if (stand) {
            CardGenerator cardGenerator = gameCardService.createCardGenerator(game);
            while (GameResultUtils.sumCardPoints(extractCardsByType(game, CardType.CROUPIER)) < 17) {
                game.getGameCards().add(cardGenerator.nextCroupierCard());
            }
        }
        GameResult gameResult = GameResultUtils.getGameResult(game.getGameCards(), stand);
        if (gameResult.getGameState() != GameState.ON_PROGRESS || stand) {
            game.setState(gameResult.getGameState());
            game.setFinish(LocalDateTime.now());
            game = gameRepository.saveAndFlush(game);
            amountService.processAmountForFinishedGame(game);
            croupierPoints = stand ? gameResult.getCroupierPoints() : 0;
        }

        return gameConverter.convert(game, gameResult.getUserPoints(), croupierPoints);
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
        List<GameCard> userCards = extractCardsByType(game, CardType.USER);
        int userPoints = GameResultUtils.sumCardPoints(userCards);
        if (userPoints >= 21) {// todo make test case
            return finishGame(game, true);
        }
        return gameConverter.convert(game, userPoints, 0);
    }

    @Override
    public GameDto finishGame(String requestedUser, long gameId) {// todo make test case
        User user = userRepository.findByEmail(requestedUser);
        Game game = gameRepository.findOne(gameId);

        if (!Objects.equals(game.getUser(), user)) {
            throw new GameException("WTF? Are you cheater?");
        }
        return finishGame(game, true);
    }

    private List<GameCard> extractCardsByType(Game game, CardType type) {
        return game.getGameCards().stream()
                .filter(card -> card.getCardType() == type)
                .collect(Collectors.toList());
    }
}
