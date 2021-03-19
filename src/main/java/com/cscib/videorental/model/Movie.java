package com.cscib.videorental.model;

import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Movie  implements Persistable<String> {

    @Id
    private String name;


    @Transient
    private boolean isNew = false;

    @Override
    public String getId() {
        return name;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}

