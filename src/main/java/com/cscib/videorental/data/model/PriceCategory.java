package com.cscib.videorental.data.model;

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
@Table(name="price_category")
public class PriceCategory {

    @Id
    private String id;

    @OneToMany(mappedBy="price_category")
    private List<Movie> movies;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id",foreignKey = @ForeignKey(name = "FK_category_price"))
    private Price price;

    // basic, premium
    private String type;


}

