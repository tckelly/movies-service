package com.github.tkelly.movies.translator;

import com.github.tkelly.movies.Movie;
import com.github.tkelly.movies.dto.MovieRequest;
import com.github.tkelly.movies.dto.MovieResponse;

public class MovieTranslator {
    private MovieTranslator() {}

    public static Movie toEntity(MovieRequest request) {
        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setReleaseYear(request.getReleaseYear());
        return movie;
    }

    public static MovieResponse toResponse(Movie movie) {
        MovieResponse response = new MovieResponse();
        response.setId(movie.getId());
        response.setTitle(movie.getTitle());
        response.setReleaseYear(movie.getReleaseYear());
        return response;
    }
}