package com.cscib.videorental.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name="category")
public class Category {

    @Id
    private String id;

    private Price price;

    private Bonus bonus;

    // product (basic, premium> or movie (new release, old)
    private String type;


}

