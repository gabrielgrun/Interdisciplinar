package dao;

import exceptions.AppException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Marca;
import model.Produto;
import util.ConnectionUtil;

/**
 *
 * @author Gabriel
 */
public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public void save(Produto produto) throws AppException {

        try {
            String SQL = "INSERT INTO PRODUTO(NOME, FOTO, QTDE, PRECO, PROMOCAO, CCATEGORIA, CMARCA, DESCRICAO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, produto.getNome());
            p.setBytes(2, produto.getFoto());
            p.setInt(3, produto.getQtde());
            p.setDouble(4, produto.getPreco());
            p.setDouble(5, produto.getPromocao());
            p.setInt(6, produto.getcCategoria().getcCategoria());
            p.setInt(7, produto.getcMarca().getcMarca());
            p.setString(8, produto.getDescricao());
            p.execute();
            p.close();

        } catch (SQLException ex) {
            throw new AppException("Ocorreu um erro ao inserir. Tente novamente, caso o erro persista contate o suporte.", ex);
        }
    }

    public Produto findById(int id) throws Exception {
        Produto objeto = new Produto();
        String SQL = "SELECT PRODUTO.*, CATEGORIA.NOME, MARCA.NOME"
                + "   FROM PRODUTO "
                + "   INNER JOIN CATEGORIA ON CATEGORIA.CCATEGORIA = PRODUTO.CCATEGORIA "
                + "   INNER JOIN MARCA ON MARCA.CMARCA = PRODUTO.CMARCA "
                + "   ORDER BY CPRODUTO "
                + "   WHERE PRODUTO.CPRODUTO = ?";

        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, id);

            ResultSet rs = p.executeQuery();

            while (rs.next()) {

                objeto = new Produto();
                objeto.setcProduto(rs.getInt("CPRODUTO"));
                objeto.setNome(rs.getString("NOME"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                objeto.setFoto(rs.getBytes("FOTO"));
                objeto.setQtde(rs.getInt("QTDE"));
                objeto.setPreco(rs.getDouble("PRECO"));
                objeto.setPromocao(rs.getDouble("PROMOCAO"));

                Categoria categoria = new Categoria();
                categoria.setcCategoria(rs.getInt("CCATEGORIA"));
                categoria.setNome(rs.getString("NOME"));

                Marca marca = new Marca();
                marca.setcMarca(rs.getInt("CMARCA"));
                marca.setNome(rs.getString("NOME"));

                objeto.setcCategoria(categoria);
                objeto.setcMarca(marca);
            }
            rs.close();
            p.close();

        } catch (SQLException ex) {
            throw new AppException("Ocorreu um erro ao consultar. Tente novamente, caso o erro persista contate o suporte.", ex);
        }

        return objeto;
    }

    public Produto findByNome(String nome) throws Exception {
        Produto objeto = new Produto();
        String SQL = "SELECT PRODUTO.*, CATEGORIA.NOME, MARCA.NOME"
                + "   FROM PRODUTO "
                + "   INNER JOIN CATEGORIA ON CATEGORIA.CCATEGORIA = PRODUTO.CCATEGORIA "
                + "   INNER JOIN MARCA ON MARCA.CMARCA = PRODUTO.CMARCA "
                + "   ORDER BY CPRODUTO "
                + "   WHERE PRODUTO.NOME LIKE ?";

        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, "%" + nome + "%");

            ResultSet rs = p.executeQuery();

            while (rs.next()) {

                objeto = new Produto();
                objeto.setcProduto(rs.getInt("CPRODUTO"));
                objeto.setNome(rs.getString("NOME"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                objeto.setFoto(rs.getBytes("FOTO"));
                objeto.setQtde(rs.getInt("QTDE"));
                objeto.setPreco(rs.getDouble("PRECO"));
                objeto.setPromocao(rs.getDouble("PROMOCAO"));

                Categoria categoria = new Categoria();
                categoria.setcCategoria(rs.getInt("CCATEGORIA"));
                categoria.setNome(rs.getString("NOME"));

                Marca marca = new Marca();
                marca.setcMarca(rs.getInt("CMARCA"));
                marca.setNome(rs.getString("NOME"));

                objeto.setcCategoria(categoria);
                objeto.setcMarca(marca);
            }
            rs.close();
            p.close();

        } catch (SQLException ex) {
            throw new AppException("Ocorreu um erro ao consultar. Tente novamente, caso o erro persista contate o suporte.", ex);
        }

        return objeto;
    }

    public List<Produto> findAll() throws Exception {

        List<Produto> list = new ArrayList<>();
        Produto objeto;
        String SQL = "SELECT PRODUTO.*, CATEGORIA.NOME, MARCA.NOME"
                + "   FROM PRODUTO "
                + "   INNER JOIN CATEGORIA ON CATEGORIA.CCATEGORIA = PRODUTO.CCATEGORIA "
                + "   INNER JOIN MARCA ON MARCA.CMARCA = PRODUTO.CMARCA "
                + "   ORDER BY CPRODUTO";

        try {
            PreparedStatement p = connection.prepareStatement(SQL);

            ResultSet rs = p.executeQuery();

            while (rs.next()) {

                objeto = new Produto();
                objeto.setcProduto(rs.getInt("CPRODUTO"));
                objeto.setNome(rs.getString("NOME"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                objeto.setFoto(rs.getBytes("FOTO"));
                objeto.setQtde(rs.getInt("QTDE"));
                objeto.setPreco(rs.getDouble("PRECO"));
                objeto.setPromocao(rs.getDouble("PROMOCAO"));

                Categoria categoria = new Categoria();
                categoria.setcCategoria(rs.getInt("CCATEGORIA"));
                categoria.setNome(rs.getString("NOME"));

                Marca marca = new Marca();
                marca.setcMarca(rs.getInt("CMARCA"));
                marca.setNome(rs.getString("NOME"));

                objeto.setcCategoria(categoria);
                objeto.setcMarca(marca);

                list.add(objeto);
            }
            rs.close();
            p.close();

        } catch (SQLException ex) {
            throw new AppException("Ocorreu um erro ao consultar. Tente novamente, caso o erro persista contate o suporte.", ex);
        }

        return list;
    }

    public Produto update() {
        String SQL = "";
        return null;
    }

    public Produto delete() {
        return null;
    }
}
