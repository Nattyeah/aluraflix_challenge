package aluraflixbackend.response;

import aluraflixbackend.model.Categoria;
import aluraflixbackend.model.Video;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaResponse {

    private Long id;
    private String titulo;
    private String cor;

    public CategoriaResponse(Categoria categoria) {
        this.id = categoria.getId();
        this.titulo = categoria.getTitulo();
        this.cor = categoria.getCor();

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Long getId() {
        return id;
    }

    public static List<CategoriaResponse> converter(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaResponse::new).collect(Collectors.toList());
    }
}
