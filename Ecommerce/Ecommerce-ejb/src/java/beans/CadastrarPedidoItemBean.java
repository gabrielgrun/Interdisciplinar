/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.PedidoItemDAO;
import dto.PedidoItemDTO;
import exceptions.AppException;
import java.sql.SQLException;
import javax.ejb.Stateless;

/**
 *
 * @author Gabriel
 */
@Stateless
public class CadastrarPedidoItemBean implements CadastrarPedidoItemBeanRemote, CadastrarPedidoItemBeanLocal
{

    public void cadastrarPedidoItem(PedidoItemDTO pedidoItem) throws AppException
    {
        try
        {
            PedidoItemDAO pedidoItemDAO = new PedidoItemDAO();
            pedidoItemDAO.save(pedidoItem);
        } catch (SQLException ex)
        {
            throw new AppException("Ocorreu um erro! Contate o suporte!", ex);
        } catch (Exception ex)
        {
            throw new AppException("Ocorreu um erro! Contate o suporte!", ex);
        }
    }
}
