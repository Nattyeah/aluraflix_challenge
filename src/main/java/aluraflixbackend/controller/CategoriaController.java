package aluraflixbackend.controller;

import aluraflixbackend.model.Categoria;
import aluraflixbackend.repository.CategoriaRepository;
import aluraflixbackend.request.CategoriaRequest;
import aluraflixbackend.response.CategoriaResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CategoriaRepository repository;

    @GetMapping
    public List<CategoriaResponse> getAll() { return CategoriaResponse.converter(repository.findAll()); }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoriaResponse> getById(@PathVariable Long id) {
        Optional<Categoria> categoria = repository.findById(id);
        if(categoria.isPresent()) {
            return ResponseEntity.ok(new CategoriaResponse(categoria.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> create(@RequestBody @Valid CategoriaRequest request, UriComponentsBuilder builder) {
        Categoria categoria = request.converter();
        repository.save(categoria);

        URI uri = builder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaResponse(categoria));
    }
}
