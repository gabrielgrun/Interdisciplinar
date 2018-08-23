/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import exceptions.AppException;
import javax.ejb.Remote;
import dto.ClienteDTO;
import dto.PedidoDTO;
import dto.PedidoItemDTO;

/**
 *
 * @author Gabriel
 */
@Remote
public interface RealizarCompraBeanRemote
{

    public boolean realizarCompra(PedidoDTO pedido, PedidoItemDTO pedidoItem, ClienteDTO cliente) throws AppException;
}
