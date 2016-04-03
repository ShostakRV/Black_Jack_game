package com.my.application.black.jack.server.dao;

import com.my.application.black.jack.model.AmountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
public interface AmountHistoryRepository extends JpaRepository<AmountHistory, Long> {
    default AmountHistory newEntity() {
        return new AmountHistory();
    }

    List<AmountHistory> findByGameId(Long id);
}
