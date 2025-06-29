package com.github.tkelly.movies.controller;

import com.github.tkelly.movies.dto.MovieRequest;
import com.github.tkelly.movies.dto.MovieResponse;
import com.github.tkelly.movies.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{movieId}")
    public MovieResponse getMovie(final @PathVariable Long movieId) {
        return movieService.readMovieById(movieId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieResponse saveMovie(final @RequestBody MovieRequest movieRequest) {
        return movieService.saveMovie(movieRequest);
    }

    @DeleteMapping("/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(final @PathVariable Long movieId) {
        movieService.deleteMovieById(movieId);
    }
}
