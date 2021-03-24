package com.cscib.videorental.data.repository;

import com.cscib.videorental.data.model.Surcharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurchargeRepository extends JpaRepository<Surcharge, Integer> {
}

