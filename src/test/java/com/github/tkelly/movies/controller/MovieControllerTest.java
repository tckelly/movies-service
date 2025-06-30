package com.github.tkelly.movies.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tkelly.movies.dto.MovieRequest;
import com.github.tkelly.movies.dto.MovieResponse;
import com.github.tkelly.movies.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
class MovieControllerTest {
    private static final String TITLE = "Oppenheimer";
    private static final int RELEASE_YEAR = 2023;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MovieService movieService;

    private MovieRequest movieRequest;
    private MovieResponse movieResponse;

    @BeforeEach
    void setUp() {
        movieRequest = new MovieRequest();
        movieRequest.setTitle(TITLE);
        movieRequest.setReleaseYear(RELEASE_YEAR);

        movieResponse = new MovieResponse();
        movieResponse.setId(1L);
        movieResponse.setTitle(TITLE);
        movieResponse.setReleaseYear(RELEASE_YEAR);
    }

    @Test
    void shouldCreateMovie() throws Exception {
        when(movieService.saveMovie(any(MovieRequest.class))).thenReturn(movieResponse);

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(TITLE)))
                .andExpect(jsonPath("$.releaseYear", is(RELEASE_YEAR)));
    }

    @Test
    void shouldReadMovieById() throws Exception {
        when(movieService.getMovieById(1L)).thenReturn(movieResponse);

        mockMvc.perform(get("/movies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(TITLE)))
                .andExpect(jsonPath("$.releaseYear", is(RELEASE_YEAR)));
    }

    @Test
    void shouldUpdateMovie() throws Exception {
        when(movieService.saveMovie(any(MovieRequest.class))).thenReturn(movieResponse);

        mockMvc.perform(put("/movies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(TITLE)))
                .andExpect(jsonPath("$.releaseYear", is(RELEASE_YEAR)));
    }

    @Test
    void shouldDeleteMovie() throws Exception {
        mockMvc.perform(delete("/movies/1"))
                .andExpect(status().isNoContent());
    }
}