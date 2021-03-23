package com.cscib.videorental.data.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name="movie")
public class Movie {

    @Id
    private String name;

    private Category category;

}

