package com.cscib.videorental.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MovieTypeEnum {

    NEW_RELEASE(0, "NEW RELEASE"),
    REGULAR(1,"REGULAR"),
    OLD(2, "OLD");

    private int id;

    private String desc;

}
