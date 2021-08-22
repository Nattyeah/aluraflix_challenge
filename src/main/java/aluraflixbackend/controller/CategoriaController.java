package aluraflixbackend.controller;

import aluraflixbackend.request.CategoriaRequest;
import aluraflixbackend.response.CategoriaResponse;
import aluraflixbackend.response.VideoResponse;
import aluraflixbackend.service.CategoriaService;
import aluraflixbackend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private VideoService videoService;

    @GetMapping
    public List<?> getAll(String embed) {
        return categoriaService.list(embed);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoriaResponse> getById(@PathVariable Long id) {
        Optional<CategoriaResponse> categoria = categoriaService.detail(id);
        return categoria.map(c -> ResponseEntity.ok(c)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{id}/videos")
    public List<VideoResponse> getByCategory(@PathVariable Long id) {
        return videoService.getByCategory(id);
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> create(@RequestBody @Valid CategoriaRequest request, UriComponentsBuilder builder) {
        CategoriaResponse categoria = categoriaService.save(request);

        URI uri = builder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CategoriaResponse> update(@RequestBody @Valid CategoriaRequest request, @PathVariable Long id) {
        Optional<CategoriaResponse> categoria = categoriaService.update(request, id);

        return categoria.map(c -> ResponseEntity.ok(c)).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CategoriaResponse> delete(@PathVariable Long id) {
        try {
            categoriaService.delete(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
