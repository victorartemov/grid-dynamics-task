package com.ssu.griddynamics.supercoolitunes.controllers.v1;

import com.ssu.griddynamics.supercoolitunes.api.v1.model.AlbumListDTO;
import com.ssu.griddynamics.supercoolitunes.services.AlbumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AlbumController.BASE_URL)
public class AlbumController {

    public static final String BASE_URL = "/api/v1/albums";

    private AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public AlbumListDTO getAllAlbums() {
        return new AlbumListDTO(albumService.findAll());
    }
}
