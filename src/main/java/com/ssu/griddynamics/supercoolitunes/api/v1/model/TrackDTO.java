package com.ssu.griddynamics.supercoolitunes.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackDTO {

    private String duration;

    private String title;

    private String year;

    private AuthorDTO author;
}
