package com.callbackcats.memeow.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Leaderboard {
    @Id
    @Column(name = "leaderboard_place_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leaderboardPlaceId;
    @Basic
    @Column(name = "leaderboard_place")
    private Integer leaderboardPlace;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User userByUserId;
}
