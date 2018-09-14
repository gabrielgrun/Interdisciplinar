/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import exceptions.AppException;
import javax.ejb.Remote;
import dto.PedidoDTO;
import java.util.List;

/**
 *
 * @author Gabriel
 */
@Remote
public interface RealizarCompraBeanRemote
{

    public void realizarCompra(PedidoDTO pedido) throws AppException;

    public PedidoDTO ultimoPedido() throws AppException;

    public List<PedidoDTO> criarRel() throws AppException;
}
