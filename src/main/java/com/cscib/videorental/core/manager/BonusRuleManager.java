package com.cscib.videorental.core.manager;

import com.cscib.videorental.core.service.BonusCategoryService;
import com.cscib.videorental.data.DataLoader;
import com.cscib.videorental.data.model.BonusCategory;
import com.cscib.videorental.data.model.Rental;
import com.cscib.videorental.data.model.enums.MovieCategoryEnum;
import com.cscib.videorental.exception.DataLoadingException;
import com.cscib.videorental.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class BonusRuleManager {

    @Autowired
    private BonusCategoryService bonusService;

    @Autowired
    private DataLoader dataLoader;

    private Map<Integer, BonusCategory> bonusCategoryMap;

    private int newReleasePoints;

    private int regularPoints;

    private int oldPoints;


    @PostConstruct
    public void initialize() throws DataLoadingException {

        if (dataLoader.isLoaded()) {
            dataLoader.load();
        }
        bonusCategoryMap = bonusService.createBonusCategoryMap();
        newReleasePoints = bonusCategoryMap.get(MovieCategoryEnum.NEW_RELEASE.getId()).getPoints();
        regularPoints = bonusCategoryMap.get(MovieCategoryEnum.REGULAR.getId()).getPoints();
        oldPoints = bonusCategoryMap.get(MovieCategoryEnum.REGULAR.getId()).getPoints();
    }

    public int calculateBonus(List<Rental> rentals) {

        int bonus = 0;
        rentals.stream()
                .map(r-> bonus + calculateBonus(r));

        return bonus;
    }


    public int calculateBonus(Rental rental) {
        MovieCategoryEnum moveCategory = MovieCategoryEnum.valueOf(rental.getMovie().getBonus_category().getId());
        return calculateBonus(moveCategory);
    }


    public int calculateBonus(MovieCategoryEnum movieCategory) {
        switch (movieCategory) {
            case NEW_RELEASE:
                return newReleasePoints;
            case REGULAR:
                return regularPoints;
            case OLD:
                return oldPoints;
            default:
                return 0;
        }
    }

}

