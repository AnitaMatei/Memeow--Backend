package com.callbackcats.memeow.model.dto;

import com.google.gson.Gson;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@EqualsAndHashCode
public class MemeDTO {
    private String memeBusinessId;
    private String memeUrl;
    private Integer reactionCount;
    private Timestamp dateTimeUtc;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
