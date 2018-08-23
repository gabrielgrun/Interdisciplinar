package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 *
 * @author Gabriel
 */
public class MarcaDTO implements Serializable
{

    private int cMarca;
    @JsonIgnoreProperties(
            {
                "nome"
            })
    private String nome;

    public int getcMarca()
    {
        return cMarca;
    }

    public void setcMarca(int cMarca)
    {
        this.cMarca = cMarca;
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
