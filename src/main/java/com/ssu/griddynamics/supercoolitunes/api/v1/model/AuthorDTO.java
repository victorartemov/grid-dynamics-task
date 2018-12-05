package com.ssu.griddynamics.supercoolitunes.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

    private String name;

    @JsonProperty("nickname")
    private String nickName;

    private String genre;

    private String country;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;
}
