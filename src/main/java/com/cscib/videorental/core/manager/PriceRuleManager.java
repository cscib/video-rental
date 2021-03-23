package com.cscib.videorental.core.manager;

import com.cscib.videorental.core.service.PricingService;
import com.cscib.videorental.data.model.Price;
import com.cscib.videorental.data.model.enums.MovieCategoryEnum;
import com.cscib.videorental.data.model.enums.ProductCategoryEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class PriceRuleManager {

    @Autowired
    private PricingService pricingService;

    private final Map<String, Price> productCategoryMap;

    private final BigDecimal basicPrice;

    private final BigDecimal premiumPrice;

    private PriceRuleManager() {
        productCategoryMap = pricingService.createCategoryPriceMap();
        basicPrice =  productCategoryMap.get(ProductCategoryEnum.BASIC_PRICE.getId()).getAmount();
        premiumPrice =  productCategoryMap.get(ProductCategoryEnum.PREMIUM_PRICE.getId()).getAmount();
    }

    public BigDecimal calculatePrice(MovieCategoryEnum movieCategory, int rentalDays) {
        switch (movieCategory) {
            case NEW_RELEASE:
                return premiumPrice.multiply(new BigDecimal(rentalDays));
            case REGULAR:
                return basicPrice.add((rentalDays-3) > 0 ? new BigDecimal(rentalDays - 3).multiply(basicPrice)
                                                         : new BigDecimal(0));
            case OLD:
                return basicPrice.add((rentalDays-5) > 0 ? new BigDecimal(rentalDays - 5).multiply(basicPrice)
                        : new BigDecimal(0));
            default:
                return new BigDecimal(0);
        }
    }

}

