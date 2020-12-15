package com.callbackcats.memeow.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Template {
    @ManyToMany
    @JoinTable(
            name = "temp_tag",
            joinColumns = @JoinColumn(name = "template_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @EqualsAndHashCode.Exclude
    Set<Tag> tags = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "meme_temp",
            joinColumns = @JoinColumn(name = "template_id"),
            inverseJoinColumns = @JoinColumn(name = "meme_id")
    )
    @EqualsAndHashCode.Exclude
    Set<Meme> memes = new HashSet<>();
    @Id
    @Column(name = "template_id")
    private Integer templateId;
    @Basic
    @Column(name = "template_name")
    private String templateName;
    @Basic
    @Column(name="min_required_level")
    private Integer minRequiredLevel;
    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "image_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private Image image;
}
