package com.cscib.videorental.api;

import com.cscib.videorental.core.service.MovieService;
import com.cscib.videorental.data.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MoviesController {

    @Autowired
    private MovieService movieService;

    @PostMapping(value = "/v1/videorental/createMovie", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/json")
    public void createMovie(@RequestBody Movie movie) {
        movieService.saveMovie(movie);
    }

    @GetMapping("/v1/videorental/{movieId}/movie")
    Movie getMovie(@PathVariable String movieId) {
        return movieService.getMovie(movieId);
    }

    @GetMapping("/v1/videorental/movies")
    List<Movie> getMovies() {
        return movieService.getMovies();
    }

}
