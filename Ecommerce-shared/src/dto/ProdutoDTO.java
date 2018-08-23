package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

/**
 *
 * @author Gabriel
 */
public class ProdutoDTO implements Serializable
{

    @JsonIgnore
    private int cProduto;
    private String nome;
    private String descricao;
    private String foto;
    private int qtde;
    private double preco;
    private double promocao;
    private CategoriaDTO cCategoria;
    private MarcaDTO cMarca;

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public int getcProduto()
    {
        return cProduto;
    }

    public void setcProduto(int cProduto)
    {
        this.cProduto = cProduto;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getFoto()
    {
        return foto;
    }

    public void setFoto(String foto)
    {
        this.foto = foto;
    }

    public int getQtde()
    {
        return qtde;
    }

    public void setQtde(int qtde)
    {
        this.qtde = qtde;
    }

    public double getPreco()
    {
        return preco;
    }

    public void setPreco(double preco)
    {
        this.preco = preco;
    }

    public double getPromocao()
    {
        return promocao;
    }

    public void setPromocao(double promocao)
    {
        this.promocao = promocao;
    }

    public CategoriaDTO getcCategoria()
    {
        return cCategoria;
    }

    public void setcCategoria(CategoriaDTO cCategoria)
    {
        this.cCategoria = cCategoria;
    }

    public MarcaDTO getcMarca()
    {
        return cMarca;
    }

    public void setcMarca(MarcaDTO cMarca)
    {
        this.cMarca = cMarca;
    }

}
