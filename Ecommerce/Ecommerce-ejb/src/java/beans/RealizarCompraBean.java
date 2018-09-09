/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.PedidoDAO;
import exceptions.AppException;
import java.sql.SQLException;
import javax.ejb.Stateless;
import dto.PedidoDTO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
@Stateless
public class RealizarCompraBean implements RealizarCompraBeanRemote, RealizarCompraBeanLocal
{

    @Override
    public void realizarCompra(PedidoDTO pedido) throws AppException
    {

        try
        {
            PedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.inserirPedido(pedido);

        } catch (SQLException ex)
        {
            throw new AppException("Ocorreu um erro! Contate o suporte!", ex);
        } catch (Exception ex)
        {
            throw new AppException("Ocorreu um erro! Contate o suporte!", ex);
        }
    }

    @Override
    public PedidoDTO ultimoPedido() throws AppException
    {
        PedidoDTO pedido;
        try
        {
            PedidoDAO clienteDAO = new PedidoDAO();
            pedido = clienteDAO.ultimoPedido();
        } catch (Exception ex)
        {
            Logger.getLogger(TrazProdutosBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new AppException("Não foi possível retornar os produtos!", ex);
        }
        return pedido;
    }
}
