package com.cscib.videorental.data.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MovieCategoryEnum {

    NEW_RELEASE(0, "NEW RELEASE"),
    REGULAR(1,"REGULAR"),
    OLD(2, "OLD");

    private int id;

    private String desc;

    public static MovieCategoryEnum valueOf(int id) {
        for (MovieCategoryEnum mEnum : MovieCategoryEnum.values()) {
            if (mEnum.id == id) return mEnum;
        }
        return null;
    }

}
