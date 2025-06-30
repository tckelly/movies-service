package com.github.tkelly.movies.service;

import com.github.tkelly.movies.Movie;
import com.github.tkelly.movies.dto.MovieRequest;
import com.github.tkelly.movies.dto.MovieResponse;
import com.github.tkelly.movies.exception.MovieNotFoundException;
import com.github.tkelly.movies.repository.MovieRepository;
import com.github.tkelly.movies.translator.MovieTranslator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional(readOnly = true)
    public MovieResponse getMovieById(final Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with movieId: " + movieId));

        return MovieTranslator.toResponse(movie);
    }

    @Transactional
    public MovieResponse saveMovie(MovieRequest movieRequest) {
        Movie movie = MovieTranslator.toEntity(movieRequest);
        return MovieTranslator.toResponse(movieRepository.save(movie));
    }

    @Transactional
    public void deleteMovieById(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new MovieNotFoundException("Movie with id " + movieId + " does not exist");
        }
        movieRepository.deleteById(movieId);
    }
}
