package com.ssu.griddynamics.supercoolitunes.services.impl;

import com.ssu.griddynamics.supercoolitunes.api.v1.mapper.AuthorMapper;
import com.ssu.griddynamics.supercoolitunes.api.v1.model.AuthorDTO;
import com.ssu.griddynamics.supercoolitunes.domain.Author;
import com.ssu.griddynamics.supercoolitunes.exception.ResourceNotFoundException;
import com.ssu.griddynamics.supercoolitunes.repositories.AuthorRepository;
import com.ssu.griddynamics.supercoolitunes.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorDTO> findAll() {
        return authorRepository
                .findAll()
                .stream()
                .map(customer -> authorMapper.authorToAuthorDTO(customer))
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO createNewAuthor(AuthorDTO authorDTO) {
        Author savedAuthor = authorRepository.save(authorMapper.authorDTOtoAuthor(authorDTO));

        return authorMapper.authorToAuthorDTO(savedAuthor);
    }

    @Override
    public AuthorDTO findByNickName(String nickName) {
        return authorRepository
                .findByNickName(nickName)
                .map(authorMapper::authorToAuthorDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        authorRepository.deleteAll();
    }
}
