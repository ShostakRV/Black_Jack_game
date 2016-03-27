package com.my.application.black.jack.server.service;

import com.my.application.black.jack.server.dao.AmountHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
@Service
public class AmountHistoryServiceImpl implements AmountHistoryService {
    private AmountHistoryRepository amountHistoryRepository;

    @Autowired
    public AmountHistoryServiceImpl(AmountHistoryRepository amountHistoryRepository) {
        this.amountHistoryRepository = amountHistoryRepository;
    }
}
