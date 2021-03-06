package aluraflixbackend.model;

import aluraflixbackend.request.VideoRequest;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_video")
public class Video {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty @NotNull
    private String titulo;
    @NotEmpty @NotNull
    private String descricao;
    @NotEmpty @NotNull
    private String url;

    private String categoriaId;

    public Video() { super(); }

    public Video(@NotEmpty @NotNull String titulo, @NotEmpty @NotNull String descricao, @NotEmpty @NotNull String url, String categoriaId) {
        super();
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.categoriaId = categoriaId;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void atualizar(@Valid VideoRequest request) {
        this.titulo = request.getTitulo();
        this.descricao = request.getDescricao();
        this.url = request.getUrl();
        this.url = request.getCategoriaId();
    }
}
