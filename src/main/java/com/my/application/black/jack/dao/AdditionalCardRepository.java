package com.my.application.black.jack.dao;

import com.my.application.black.jack.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
public interface AdditionalCardRepository extends JpaRepository<Game, Long> {

}