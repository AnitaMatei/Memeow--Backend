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
public class Tag {
    @ManyToMany
    @JoinTable(
            name = "temp_tag",
            joinColumns = @JoinColumn(name="tag_id"),
            inverseJoinColumns = @JoinColumn(name="template_id")
    )
    Set<Template> templatesWithTag;
    @Id
    @Column(name = "tag_id")
    private Integer tagId;
    @Basic
    @Column(name = "tag_name")
    private String tagName;
}
