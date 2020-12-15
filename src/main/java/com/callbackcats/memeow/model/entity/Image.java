package com.callbackcats.memeow.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Image {
    @Id
    @Column(name = "image_id")
    private Integer imageId;
    @Basic
    @Column(name = "image_url")
    private String imageUrl;
    @OneToMany(mappedBy = "image")
    @EqualsAndHashCode.Exclude
    private Collection<Template> templates;
}
