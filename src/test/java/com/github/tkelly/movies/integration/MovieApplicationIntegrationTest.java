package com.github.tkelly.movies.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tkelly.movies.dto.MovieRequest;
import com.github.tkelly.movies.dto.MovieResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MovieApplicationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAndFetchMovie() throws Exception {
        MovieRequest request = new MovieRequest();
        request.setId(1L);
        request.setTitle("Oppenheimer");
        request.setReleaseYear(2023);

        String responseBody = mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        MovieResponse created = objectMapper.readValue(responseBody, MovieResponse.class);

        mockMvc.perform(get("/movies/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Oppenheimer"))
                .andExpect(jsonPath("$.releaseYear").value(2023));

        assertEquals("Oppenheimer", created.getTitle());
    }
}
