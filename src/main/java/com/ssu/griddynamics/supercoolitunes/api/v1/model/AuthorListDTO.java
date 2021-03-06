package com.ssu.griddynamics.supercoolitunes.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorListDTO {
    List<AuthorDTO> authors;
}
