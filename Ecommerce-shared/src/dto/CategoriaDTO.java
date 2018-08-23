package dto;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

/**
 *
 * @author Gabriel
 */
public class CategoriaDTO implements Serializable
{

    @JsonIdentityReference(alwaysAsId = true)
    private int cCategoria;
    @JsonIgnore

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
