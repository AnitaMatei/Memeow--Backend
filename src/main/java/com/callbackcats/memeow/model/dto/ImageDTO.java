package com.callbackcats.memeow.model.dto;

import com.callbackcats.memeow.model.entity.Template;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;


@Setter
@Getter
@EqualsAndHashCode
public class ImageDTO {
    private String imageUrl;
    private Collection<Template> templatesByImageId;
}
