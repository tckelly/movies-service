package com.github.tkelly.movies.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MovieRequest {
    private long id;

    @NotBlank(message = "Title cannot be blank.")
    private String title;

    @NotNull(message = "Release year cannot be null.")
    @Min(value = 1888, message = "Release year must be no earlier than 1888")
    @Max(value = 2100, message = "Release year must be no later than 2100")
    private Integer releaseYear;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
