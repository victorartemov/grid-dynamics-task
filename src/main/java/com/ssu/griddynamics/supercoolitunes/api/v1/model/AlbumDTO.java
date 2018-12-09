package com.ssu.griddynamics.supercoolitunes.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDTO {

    @JsonProperty("is_single")
    private String isSingle;

    @JsonProperty("is_compilation")
    private String isCompilation;

    private String title;

    private String genre;

    @JsonProperty("date_of_production")
    private String dateOfProduction;

    private AuthorDTO author;

    private List<TrackDTO> tracks;
}
