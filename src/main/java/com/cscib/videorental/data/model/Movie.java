package com.cscib.videorental.data.model;

import com.univocity.parsers.annotations.Parsed;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="movie")
public class Movie {

    @Id
    private String name;

    @OneToMany(mappedBy="movie")
    private List<Rental> rentals;

    @ManyToOne(cascade = CascadeType.PERSIST)
    // NEW RELEASE / REGULAR / OLD  & the Points Per Bonus Category
    private BonusCategory bonus_category;
}

