package com.cscib.videorental.data.init;

import com.univocity.parsers.annotations.Parsed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieDTO {


    @Parsed
    private String name;

    @Parsed
    private String bonusCategory;
}

