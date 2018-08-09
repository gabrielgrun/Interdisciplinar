package model;

/**
 *
 * @author Gabriel
 */
public class Categoria {
    private int cCategoria;
    private String nome;
    private Marca cMarca;

    public int getcCategoria() {
        return cCategoria;
    }

    public void setcCategoria(int cCategoria) {
        this.cCategoria = cCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Marca getcMarca() {
        return cMarca;
    }

    public void setcMarca(Marca cMarca) {
        this.cMarca = cMarca;
    }
    
    
}
