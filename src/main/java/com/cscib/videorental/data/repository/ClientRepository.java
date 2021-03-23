package com.cscib.videorental.data.repository;

import com.cscib.videorental.data.model.Client;
import com.cscib.videorental.data.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
}

