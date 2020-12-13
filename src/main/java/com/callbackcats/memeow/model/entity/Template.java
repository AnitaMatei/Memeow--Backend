package com.callbackcats.memeow.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@EqualsAndHashCode
public class Template {
    @ManyToMany
    @JoinTable(
            name = "temp_tag",
            joinColumns = @JoinColumn(name = "template_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    Set<Tag> tagsByTagId;
    @ManyToMany
    @JoinTable(
            name = "meme_temp",
            joinColumns = @JoinColumn(name = "template_id"),
            inverseJoinColumns = @JoinColumn(name = "meme_id")
    )
    Set<Meme> memesByMemeId;
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
    private Image imageByImageId;
}
