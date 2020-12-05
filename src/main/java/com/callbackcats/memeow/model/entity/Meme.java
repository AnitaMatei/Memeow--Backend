package com.callbackcats.memeow.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Setter
@Getter
@EqualsAndHashCode
public class Meme {
    @Id
    @Column(name = "meme_id")
    private Integer memeId;
    @Basic
    @Column(name = "meme_url")
    private String memeUrl;
    @Basic
    @Column(name = "storage_location")
    private String storageLocation;
    @Basic
    @Column(name = "meme_title")
    private String memeTitle;
    @Basic
    @Column(name = "reaction_count")
    private Integer reactionCount;
    @Basic
    @Column(name = "date_time_UTC")
    private Timestamp dateTimeUtc;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userByUserId;
}
