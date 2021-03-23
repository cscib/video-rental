package com.cscib.videorental.data.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductCategoryEnum {

    PREMIUM_PRICE(0, "Premium Price"),
    BASIC_PRICE(1,"Regular Price");

    private int id;

    private String desc;

}
