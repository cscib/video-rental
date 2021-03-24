package com.cscib.videorental.api;

import com.google.gson.Gson;

public abstract class AbstractControllerTest {

    protected String mapToJson(Object obj) {
        return new Gson().toJson(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz) {
       return new Gson().fromJson(json,clazz);
    }
}