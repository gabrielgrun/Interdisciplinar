/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ClienteDAO;
import dto.ClienteDTO;
import exceptions.AppException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Gabriel
 */
@Stateless
public class CadastrarClienteBean implements CadastrarClienteBeanRemote, CadastrarClienteBeanLocal
{

    @Override
    public void cadastrarCliente(ClienteDTO cliente) throws AppException
    {
        try
        {
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.inserirCliente(cliente);

        } catch (SQLException ex)
        {
            throw new AppException("Ocorreu um erro! Contate o suporte!", ex);
        } catch (Exception ex)
        {
            throw new AppException("Ocorreu um erro! Contate o suporte!", ex);
        }
    }

    @Override
    public ClienteDTO ultimoCliente() throws AppException
    {
        ClienteDTO cliente;
        try
        {
            ClienteDAO clienteDAO = new ClienteDAO();
            cliente = clienteDAO.ultimoCliente();
        } catch (Exception ex)
        {
            Logger.getLogger(TrazProdutosBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new AppException("Não foi possível retornar os clientes!", ex);
        }
        return cliente;
    }
}
