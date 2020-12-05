package com.callbackcats.memeow.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Setter
@Getter
@EqualsAndHashCode
public class Image {
    @Id
    @Column(name = "image_id")
    private Integer imageId;
    @Basic
    @Column(name = "image_url")
    private String imageUrl;
    @OneToMany(mappedBy = "imageByImageId")
    private Collection<Template> templatesByImageId;
}
