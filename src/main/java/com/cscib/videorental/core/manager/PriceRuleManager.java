package com.cscib.videorental.core.manager;



import com.cscib.videorental.data.model.Price;
import com.cscib.videorental.data.model.enums.MovieCategoryEnum;
import com.cscib.videorental.data.model.enums.ProductCategoryEnum;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PriceRuleManager {

    private final Map<ProductCategoryEnum, Price> productCategoryMap;

    private PriceRuleManager(){

        Map<ProductCategoryEnum, Price> productCategoryMap = new HashMap<>();
        // TODO load productCategoryMap from db
        this.productCategoryMap = productCategoryMap;
    }

    public void calculatePrice(MovieCategoryEnum movieCategory, int rentalDays) {

        // TODO Calculate the price
        BigDecimal amount;
        switch  (movieCategory) {
            case NEW_RELEASE : amount = new BigDecimal(1);
            case REGULAR: amount = new BigDecimal(1);
            case OLD:  amount = new BigDecimal(1);
        }

    }



}
