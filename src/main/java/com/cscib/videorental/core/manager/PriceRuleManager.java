package com.cscib.videorental.core.manager;

import com.cscib.videorental.core.service.PricingCategoryService;
import com.cscib.videorental.data.DataLoader;
import com.cscib.videorental.data.model.PriceCategory;
import com.cscib.videorental.data.model.Rental;
import com.cscib.videorental.data.model.Surcharge;
import com.cscib.videorental.data.model.enums.MovieCategoryEnum;
import com.cscib.videorental.data.model.enums.ProductCategoryEnum;
import com.cscib.videorental.exception.DataLoadingException;
import com.cscib.videorental.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PriceRuleManager {

    @Autowired
    private PricingCategoryService pricingService;

    @Autowired
    private DataLoader dataLoader;

    private Map<Integer, PriceCategory> productCategoryMap;

    private BigDecimal basicPrice;

    private BigDecimal premiumPrice;

    private String currency;


    @PostConstruct
    public void initialize() throws DataLoadingException {
        if (dataLoader.isLoaded()) {
            dataLoader.load();
        }
        productCategoryMap = pricingService.createCategoryPriceMap();
        currency = productCategoryMap.get(0).getCurrency();
        basicPrice =  productCategoryMap.get(ProductCategoryEnum.BASIC_PRICE.getId()).getAmount();
        premiumPrice =  productCategoryMap.get(ProductCategoryEnum.PREMIUM_PRICE.getId()).getAmount();
    }

    public BigDecimal calculatePrice(List<Rental> rentals) {
        log.info("Calculating price ");

        BigDecimal payment = new BigDecimal(0);
        for (Rental rental :
                rentals) {
            payment = payment.add(calculatePrice(rental));
        }
        return payment;
    }

    public BigDecimal calculatePrice(Rental rental) {
        log.info("[{}] Calculating price for {} rental {} ",rental.getClient(), rental.getMovie().getBonus_category().getId(),
                rental.getMovie().getName());
        MovieCategoryEnum moveCategory = MovieCategoryEnum.valueOf(rental.getMovie().getBonus_category().getId());
        int rentalDays = DateUtils.calculateRentalDays(rental.getRentedOn(), rental.getExpectedReturnOn());
        return calculatePrice(moveCategory, rentalDays);
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

    public String getCurrency() {

        return currency;

    }


    public BigDecimal calculateSurcharge(Rental r) {

        int totalDays = DateUtils.calculateRentalDays(r.getRentedOn(), r.getReturnedOn());
        if (totalDays >=1) {
            MovieCategoryEnum mEnum = MovieCategoryEnum.valueOf(r.getMovie().getBonus_category().getId());
            BigDecimal totalPrice = calculatePrice(mEnum, totalDays);
            return totalPrice.subtract(calculatePrice(mEnum, DateUtils.calculateRentalDays(r.getRentedOn(), r.getExpectedReturnOn())));
        }
        return new BigDecimal(0);

    }
}

