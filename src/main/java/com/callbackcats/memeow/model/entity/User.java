package com.callbackcats.memeow.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String password;
    @Basic
    @Column(name = "email")
    @NonNull
    private String email;
    @Basic
    @Column(name = "first_name")
    @NonNull
    private String firstName;
    @Basic
    @Column(name = "last_name")
    @NonNull
    private String lastName;
    @Basic
    @Column(name = "age")
    private Integer age;
    @Basic
    @Column(name = "icon_url")
    private String iconUrl;
    @Basic
    @Column(name = "has_facebook")
    private Byte hasFacebook;
    @Basic
    @Column(name = "facebook_registration_date_UTC")
    private Timestamp facebookRegistrationDateUtc;
    @Basic
    @Column(name = "has_twitter")
    private Byte hasTwitter;
    @Basic
    @Column(name = "twitter_registration_date_UTC")
    private Timestamp twitterRegistrationDateUtc;
    @Basic
    @Column(name = "has_instagram")
    private Byte hasInstagram;
    @Basic
    @Column(name = "instagram_registration_date_UTC")
    private Timestamp instagramRegistrationDateUtc;
    @Basic
    @Column(name = "profile_uuid")
    @NonNull
    private String profileUuid;
    @Basic
    @Column
    private String userRole;
    @Basic
    @Column
    private String facebookLink;
    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    private Collection<Meme> memes;
    @OneToOne(mappedBy = "userByUserId")
    @EqualsAndHashCode.Exclude
    private Leaderboard leaderboardPlaceByUserId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id", referencedColumnName = "level_id")
    @NonNull
    private Level level;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_liked_meme",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "meme_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Meme> likedMemes = new HashSet<>();
}
