package com.my.application.black.jack.service.impl;

import com.my.application.black.jack.dao.AmountHistoryRepository;
import com.my.application.black.jack.service.AmountHistoryService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
@Service
public class AmountHistoryServiceImpl implements AmountHistoryService {
    private AmountHistoryRepository amountHistoryRepository;

    @Inject
    public AmountHistoryServiceImpl(AmountHistoryRepository amountHistoryRepository) {
        this.amountHistoryRepository = amountHistoryRepository;
    }
}
