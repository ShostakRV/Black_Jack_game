package com.my.application.black.jack.server.service;

import com.my.application.black.jack.model.Card;
import com.my.application.black.jack.model.cards.CroupierCard;
import com.my.application.black.jack.model.cards.UserCard;

/**
 * Developer: Roman Shostak
 * Date: 27-Oct-15.
 */
public interface CardGenerator {


    UserCard nextUserCard();

    CroupierCard nextCroupierCard();
}
