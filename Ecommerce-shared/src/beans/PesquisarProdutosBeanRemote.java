/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import exceptions.AppException;
import javax.ejb.Remote;
import dto.ProdutoDTO;

/**
 *
 * @author Gabriel
 */
@Remote
public interface PesquisarProdutosBeanRemote
{

    public ProdutoDTO pesquisar(String jsonJs) throws AppException;

}
