package com.cscib.videorental.data.repository;

import com.cscib.videorental.data.model.PriceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceCategoryRepository extends JpaRepository<PriceCategory, Integer> {
}

