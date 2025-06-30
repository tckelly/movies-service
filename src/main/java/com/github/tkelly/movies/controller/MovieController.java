package com.github.tkelly.movies.controller;

import com.github.tkelly.movies.dto.MovieRequest;
import com.github.tkelly.movies.dto.MovieResponse;
import com.github.tkelly.movies.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(
            summary = "Create a new movie",
            description = "Adds a new movie to the database using the provided details."
    )
    @ApiResponse(responseCode = "201", description = "Movie created successfully")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieResponse createMovie(final @Valid @RequestBody MovieRequest movieRequest) {
        return movieService.saveMovie(movieRequest);
    }

    @Operation(
            summary = "Get a movie by ID",
            description = "Retrieves a movie by its ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movie retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    @GetMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public MovieResponse readMovie(final @PathVariable Long movieId) {
        return movieService.getMovieById(movieId);
    }

    @Operation(
            summary = "Update an existing movie",
            description = "Updates a movie's data. The movie must already exist and the ID must be provided."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movie updated successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    @PutMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public MovieResponse updateMovie(
            @PathVariable Long movieId,
            @Valid @RequestBody MovieRequest request
    ) {
        request.setId(movieId);
        return movieService.saveMovie(request);
    }

    @Operation(
            summary = "Delete a movie by ID",
            description = "Deletes a movie and returns the deleted movie's details."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movie deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    @DeleteMapping("/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(final @PathVariable Long movieId) {
        movieService.deleteMovieById(movieId);
    }
}
