package com.callbackcats.memeow.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
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
    Set<Tag> templateTags;
    @Id
    @Column(name = "template_id")
    private Integer templateId;
    @Basic
    @Column(name = "template_name")
    private String templateName;
    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "image_id", nullable = false)
    private Image imageByImageId;
}
