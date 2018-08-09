package model;

/**
 *
 * @author Gabriel
 */
public class PedidoItem {
    private int cPedidoItem;
    private int qtde;
    private double valor;
    private Produto cProduto;
    private Pedido cPedido;

    public int getcPedidoItem() {
        return cPedidoItem;
    }

    public void setcPedidoItem(int cPedidoItem) {
        this.cPedidoItem = cPedidoItem;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Produto getcProduto() {
        return cProduto;
    }

    public void setcProduto(Produto cProduto) {
        this.cProduto = cProduto;
    }

    public Pedido getcPedido() {
        return cPedido;
    }

    public void setcPedido(Pedido cPedido) {
        this.cPedido = cPedido;
    }
    
    
}
