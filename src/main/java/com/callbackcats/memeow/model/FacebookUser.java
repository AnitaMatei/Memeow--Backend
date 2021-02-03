package com.callbackcats.memeow.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacebookUser {
    String email;
    String first_name;
    String last_name;
    Picture picture;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @AllArgsConstructor
    public static class Picture{
        @Getter
        Data data;

        @AllArgsConstructor
        public static class Data {
            @Getter
            String url;
        }
    }

    public String getPictureUrl(){
        return picture.getData().getUrl();
    }

}
