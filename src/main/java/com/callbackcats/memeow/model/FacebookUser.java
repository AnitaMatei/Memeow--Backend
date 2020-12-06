package com.callbackcats.memeow.model;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacebookUser {
    String email;
    String first_name;
    String last_name;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
