package com.cscib.videorental.data;


import com.cscib.videorental.data.init.MovieDTO;
import com.cscib.videorental.data.model.BonusCategory;
import com.cscib.videorental.data.model.Movie;
import com.cscib.videorental.data.model.PriceCategory;
import com.cscib.videorental.data.repository.BonusCategoryRepository;
import com.cscib.videorental.data.repository.MovieRepository;
import com.cscib.videorental.data.repository.PriceCategoryRepository;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvRoutines;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Slf4j
@Component
public class DataLoader {

    @Value("${data.bonus.path}")
    private String dataBonusPath;

    @Value("${data.price.path}")
    private String dataPricePath;

    @Value("${data.movies.path}")
    private String dataMoviePath;

    @Autowired
    private CsvParserSettings csvParserSettings;

    @Autowired
    private BonusCategoryRepository bonusRepository;

    @Autowired
    private PriceCategoryRepository priceRepository;

    @Autowired
    private MovieRepository movieRepository;

    private volatile boolean isLoaded = false;


    @PostConstruct
    private void setupData() {
        load();
    }


    public void load(){
        load(false);
    }

    public void load(boolean reload){

        synchronized (this) {
            if (!isLoaded || reload) {
                // load bonus data to repository
                log.info("Reading Bonus Data from {} and Storing to Repository.", dataBonusPath);
                Path path = Paths.get(dataBonusPath);
                List<BonusCategory> bonuses = read(path, BonusCategory.class);
                bonusRepository.saveAll(bonuses);

                // load price data to repository
                log.info("Reading Price Data from {} and Storing to Repository.", dataPricePath);
                path = Paths.get(dataPricePath);
                List<PriceCategory> prices = read(path, PriceCategory.class);
                priceRepository.saveAll(prices);

                // load movie data to repository
                log.info("Reading Movie Data from {} and Storing to Repository.", dataMoviePath);
                path = Paths.get(dataMoviePath);
                List<MovieDTO> movieCategoryPairs = read(path,MovieDTO.class);
                for (MovieDTO movieCategoryPair : movieCategoryPairs) {
                    BonusCategory bonusCategory = bonusRepository.findByType(String.valueOf(movieCategoryPair.getBonusCategory()));
                    Movie movie = new Movie();
                    movie.setName(movieCategoryPair.getName());
                    movie.setBonus_category(bonusCategory);
                    movieRepository.save(movie);
                }
                isLoaded = true;
            }
        }
    }

    public boolean isLoaded(){
        synchronized (this) {
            return isLoaded;
        }
    }

    public void reload(){
       load(true);
    }

    private <T> List<T> read(Path path, Class<T> beanType) {
        return new CsvRoutines(csvParserSettings).parseAll(beanType, path.toFile(), "UTF-8");
    }
}
