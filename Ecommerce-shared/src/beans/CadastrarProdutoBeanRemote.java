/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import exceptions.AppException;
import javax.ejb.Remote;
import model.Produto;

/**
 *
 * @author Gabriel
 */
@Remote
public interface CadastrarProdutoBeanRemote
{

    public void cadastrarProduto(Produto produto) throws AppException;
}
