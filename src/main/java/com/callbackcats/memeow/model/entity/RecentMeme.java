package com.callbackcats.memeow.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="recent_meme")
public class RecentMeme {
    @Id
    @Column(name = "recent_meme_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recentMemeId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meme_id", referencedColumnName = "meme_id", nullable = false)
    @NonNull
    private Meme meme;
}
