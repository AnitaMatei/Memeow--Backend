package com.callbackcats.memeow.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Meme {
    @ManyToMany
    @JoinTable(
            name = "meme_temp",
            joinColumns = @JoinColumn(name = "meme_id"),
            inverseJoinColumns = @JoinColumn(name = "template_id")
    )
    @EqualsAndHashCode.Exclude
    Set<Template> templates = new HashSet<>();
    @Id
    @Column(name = "meme_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memeId;
    @Basic
    @Column(name = "meme_business_id")
    private String memeBusinessId;
    @Basic
    @Column(name = "meme_url")
    private String memeUrl;
    @Basic
    @Column(name = "reaction_count")
    private Integer reactionCount = 0;
    @Basic
    @Column(name = "date_time_UTC")
    private Timestamp dateTimeUtc;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @EqualsAndHashCode.Exclude
    private User user;
}
