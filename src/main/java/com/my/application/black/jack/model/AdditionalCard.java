package com.my.application.black.jack.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */
//@Entity
public class AdditionalCard extends AbstractEntity {
    @ManyToOne
    private Game game;
    private Boolean userCard;
    private Card cardValue;


}
