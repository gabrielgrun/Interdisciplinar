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
    private ClienteDTO cCliente;

    public ClienteDTO getcCliente()
    {
        return cCliente;
    }

    public void setcCliente(ClienteDTO cCliente)
    {
        this.cCliente = cCliente;
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
}
