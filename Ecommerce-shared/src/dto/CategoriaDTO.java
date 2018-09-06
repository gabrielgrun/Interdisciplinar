package dto;

import java.io.Serializable;

/**
 *
 * @author Gabriel
 */
public class CategoriaDTO implements Serializable
{

    private int cCategoria;
    private String nome;

    public int getcCategoria()
    {
        return cCategoria;
    }

    public void setcCategoria(int cCategoria)
    {
        this.cCategoria = cCategoria;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }
}
