package com.github.tkelly.movies.service;

import com.github.tkelly.movies.Movie;
import com.github.tkelly.movies.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService subject;

    @Test
    void saveMovie() {
        Movie movie = new Movie();
        when(movieRepository.save(movie)).thenReturn(movie);

        Movie actual = subject.saveMovie(movie);

        assertNotNull(actual);
        verify(movieRepository).save(any(Movie.class));
    }

    @Test
    void readMovieById() {
        when(movieRepository.findById(123L)).thenReturn(Optional.of(new Movie()));
        Movie movie = subject.readMovieById(123L).get();

        assertNotNull(movie);
        verify(movieRepository).findById(123L);
    }

    @Test
    void deleteMovieById() {
        Movie movie = new Movie();
        movie.setId(345L);
        when(movieRepository.existsById(345L)).thenReturn(Boolean.TRUE);

        subject.deleteMovieById(movie.getId());

        verify(movieRepository).deleteById(345L);
    }

}