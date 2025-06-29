package com.github.tkelly.movies.controller;

import com.github.tkelly.movies.Movie;
import com.github.tkelly.movies.exception.MovieNotFoundException;
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
    public Movie getMovie(final @PathVariable Long movieId) {
        return movieService.readMovieById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + movieId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie saveMovie(final @RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @DeleteMapping("/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(final @PathVariable Long movieId) {
        movieService.deleteMovieById(movieId);
    }
}
