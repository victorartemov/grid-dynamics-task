package com.ssu.griddynamics.supercoolitunes.controllers.v1;

import com.ssu.griddynamics.supercoolitunes.api.v1.model.AuthorDTO;
import com.ssu.griddynamics.supercoolitunes.api.v1.model.AuthorListDTO;
import com.ssu.griddynamics.supercoolitunes.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthorController.BASE_URL)
public class AuthorController {

    public static final String BASE_URL = "/api/v1/authors";

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AuthorListDTO getAllAuthors() {
        return new AuthorListDTO(authorService.findAll());
    }

    @GetMapping("/search/by-nickname")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO getAuthorByNickName(@RequestParam String nickName) {
        return authorService.findByNickName(nickName);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllAuthors() {
        authorService.deleteAll();
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteAuthorById(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO createNewAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.createNewAuthor(authorDTO);
    }

    @GetMapping("/search/by-id")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO findById(@RequestParam Long id) {
        return authorService.findById(id);
    }
}
