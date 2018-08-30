/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dto.ProdutoDTO;
import exceptions.AppException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Gabriel
 */
@Remote
public interface TrazProdutosBeanRemote
{

    public List<ProdutoDTO> listarProdutos() throws AppException;
}
