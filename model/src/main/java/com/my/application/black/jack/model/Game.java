package com.my.application.black.jack.model;

import com.my.application.black.jack.model.cards.GameCard;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<GameCard> gameCards = new ArrayList<>();

//    @OneToMany(fetch = FetchType.EAGER, targetEntity = UserCard.class, mappedBy = "id", cascade = CascadeType.ALL)
//    private List<UserCard> userCards = new ArrayList<>();
//
//    @OneToMany(fetch = FetchType.EAGER, targetEntity = CroupierCard.class, mappedBy = "id", cascade = CascadeType.ALL)
//    private List<CroupierCard> croupierCards = new ArrayList<>();

    public Game() {
        this.start = LocalDateTime.now();
    }


}
