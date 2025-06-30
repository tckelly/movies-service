package com.github.tkelly.movies.repository;

import com.github.tkelly.movies.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase
@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void shouldSaveAndLoadMovie() {
        Movie movie = new Movie();
        movie.setTitle("Oppenheimer");

        Movie saved = movieRepository.save(movie);
        Optional<Movie> loaded = movieRepository.findById(saved.getId());

        assertTrue(loaded.isPresent());
        assertEquals("Oppenheimer", loaded.get().getTitle());
    }
}