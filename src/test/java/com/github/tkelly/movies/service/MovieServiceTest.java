package com.github.tkelly.movies.service;

import com.github.tkelly.movies.Movie;
import com.github.tkelly.movies.dto.MovieRequest;
import com.github.tkelly.movies.dto.MovieResponse;
import com.github.tkelly.movies.exception.MovieNotFoundException;
import com.github.tkelly.movies.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    private static final String TITLE = "title";
    private static final int RELEASE_YEAR = 2000;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService subject;

    @Test
    void saveMovie() {
        MovieRequest movieRequest = getMovieRequest();
        Movie movie = getMovie();
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        MovieResponse actual = subject.saveMovie(movieRequest);

        assertNotNull(actual);
        assertEquals(TITLE, actual.getTitle());
        verify(movieRepository).save(any(Movie.class));
    }


    @Test
    void readMovieById() {
        Movie movie = getMovie();
        when(movieRepository.findById(123L)).thenReturn(Optional.of(movie));

        MovieResponse actual = subject.readMovieById(123L);

        assertNotNull(actual);
        assertEquals(TITLE, actual.getTitle());
        verify(movieRepository).findById(123L);
    }

    @Test
    void readMovieById_whenDoesNotExist_throwsException() {
        assertThrows(MovieNotFoundException.class, () -> subject.readMovieById(123L));

        verify(movieRepository).findById(123L);
    }

    @Test
    void deleteMovieById_whenExists() {
        long movieId = 345L;
        when(movieRepository.existsById(movieId)).thenReturn(Boolean.TRUE);

        subject.deleteMovieById(movieId);

        verify(movieRepository).deleteById(movieId);
    }

    @Test
    void deleteMovieById_whenDoesNotExist_throwsException() {
        long movieId = 345L;
        when(movieRepository.existsById(movieId)).thenReturn(Boolean.FALSE);

        assertThrows(MovieNotFoundException.class, () -> subject.deleteMovieById(movieId));

        verify(movieRepository, never()).deleteById(movieId);
    }

    private static MovieRequest getMovieRequest() {
        MovieRequest movieRequest = new MovieRequest();
        movieRequest.setTitle(TITLE);
        movieRequest.setReleaseYear(RELEASE_YEAR);
        return movieRequest;
    }

    private static Movie getMovie() {
        Movie movie = new Movie();
        movie.setReleaseYear(RELEASE_YEAR);
        movie.setTitle(TITLE);
        return movie;
    }
}