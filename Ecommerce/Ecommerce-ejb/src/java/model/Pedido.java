package model;

import java.sql.Date;

/**
 *
 * @author Gabriel
 */
public class Pedido
{

    private int cPedido;
    private Date data;
    private double valor;
    private Cliente cliente;

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public int getcPedido()
    {
        return cPedido;
    }

    public void setcPedido(int cPedido)
    {
        this.cPedido = cPedido;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public double getValor()
    {
        return valor;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }

}
