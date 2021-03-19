package com.cscib.videorental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RentalTypes implements Persistable<Integer> {

    @Id
    private Integer type;

    private String desc;

    @Transient
    private boolean isNew = false;

    @Override
    public Integer getId() {
        return type;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}

