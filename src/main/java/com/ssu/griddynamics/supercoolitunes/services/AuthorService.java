package com.ssu.griddynamics.supercoolitunes.services;

import com.ssu.griddynamics.supercoolitunes.api.v1.model.AuthorDTO;

import java.util.List;

public interface AuthorService {

    List<AuthorDTO> findAll();

    AuthorDTO createNewAuthor(AuthorDTO authorDTO);

    AuthorDTO findByNickName(String nickName);

    void deleteAuthorById(Long id);

    void deleteAll();

    AuthorDTO findById(Long id);
}
