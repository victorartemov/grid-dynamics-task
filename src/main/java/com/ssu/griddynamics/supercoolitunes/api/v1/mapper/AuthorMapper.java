package com.ssu.griddynamics.supercoolitunes.api.v1.mapper;

import com.ssu.griddynamics.supercoolitunes.api.v1.model.AuthorDTO;
import com.ssu.griddynamics.supercoolitunes.domain.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO authorToAuthorDTO(Author author);

    Author authorDTOtoAuthor(AuthorDTO authorDTO);
}

