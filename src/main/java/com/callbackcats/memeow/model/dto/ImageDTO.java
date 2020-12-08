package com.callbackcats.memeow.model.dto;

import com.callbackcats.memeow.model.entity.Template;
import com.google.gson.Gson;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;


@Setter
@Getter
@EqualsAndHashCode
public class ImageDTO {
    private String imageUrl;
    private Collection<TemplateDTO> templatesByImageId;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
