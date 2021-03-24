package com.cscib.videorental.data.repository;

import com.cscib.videorental.data.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Query("SELECT DISTINCT r FROM Rental r LEFT JOIN r.movie WHERE r.movie.name =:movie")
    public List<Rental> find(@Param("movie") String movie);
}

