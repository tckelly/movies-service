package com.github.tkelly.movies.service;

import com.github.tkelly.movies.Movie;
import com.github.tkelly.movies.repository.MovieRepository;
import com.github.tkelly.movies.exception.MovieNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Movie> readMovieById(final Long id) {
        return movieRepository.findById(id);
    }

    @Transactional
    public Movie saveMovie(Movie movie) {
        // todo use separate API objects and entity objects
        return movieRepository.save(movie);
    }

    @Transactional
    public void deleteMovieById(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new MovieNotFoundException("Movie with id " + movieId + " does not exist");
        }
        movieRepository.deleteById(movieId);
    }
}
