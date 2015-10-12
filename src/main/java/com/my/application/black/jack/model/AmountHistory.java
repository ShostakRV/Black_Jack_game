package com.my.application.black.jack.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */
//@Entity
public class AmountHistory extends AbstractEntity {
    @NotNull
    private User user;
    @NotNull
    private BigDecimal before;
    @NotNull
    private BigDecimal after;
    @NotNull
    private AmountSource source;

}
