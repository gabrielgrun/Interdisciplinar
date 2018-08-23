package dto;

import java.io.Serializable;

/**
 *
 * @author Gabriel
 */
public class PedidoItemDTO implements Serializable
{

    private int cPedidoItem;
    private int qtde;
    private double valor;
    private ProdutoDTO cProduto;
    private PedidoDTO cPedido;

    public int getcPedidoItem()
    {
        return cPedidoItem;
    }

    public void setcPedidoItem(int cPedidoItem)
    {
        this.cPedidoItem = cPedidoItem;
    }

    public int getQtde()
    {
        return qtde;
    }

    public void setQtde(int qtde)
    {
        this.qtde = qtde;
    }

    public double getValor()
    {
        return valor;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }

    public ProdutoDTO getcProduto()
    {
        return cProduto;
    }

    public void setcProduto(ProdutoDTO cProduto)
    {
        this.cProduto = cProduto;
    }

    public PedidoDTO getcPedido()
    {
        return cPedido;
    }

    public void setcPedido(PedidoDTO cPedido)
    {
        this.cPedido = cPedido;
    }

}
