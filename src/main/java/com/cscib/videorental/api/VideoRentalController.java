package com.cscib.videorental.api;

import com.cscib.videorental.api.dto.RentMoviesDTO;
import com.cscib.videorental.api.dto.RentMoviesResponseDTO;
import com.cscib.videorental.api.dto.ReturnMoviesDTO;
import com.cscib.videorental.api.dto.ReturnMoviesResponseDTO;
import com.cscib.videorental.core.service.RentMoviesService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class VideoRentalController {

    @Autowired
    private RentMoviesService rentMoviesService;

    @SneakyThrows
    @PostMapping(value = "/v1/rental/rent", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/json")
    public RentMoviesResponseDTO rentMovie(@RequestBody RentMoviesDTO rentMoviesDTO) {
        return rentMoviesService.rentMovies(rentMoviesDTO);
    }

    @SneakyThrows
    @PostMapping(value = "/v1/rental/return", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/json")
    public ReturnMoviesResponseDTO returnMovie(@RequestBody ReturnMoviesDTO returnMoviesDTO) {
        return rentMoviesService.returnMovies(returnMoviesDTO);
    }

}
