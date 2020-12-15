package com.callbackcats.memeow.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Tag {
    @ManyToMany
    @JoinTable(
            name = "temp_tag",
            joinColumns = @JoinColumn(name="tag_id"),
            inverseJoinColumns = @JoinColumn(name="template_id")
    )
    @EqualsAndHashCode.Exclude
    Set<Template> templates;
    @Id
    @Column(name = "tag_id")
    private Integer tagId;
    @Basic
    @Column(name = "tag_name")
    private String tagName;
}
