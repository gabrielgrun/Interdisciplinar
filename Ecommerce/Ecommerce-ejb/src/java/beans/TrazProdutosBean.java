/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ProdutoDAO;
import dto.ProdutoDTO;
import exceptions.AppException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Gabriel
 */
@Stateless
public class TrazProdutosBean implements TrazProdutosBeanRemote, TrazProdutosBeanLocal
{

    @Override
    public List<ProdutoDTO> listarProdutos() throws AppException
    {
        List<ProdutoDTO> produto;
        try
        {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produto = produtoDAO.findAll();
        } catch (Exception ex)
        {
            Logger.getLogger(TrazProdutosBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new AppException("Não foi possível retornar os produtos!", ex);
        }
        return produto;
    }
}