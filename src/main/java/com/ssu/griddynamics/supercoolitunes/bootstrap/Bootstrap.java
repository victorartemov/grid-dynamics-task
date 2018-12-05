package com.ssu.griddynamics.supercoolitunes.bootstrap;

import com.ssu.griddynamics.supercoolitunes.domain.Author;
import com.ssu.griddynamics.supercoolitunes.repositories.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Bootstrap implements CommandLineRunner {

    private AuthorRepository authorRepository;

    public Bootstrap(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //check http://localhost:8080/h2-console to see created tables

        loadSampleData();
    }

    private void loadSampleData() {

        Author author1 = new Author();
        author1.setName("Eric Patrick Clapton");
        author1.setCountry("England");
        author1.setGenre("Blues");
        author1.setNickName("Clapton");
        author1.setDateOfBirth(new Date(1945, 4, 30));

        Author author2 = new Author();
        author2.setName("Farrokh Bulsara");
        author2.setCountry("England");
        author2.setGenre("Rock");
        author2.setNickName("Freddie Mercury");
        author2.setDateOfBirth(new Date(1946, 9, 5));

        authorRepository.save(author1);
        authorRepository.save(author2);
    }
}
