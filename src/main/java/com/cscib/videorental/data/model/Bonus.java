package com.cscib.videorental.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name="bonus")
public class Bonus{

    @Id
    private String name;

    @OneToOne(mappedBy="bonus")
    private BonusCategory category;

    private int points;

}

