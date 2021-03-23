package com.cscib.videorental.data;


import com.cscib.videorental.data.model.Bonus;
import com.cscib.videorental.data.model.Price;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvRoutines;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    @Autowired
    private CsvParserSettings csvParserSettings;

    public void load(){
        // load bonus data to repository
        log.info("Reading Bonus Data from {} and Storing to Repository.",dataBonusPath);
        Path path = Paths.get(dataBonusPath);
        List<Bonus> bonuses =  read(path, Bonus.class);

        // load price data to repository
        log.info("Reading Price Data from {} and Storing to Repository.",dataPricePath);
        path = Paths.get(dataPricePath);
        List<Price> prices =  read(path, Price.class);

    }

    private <T> List<T> read(Path path, Class<T> beanType) {
        return new CsvRoutines(csvParserSettings).parseAll(beanType, path.toFile(), "UTF-8");
    }
}
