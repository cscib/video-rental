package com.cscib.videorental.data.model;

import com.univocity.parsers.annotations.Parsed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name="bonus_category")
public class BonusCategory {

    @Id
    @Parsed
    private Integer id;

    @OneToMany(mappedBy="bonus_category")
    private List<Movie> movies;

    @Parsed
    private int points;

    // new release, regular, old
    @Parsed
    private String type;


}

