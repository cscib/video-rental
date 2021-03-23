package com.cscib.videorental.data.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name="movie")
public class Movie {

    @Id
    private String name;

    @OneToMany(mappedBy="movie")
    private List<Rental> rentals;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="price_category_id",foreignKey = @ForeignKey(name = "FK_category_price_movie"))
    private PriceCategory price_category;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private BonusCategory bonus_category;

}

