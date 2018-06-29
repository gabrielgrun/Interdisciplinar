package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Marca;
import util.ConnectionUtil;

/**
 *
 * @author Gabriel
 */
public class CategoriaDAO {
    private Connection connection;
    
    public CategoriaDAO() throws Exception{
        connection = ConnectionUtil.getConnection();
    }
    
    public Categoria findById(int id) throws Exception {
        Categoria objeto = new Categoria();
        String SQL = "SELECT CATEGORIA.*, MARCA.NOME "
                + "   FROM CATEGORIA"
                + "   INNER JOIN MARCA ON CCATEGORIA = MARCA.CCATEGORIA "
                + "   WHERE CATEGORIA.CCATEGORIA = ?";
        
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, id);
            
            ResultSet rs = p.executeQuery();
            
            while (rs.next()) {
                
                Marca marca = new Marca();
                
                objeto = new Categoria();
                objeto.setcCategoria(rs.getInt("CCATEGORIA"));
                objeto.setNome(rs.getString("NOME"));
                
                marca.setcMarca(rs.getInt("CMARCA") );
                marca.setNome(rs.getString("NOME"));
                
                objeto.setcMarca(marca);
            }
            rs.close();
            p.close();

        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        
        return objeto;
    }
    
    
    public List<Categoria> findAll() throws Exception {
        
        List<Categoria> list = new ArrayList<>();
        Categoria objeto;
        String SQL = "SELECT CATEGORIA.*, MARCA.NOME"
                + "   FROM CATEGORIA "
                + "   INNER JOIN MARCA ON CATEGORIA.CCATEGORIA = MARCA.CCATEGORIA "
                + "   ORDER BY CCATEGORIA";
        
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            
            ResultSet rs = p.executeQuery();
            
            while (rs.next()) {
                
                objeto = new Categoria();
                objeto.setcCategoria(rs.getInt("CCATEGORIA"));
                objeto.setNome(rs.getString("NOME"));
                
                Marca marca = new Marca();
                marca.setcMarca(rs.getInt("CMARCA") );
                marca.setNome(rs.getString("NOME"));
                
                objeto.setcMarca(marca);
                
                list.add(objeto);
            }
            rs.close();
            p.close();

        } catch (SQLException ex) {
            throw new Exception("Ocorreu um erro ao consultar. Tente novamente, caso o erro persista contate o suporte.",ex);
        }
        
        return list;
    }
}
