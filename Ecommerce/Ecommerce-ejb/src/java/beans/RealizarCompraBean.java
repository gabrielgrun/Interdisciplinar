/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.PedidoDAO;
import dao.PedidoItemDAO;
import exceptions.AppException;
import java.sql.SQLException;
import javax.ejb.Stateless;
import model.Cliente;
import model.Pedido;
import model.PedidoItem;

/**
 *
 * @author Gabriel
 */
@Stateless
public class RealizarCompraBean implements RealizarCompraBeanRemote, RealizarCompraBeanLocal
{

    @Override
    public boolean realizarCompra(Pedido pedido, PedidoItem pedidoItem, Cliente cliente) throws AppException
    {

        if (pedidoItem.getcProduto().getQtde() > 0)
        {

            try
            {
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedidoDAO.inserirPedido(pedido, cliente);

                PedidoItemDAO pedidoItemDAO = new PedidoItemDAO();
                pedidoItemDAO.save(pedidoItem);
            } catch (SQLException ex)
            {
                throw new AppException("Ocorreu um erro! Contate o suporte!", ex);
            } catch (Exception ex)
            {
                throw new AppException("Ocorreu um erro! Contate o suporte!", ex);
            }
            return true;
        } else
        {
            return false;
        }
    }

}
