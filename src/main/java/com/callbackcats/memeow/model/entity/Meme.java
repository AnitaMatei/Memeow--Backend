package com.callbackcats.memeow.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    @ToString.Exclude
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
    @ToString.Exclude
    private User user;
    @OneToOne(mappedBy = "meme", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private RecentMeme recentMeme;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_liked_meme",
            joinColumns = @JoinColumn(name = "meme_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> likedBy = new HashSet<>();
}
