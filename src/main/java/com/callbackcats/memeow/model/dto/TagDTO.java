package com.callbackcats.memeow.model.dto;

import com.callbackcats.memeow.model.entity.Template;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode
public class TagDTO {
    Set<Template> templatesByTemplateId;
    private String tagName;
}
