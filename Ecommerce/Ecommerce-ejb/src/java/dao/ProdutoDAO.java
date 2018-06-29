package dao;

import exceptions.AppException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public ProdutoDAO() throws Exception{
        connection = ConnectionUtil.getConnection();
    }
    
    public void save(Produto produto) throws AppException{
        
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
    
    public Produto update(){
        String SQL = "";
        return null;
    }
    
    public Produto delete(){
        return null;
    }
}
