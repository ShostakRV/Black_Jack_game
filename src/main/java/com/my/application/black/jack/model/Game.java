package com.my.application.black.jack.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */
//@Entity
public class Game extends AbstractEntity {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "FK_USER")
    private User user;
    @NotNull
    private LocalDateTime start;
    @NotNull
    private LocalDateTime finish;
    @NotNull
    private BigDecimal rate;
    @NotNull
    private Card userCard1;
    @NotNull
    private Card userCard2;
    @NotNull
    private Card croupierCard1;
    @NotNull
    private Card croupierCard2;
}
