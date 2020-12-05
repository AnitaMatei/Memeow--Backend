package com.callbackcats.memeow.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
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
    private String email;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "age")
    private Integer age;
    @Basic
    @Column(name = "registration_platform")
    private String registrationPlatform;
    @Basic
    @Column(name = "registration_date_UTC")
    private Timestamp registrationDateUtc;
    @Basic
    @Column(name = "icon_url")
    private String iconUrl;
    @Basic
    @Column(name = "xp")
    private Long xp;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Meme> memesByUserId;
}
