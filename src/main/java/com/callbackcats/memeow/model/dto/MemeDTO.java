package com.callbackcats.memeow.model.dto;

import com.callbackcats.memeow.model.entity.Template;
import com.callbackcats.memeow.model.entity.User;
import com.google.gson.Gson;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode
public class MemeDTO {
    Set<TemplateDTO> templatesByTemplateId;
    private String memeUrl;
    private String storageLocation;
    private String memeTitle;
    private Integer reactionCount;
    private Timestamp dateTimeUtc;
    private User userByUserId;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
