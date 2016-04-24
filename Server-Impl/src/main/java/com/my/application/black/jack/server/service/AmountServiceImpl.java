package com.my.application.black.jack.server.service;

import com.my.application.black.jack.model.AmountHistory;
import com.my.application.black.jack.model.AmountSource;
import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.server.dao.AmountHistoryRepository;
import com.my.application.black.jack.server.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


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
    public void increaseAmountForWonGame(Game game) {
        User user = game.getUser();
        BigDecimal before = user.getAmount();
        BigDecimal rate = game.getRate();
        BigDecimal after = before.add(rate.multiply(new BigDecimal(2)));

        logAmountChangeForUser(game, before, after);

        user.setAmount(after);

        userRepository.save(user);


    }


}
