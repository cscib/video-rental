package com.cscib.videorental.core.service;

import com.cscib.videorental.data.model.Bonus;
import com.cscib.videorental.data.model.Price;
import com.cscib.videorental.data.repository.PriceRepository;
import com.cscib.videorental.exception.DataLoadingException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.*;

@NoArgsConstructor
@Component
public class PricingService {

    @Autowired
    private PriceRepository priceRepository;

    public Price savePrice(Price price) throws Exception {
        return Optional.of(priceRepository.save(price)).orElseThrow(PersistenceException::new);
    }

    public Price getPrice(int priceId){
        return Optional.of(priceRepository.findById(priceId).get()).orElseThrow(PersistenceException::new);
    }

    public List<Price> getPrices() {
        return Optional.of(priceRepository.findAll()).orElseThrow(PersistenceException::new);
    }

    public Map<String, Price> createCategoryPriceMap() throws DataLoadingException {
        List<Price> prices = Optional.ofNullable(priceRepository.findAll())
                .orElseThrow(DataLoadingException::new);

        if (prices.isEmpty()) throw new DataLoadingException();

        Map<String, Price> map = new HashMap<>();
        prices.stream()
                .forEach(price -> map.put((price.getCategory().getId()), price));


        return map;
    }

}
