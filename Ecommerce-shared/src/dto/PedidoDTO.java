package dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Gabriel
 */
public class PedidoDTO implements Serializable
{

    private int cPedido;
    private Date data;
    private double valor;
    private ClienteDTO cliente;

    public ClienteDTO getCliente()
    {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente)
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
