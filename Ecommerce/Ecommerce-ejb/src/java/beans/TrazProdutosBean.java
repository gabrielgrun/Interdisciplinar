/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import dto.CategoriaDTO;
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

    @Override
    public List<ProdutoDTO> filtrarCategoria(String categoria) throws AppException
    {
        List<ProdutoDTO> produto;
        try
        {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produto = produtoDAO.findByCategoria(categoria);
        } catch (Exception ex)
        {
            Logger.getLogger(TrazProdutosBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new AppException("Não foi possível retornar os produtos!", ex);
        }
        return produto;
    }

    @Override
    public List<ProdutoDTO> filtrarNome(String nome) throws AppException
    {
        List<ProdutoDTO> produto;
        try
        {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produto = produtoDAO.findByNome(nome);
        } catch (Exception ex)
        {
            Logger.getLogger(TrazProdutosBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new AppException("Não foi possível retornar os produtos!", ex);
        }
        return produto;
    }

    @Override
    public List<CategoriaDTO> listarCategorias() throws AppException
    {
        List<CategoriaDTO> categoria;
        try
        {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            categoria = categoriaDAO.findAll();
        } catch (Exception ex)
        {
            Logger.getLogger(TrazProdutosBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new AppException("Não foi possível retornar os produtos!", ex);
        }
        return categoria;
    }

    @Override
    public ProdutoDTO filtrarID(int id) throws AppException
    {
        ProdutoDTO produto;
        try
        {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produto = produtoDAO.findById(id);
        } catch (Exception ex)
        {
            Logger.getLogger(TrazProdutosBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new AppException("Não foi possível retornar os produtos!", ex);
        }
        return produto;
    }
}
