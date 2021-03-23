package com.cscib.videorental.data.repository;

import com.cscib.videorental.data.model.BonusCategory;
import com.cscib.videorental.data.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusCategoryRepository extends JpaRepository<BonusCategory, String> {
}

