package com.cscib.videorental.core.service;

import com.cscib.videorental.data.model.Bonus;
import com.cscib.videorental.data.repository.BonusRepository;
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
public class BonusService {

    @Autowired
    private BonusRepository bonusRepository;

    public Bonus saveBonus(Bonus bonus) throws Exception {
        return Optional.of(bonusRepository.save(bonus)).orElseThrow(PersistenceException::new);
    }

    public Bonus getBonus(String bonusId){
        return Optional.of(bonusRepository.findById(bonusId).get()).orElseThrow(PersistenceException::new);
    }

    public List<Bonus> getBonuses() {
        return Optional.of(bonusRepository.findAll()).orElseThrow(PersistenceException::new);
    }

    public Map<String, Bonus> createBonusCategoryMap() throws DataLoadingException {
        List<Bonus> bonuses = Optional.ofNullable(bonusRepository.findAll())
                .orElseThrow(DataLoadingException::new);
        if (bonuses.isEmpty()) throw new DataLoadingException();

        Map<String, Bonus> map = new HashMap<>();
        bonuses.stream()
        .forEach(bonus -> map.put((bonus.getCategory().getId()), bonus));

        return map;
    }
}
