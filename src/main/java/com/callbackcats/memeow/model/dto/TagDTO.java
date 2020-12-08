package com.callbackcats.memeow.model.dto;

import com.callbackcats.memeow.model.entity.Template;
import com.google.gson.Gson;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode
public class TagDTO {
    Set<TemplateDTO> templatesByTemplateId;
    private String tagName;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
