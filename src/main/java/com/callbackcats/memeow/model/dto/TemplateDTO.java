package com.callbackcats.memeow.model.dto;

import com.callbackcats.memeow.model.entity.Image;
import com.callbackcats.memeow.model.entity.Meme;
import com.callbackcats.memeow.model.entity.Tag;
import com.google.gson.Gson;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode
public class TemplateDTO {
    Set<Tag> tagsByTagId;
    Set<Meme> memesByMemeId;
    private String templateName;
    private Image imageByImageId;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
