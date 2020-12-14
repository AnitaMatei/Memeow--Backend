package com.callbackcats.memeow.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Setter
@Getter
@EqualsAndHashCode
public class Meme {
    @ManyToMany
    @JoinTable(
            name = "meme_temp",
            joinColumns = @JoinColumn(name = "meme_id"),
            inverseJoinColumns = @JoinColumn(name = "template_id")
    )
    Set<Template> templatesByTemplateId;
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
    private User userByUserId;
}
