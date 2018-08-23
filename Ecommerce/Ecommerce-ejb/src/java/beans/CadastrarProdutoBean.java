/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ProdutoDAO;
import exceptions.AppException;
import java.sql.SQLException;
import javax.ejb.Stateless;
import dto.ProdutoDTO;

/**
 *
 * @author Gabriel
 */
@Stateless
public class CadastrarProdutoBean implements CadastrarProdutoBeanRemote, CadastrarProdutoBeanLocal
{

    @Override
    public void cadastrarProduto(ProdutoDTO produto) throws AppException
    {
        try
        {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.save(produto);
        } catch (SQLException ex)
        {
            throw new AppException("Ocorreu um erro! Contate o suporte!", ex);
        } catch (Exception ex)
        {
            throw new AppException("Ocorreu um erro! Contate o suporte!", ex);
        }
    }
}
