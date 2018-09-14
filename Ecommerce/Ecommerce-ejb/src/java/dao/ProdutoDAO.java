package dao;

import exceptions.AppException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.CategoriaDTO;
import dto.MarcaDTO;
import dto.ProdutoDTO;
import util.ConnectionUtil;

/**
 *
 * @author Gabriel
 */
public class ProdutoDAO
{

    private Connection connection;

    public ProdutoDAO() throws Exception
    {
        connection = ConnectionUtil.getConnection();
    }

    public void save(ProdutoDTO produto) throws AppException
    {

        try
        {
            String SQL = "INSERT INTO PRODUTO(NOME, FOTO, QTDE, PRECO, PROMOCAO, CCATEGORIA, CMARCA, DESCRICAO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, produto.getNome());
            p.setString(2, produto.getFoto());
            p.setInt(3, produto.getQtde());
            p.setDouble(4, produto.getPreco());
            p.setDouble(5, produto.getPromocao());
            p.setInt(6, produto.getcCategoria().getcCategoria());
            p.setInt(7, produto.getcMarca().getcMarca());
            p.setString(8, produto.getDescricao());
            p.execute();
            p.close();

        } catch (SQLException ex)
        {
            throw new AppException("Ocorreu um erro ao inserir. Tente novamente, caso o erro persista contate o suporte.", ex);
        }
    }

    public ProdutoDTO findById(int id) throws Exception
    {
        ProdutoDTO objeto = new ProdutoDTO();
        String SQL = " SELECT PRODUTO.*, CATEGORIA.NOME, MARCA.NOME "
                + "   FROM PRODUTO "
                + "   INNER JOIN CATEGORIA ON CATEGORIA.CCATEGORIA = PRODUTO.CCATEGORIA "
                + "   INNER JOIN MARCA ON MARCA.CMARCA = PRODUTO.CMARCA "
                + "   WHERE PRODUTO.CPRODUTO = " + id
                + "   ORDER BY PRODUTO.CPRODUTO ";

        try
        {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();

            while (rs.next())
            {

                objeto = new ProdutoDTO();
                objeto.setcProduto(rs.getInt("CPRODUTO"));
                objeto.setNome(rs.getString("NOME"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                objeto.setFoto(rs.getString("FOTO"));
                objeto.setQtde(rs.getInt("QTDE"));
                objeto.setPreco(rs.getDouble("PRECO"));
                objeto.setPromocao(rs.getDouble("PROMOCAO"));

                CategoriaDTO categoria = new CategoriaDTO();
                categoria.setcCategoria(rs.getInt("CCATEGORIA"));
                categoria.setNome(rs.getString("NOME"));

                MarcaDTO marca = new MarcaDTO();
                marca.setcMarca(rs.getInt("CMARCA"));
                marca.setNome(rs.getString("NOME"));

                objeto.setcCategoria(categoria);
                objeto.setcMarca(marca);
            }
            rs.close();
            p.close();

        } catch (SQLException ex)
        {
            throw new AppException("Ocorreu um erro ao consultar. Tente novamente, caso o erro persista contate o suporte.", ex);
        }

        return objeto;
    }

    public List<ProdutoDTO> findByNome(String nome) throws Exception
    {
        ProdutoDTO objeto = new ProdutoDTO();
        List<ProdutoDTO> list = new ArrayList<>();
        String SQL = "SELECT PRODUTO.*, CATEGORIA.NOME, MARCA.NOME"
                + "   FROM PRODUTO "
                + "   INNER JOIN CATEGORIA ON CATEGORIA.CCATEGORIA = PRODUTO.CCATEGORIA "
                + "   INNER JOIN MARCA ON MARCA.CMARCA = PRODUTO.CMARCA "
                + "   WHERE PRODUTO.NOME LIKE '%" + nome + "%'"
                + "   ORDER BY PRODUTO.CPRODUTO ";

        try
        {
            PreparedStatement p = connection.prepareStatement(SQL);

            ResultSet rs = p.executeQuery();

            while (rs.next())
            {

                objeto = new ProdutoDTO();
                objeto.setcProduto(rs.getInt("CPRODUTO"));
                objeto.setNome(rs.getString("NOME"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                objeto.setFoto(rs.getString("FOTO"));
                objeto.setQtde(rs.getInt("QTDE"));
                objeto.setPreco(rs.getDouble("PRECO"));
                objeto.setPromocao(rs.getDouble("PROMOCAO"));

                CategoriaDTO categoria = new CategoriaDTO();
                categoria.setcCategoria(rs.getInt("CCATEGORIA"));
                categoria.setNome(rs.getString("NOME"));

                MarcaDTO marca = new MarcaDTO();
                marca.setcMarca(rs.getInt("CMARCA"));
                marca.setNome(rs.getString("NOME"));

                objeto.setcCategoria(categoria);
                objeto.setcMarca(marca);

                list.add(objeto);
            }
            rs.close();
            p.close();

        } catch (SQLException ex)
        {
            throw new AppException("Ocorreu um erro ao consultar. Tente novamente, caso o erro persista contate o suporte.", ex);
        }

        return list;
    }

    public List<ProdutoDTO> findAll() throws Exception
    {

        List<ProdutoDTO> list = new ArrayList<>();
        ProdutoDTO objeto;
        String SQL = "SELECT PRODUTO.*, CATEGORIA.NOME, MARCA.NOME"
                + "   FROM PRODUTO "
                + "   INNER JOIN CATEGORIA ON CATEGORIA.CCATEGORIA = PRODUTO.CCATEGORIA "
                + "   INNER JOIN MARCA ON MARCA.CMARCA = PRODUTO.CMARCA "
                + "   ORDER BY CPRODUTO";

        try
        {
            PreparedStatement p = connection.prepareStatement(SQL);

            ResultSet rs = p.executeQuery();

            while (rs.next())
            {

                objeto = new ProdutoDTO();
                objeto.setcProduto(rs.getInt("CPRODUTO"));
                objeto.setNome(rs.getString("NOME"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                objeto.setFoto(rs.getString("FOTO"));
                objeto.setQtde(rs.getInt("QTDE"));
                objeto.setPreco(rs.getDouble("PRECO"));
                objeto.setPromocao(rs.getDouble("PROMOCAO"));

                CategoriaDTO categoria = new CategoriaDTO();
                categoria.setcCategoria(rs.getInt("CCATEGORIA"));
                categoria.setNome(rs.getString("NOME"));

                MarcaDTO marca = new MarcaDTO();
                marca.setcMarca(rs.getInt("CMARCA"));
                marca.setNome(rs.getString("NOME"));

                objeto.setcCategoria(categoria);
                objeto.setcMarca(marca);

                list.add(objeto);
            }
            rs.close();
            p.close();

        } catch (SQLException ex)
        {
            throw new AppException("Ocorreu um erro ao consultar. Tente novamente, caso o erro persista contate o suporte.", ex);
        }

        return list;
    }

    public List<ProdutoDTO> findByCategoria(String categoria) throws Exception
    {

        List<ProdutoDTO> list = new ArrayList<>();
        ProdutoDTO objeto;
        String SQL = "SELECT PRODUTO.*, CATEGORIA.NOME, MARCA.NOME"
                + "   FROM PRODUTO "
                + "   INNER JOIN CATEGORIA ON CATEGORIA.CCATEGORIA = PRODUTO.CCATEGORIA "
                + "   INNER JOIN MARCA ON MARCA.CMARCA = PRODUTO.CMARCA "
                + "   WHERE CATEGORIA.NOME ='" + categoria + "'"
                + "   ORDER BY CPRODUTO";

        try
        {
            PreparedStatement p = connection.prepareStatement(SQL);

            ResultSet rs = p.executeQuery();

            while (rs.next())
            {

                objeto = new ProdutoDTO();
                objeto.setcProduto(rs.getInt("CPRODUTO"));
                objeto.setNome(rs.getString("NOME"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                objeto.setFoto(rs.getString("FOTO"));
                objeto.setQtde(rs.getInt("QTDE"));
                objeto.setPreco(rs.getDouble("PRECO"));
                objeto.setPromocao(rs.getDouble("PROMOCAO"));

                CategoriaDTO categorias = new CategoriaDTO();
                categorias.setcCategoria(rs.getInt("CCATEGORIA"));
                categorias.setNome(rs.getString("NOME"));

                MarcaDTO marca = new MarcaDTO();
                marca.setcMarca(rs.getInt("CMARCA"));
                marca.setNome(rs.getString("NOME"));

                objeto.setcCategoria(categorias);
                objeto.setcMarca(marca);

                list.add(objeto);
            }
            rs.close();
            p.close();

        } catch (SQLException ex)
        {
            throw new AppException("Ocorreu um erro ao consultar. Tente novamente, caso o erro persista contate o suporte.", ex);
        }

        return list;
    }
}
