/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dto.PedidoItemDTO;
import exceptions.AppException;
import javax.ejb.Remote;

/**
 *
 * @author Gabriel
 */
@Remote
public interface CadastrarPedidoItemBeanRemote
{

    public void cadastrarPedidoItem(PedidoItemDTO pedidoItem) throws AppException;
}
