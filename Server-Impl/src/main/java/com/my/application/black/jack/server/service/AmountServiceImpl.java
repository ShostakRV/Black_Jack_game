package com.my.application.black.jack.server.service;

import com.my.application.black.jack.model.*;
import com.my.application.black.jack.server.dao.AmountHistoryRepository;
import com.my.application.black.jack.server.dao.UserRepository;
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
public class AmountServiceImpl implements AmountService {
    private AmountHistoryRepository amountHistoryRepository;
    private UserRepository userRepository;

    @Autowired
    public AmountServiceImpl(AmountHistoryRepository amountHistoryRepository, UserRepository userRepository) {
        this.amountHistoryRepository = amountHistoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void withdrawForNewGame(Game game) {
        User user = game.getUser();
        BigDecimal rate = game.getRate();
        BigDecimal before = user.getAmount();
        BigDecimal after = before.subtract(rate);
        logAmountChangeForUser(game, before, after);
        user.setAmount(after);
        userRepository.saveAndFlush(user);

    }

    private void logAmountChangeForUser(Game game, BigDecimal before, BigDecimal after) {
        AmountHistory amountHistory = amountHistoryRepository.newEntity();
        amountHistory.setUser(game.getUser());
        amountHistory.setBefore(before);
        amountHistory.setAfter(after);
        amountHistory.setSource(AmountSource.GAME);
        amountHistory.setGame(game);
        amountHistoryRepository.saveAndFlush(amountHistory);
    }

    @Override
    @Transactional
    public void processAmountForFinishedGame(Game game) {// todo make test case
        User user = game.getUser();
        BigDecimal before = user.getAmount();
        BigDecimal rate = game.getRate();
        BigDecimal increaseValue;
        GameState gameState = game.getState();
        if (gameState == GameState.USER_WIN) {
            increaseValue = rate.multiply(new BigDecimal(2));
        } else if (gameState == GameState.USER_BLACK_JACK) {
            increaseValue = rate.add(rate.multiply(new BigDecimal(1.5)));
        } else if (gameState == GameState.DEAD_HEAT) {
            increaseValue = rate.add(rate);
        } else {
            increaseValue = BigDecimal.ZERO;
        }

        BigDecimal after = before.add(increaseValue);
        if (!Objects.equals(increaseValue, BigDecimal.ZERO)) {
            logAmountChangeForUser(game, before, after);

            user.setAmount(after);

            userRepository.save(user);
        }
    }


}
