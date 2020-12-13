package com.callbackcats.memeow.model.dto;

import com.google.gson.Gson;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class TemplateDTO {
    private String templateName;
    private Integer minRequiredLevel;
    String imageUrl;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
