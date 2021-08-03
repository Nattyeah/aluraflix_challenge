package aluraflixbackend.model;

import aluraflixbackend.request.CategoriaRequest;
import aluraflixbackend.request.VideoRequest;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @NotNull
    private String titulo;
    @NotEmpty @NotNull
    private String cor;

    public Categoria() { super(); }

    public Categoria(@NotEmpty @NotNull String titulo, @NotEmpty @NotNull String cor) {
        super();
        this.titulo = titulo;
        this.cor = cor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void atualizar(@Valid CategoriaRequest request) {
        this.titulo = request.getTitulo();
        this.cor = request.getCor();
    }
}
