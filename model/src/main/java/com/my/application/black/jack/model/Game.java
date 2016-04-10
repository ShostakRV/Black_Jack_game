package com.my.application.black.jack.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "GAME")
public class Game extends AbstractEntity {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "FK_USER")
    private User user;
    @NotNull
    @Column(name = "START_ON")
    private LocalDateTime start;

    @Column(name = "FINISHED_ON")
    private LocalDateTime finish;
    @NotNull
    @Column(name = "RATE")
    private BigDecimal rate;
//    @NotNull
//    @Column(name = "USER_CARD_1", updatable = false)
//    @Enumerated(EnumType.STRING)
//    private Card userCard1;
//    @NotNull
//    @Column(name = "USER_CARD_2", updatable = false)
//    @Enumerated(EnumType.STRING)
//    private Card userCard2;
//    @NotNull
//    @Column(name = "CROUPIER_CARD_1",updatable = false)
//    @Enumerated(EnumType.STRING)
//    private Card croupierCard1;
//    @NotNull
//    @Column(name = "CROUPIER_CARD_2", updatable = false)
//    @Enumerated(EnumType.STRING)
//    private Card croupierCard2;

    public Game() {
        this.start = LocalDateTime.now();
    }


}
