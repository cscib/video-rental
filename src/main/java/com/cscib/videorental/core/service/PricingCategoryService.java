package com.cscib.videorental.core.service;

import com.cscib.videorental.data.model.PriceCategory;
import com.cscib.videorental.data.repository.PriceCategoryRepository;
import com.cscib.videorental.exception.DataLoadingException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.*;

@NoArgsConstructor
@Component
public class PricingCategoryService {

    @Autowired
    private PriceCategoryRepository priceRepository;

    public PriceCategory savePriceCategory(PriceCategory priceCategory) throws Exception {
        return Optional.of(priceRepository.save(priceCategory)).orElseThrow(PersistenceException::new);
    }

    public PriceCategory getPriceCategory(int priceCategoryId){
        return Optional.of(priceRepository.findById(priceCategoryId).get()).orElseThrow(PersistenceException::new);
    }

    public List<PriceCategory> getPriceCategorys() {
        return Optional.of(priceRepository.findAll()).orElseThrow(PersistenceException::new);
    }

    public Map<Integer, PriceCategory> createCategoryPriceMap() throws DataLoadingException {
        List<PriceCategory> priceCategories = Optional.ofNullable(priceRepository.findAll())
                .orElseThrow(DataLoadingException::new);

        if (priceCategories.isEmpty()) throw new DataLoadingException();

        Map<Integer, PriceCategory> map = new HashMap<>();
        priceCategories.stream()
                .forEach(priceCategory -> map.put((priceCategory.getId()), priceCategory));


        return map;
    }

}
