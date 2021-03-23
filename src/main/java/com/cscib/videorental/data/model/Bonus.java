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
@Table(name="bonus")
public class Bonus{

    @Id
    private String name;

    private Category category;

    private int points;

}

