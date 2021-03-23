package com.cscib.videorental.data.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductCategoryEnum {

    PREMIUM_PRICE(0, "Premium Category"),
    BASIC_PRICE(1,"Regular Category");

    private int id;

    private String desc;

}
