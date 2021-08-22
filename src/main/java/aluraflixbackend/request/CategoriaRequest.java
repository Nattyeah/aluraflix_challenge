package aluraflixbackend.request;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank(message = "O campo 'titulo' não pode ser vazio.")
    private String titulo;
    @NotBlank(message = "O campo 'cor' não pode ser vazio.")
    private String cor;

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
}
