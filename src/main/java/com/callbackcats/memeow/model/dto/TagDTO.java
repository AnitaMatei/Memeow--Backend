package com.callbackcats.memeow.model.dto;

import com.google.gson.Gson;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class TagDTO {
    private String tagName;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
