package br.org.rpessoa.vstore.controller;

import br.org.rpessoa.vstore.dao.GenericDAO;
import br.org.rpessoa.vstore.model.Media;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/media")
public class MediaController {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> add(@RequestParam("picture") MultipartFile file)
            throws IOException {

        Media media = new Media();
        media.setData(file.getBytes());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(media.getUuid()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "{uuid}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> get(@PathVariable("uuid") UUID uuid) {
        GenericDAO<Media> mediaDAO = new GenericDAO<>();
        Media media = mediaDAO.findById(Media.class, uuid);
        return ResponseEntity.ok(media);
    }
}
