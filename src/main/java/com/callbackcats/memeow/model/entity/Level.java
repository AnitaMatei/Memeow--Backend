package com.callbackcats.memeow.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
public class Level {
    @Id
    @Column(name = "level_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer levelId;
    @Basic
    @Column(name = "current_level")
    private Integer currentLevel = 1;
    @Basic
    @Column(name = "current_xp")
    private Integer currentXp = 0;
    @Basic
    @Column(name = "last_time_requested_UTC")
    private Timestamp lastTimeRequestedUtc = new Timestamp(new Date().getTime());
    @Basic
    @Column(name = "last_current_xp")
    private Integer lastCurrentXp = 0;
    @Basic
    @Column(name = "last_current_level")
    private Integer lastCurrentLevel = 1;
    @OneToOne(mappedBy = "level")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;
}
