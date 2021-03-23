package com.cscib.videorental.core.manager;

import com.cscib.videorental.core.service.BonusService;
import com.cscib.videorental.data.model.Bonus;
import com.cscib.videorental.data.model.enums.MovieCategoryEnum;
import com.cscib.videorental.data.model.enums.ProductCategoryEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class BonusRuleManager {

    @Autowired
    private BonusService bonusService;

    private final Map<String, Bonus> bonusCategoryMap;

    private final int newReleasePoints;

    private final int regularPoints;

    private final int oldPoints;

    private BonusRuleManager() {
        bonusCategoryMap = bonusService.createBonusCategoryMap();
        newReleasePoints = bonusCategoryMap.get(MovieCategoryEnum.NEW_RELEASE.getId()).getPoints();
        regularPoints = bonusCategoryMap.get(MovieCategoryEnum.REGULAR.getId()).getPoints();
        oldPoints = bonusCategoryMap.get(MovieCategoryEnum.REGULAR.getId()).getPoints();
    }

    public int calculateBonuse(MovieCategoryEnum movieCategory, int rentalDays) {
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

