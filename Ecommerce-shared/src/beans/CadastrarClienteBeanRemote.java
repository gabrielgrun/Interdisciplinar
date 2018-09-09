/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dto.ClienteDTO;
import exceptions.AppException;
import javax.ejb.Remote;

/**
 *
 * @author Gabriel
 */
@Remote
public interface CadastrarClienteBeanRemote
{

    public void cadastrarCliente(ClienteDTO cliente) throws AppException;

    public ClienteDTO ultimoCliente() throws AppException;
}
