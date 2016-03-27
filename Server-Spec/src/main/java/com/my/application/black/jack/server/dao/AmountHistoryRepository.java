package com.my.application.black.jack.server.dao;

import com.my.application.black.jack.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
public interface AmountHistoryRepository extends JpaRepository<Game, Long> {
}
