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
@Table(name="bonus_category")
public class BonusCategory {

    @Id
    private String id;

    @OneToMany(mappedBy="bonus_category")
    private List<Movie> movies;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bonus_id",foreignKey = @ForeignKey(name = "FK_category_price"))
    private Bonus bonus;

    // new release, regular, old
    private String type;


}

