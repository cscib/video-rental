package com.cscib.videorental.core.service;

import com.cscib.videorental.data.model.BonusCategory;
import com.cscib.videorental.data.repository.BonusCategoryRepository;
import com.cscib.videorental.exception.DataLoadingException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@Component
public class BonusCategoryService {

    @Autowired
    private BonusCategoryRepository bonusCategoryRepository;

    public BonusCategory saveBonus(BonusCategory bonus) throws Exception {
        return Optional.of(bonusCategoryRepository.save(bonus)).orElseThrow(PersistenceException::new);
    }

    public BonusCategory getBonus(String bonusId){
        return Optional.of(bonusCategoryRepository.findById(bonusId).get()).orElseThrow(PersistenceException::new);
    }

    public List<BonusCategory> getBonuses() {
        return Optional.of(bonusCategoryRepository.findAll()).orElseThrow(PersistenceException::new);
    }

    public Map<Integer, BonusCategory> createBonusCategoryMap() throws DataLoadingException {
        List<BonusCategory> bonuses = Optional.ofNullable(bonusCategoryRepository.findAll())
                .orElseThrow(DataLoadingException::new);
        if (bonuses.isEmpty()) throw new DataLoadingException();

        Map<Integer, BonusCategory> map = new HashMap<>();
        bonuses.stream()
        .forEach(bonus -> map.put((bonus.getId()), bonus));

        return map;
    }
}
