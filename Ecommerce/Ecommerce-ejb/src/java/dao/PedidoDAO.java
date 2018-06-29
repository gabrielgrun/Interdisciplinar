package dao;

import exceptions.AppException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Categoria;
import model.Marca;
import model.Pedido;
import util.ConnectionUtil;

/**
 *
 * @author Gabriel
 */
public class PedidoDAO {
    private Connection connection;
    
    public PedidoDAO() throws Exception{
        connection = ConnectionUtil.getConnection();
    }
    
    public List<Pedido> criarRel() throws AppException{
        Pedido objeto;
        List<Pedido> list = new ArrayList<>();
        try {
            String SQL = "SELECT PEDIDOITEM.CPEDIDO"
                + "   FROM PEDIDOITEM"
                + "   INNER JOIN PEDIDO ON (PEDIDO.CPEDIDO = PEDIDOITEM.CPEDIDO)";
            PreparedStatement p;
            p = connection.prepareStatement(SQL);
            
            ResultSet rs = p.executeQuery();
            
            while (rs.next()) {
                
                objeto = new Pedido();
                objeto.setcPedido(rs.getInt("CPEDIDO"));
                objeto.setEndereco(rs.getString("ENDERECO"));
                objeto.setBairro(rs.getString("BAIRRO"));
                objeto.setNumero(rs.getInt("NUMERO"));
                objeto.setCep(rs.getString("CEP"));
                objeto.setComplemento(rs.getString("COMPLEMENTO"));
                objeto.setCpf(rs.getString("CPF"));
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                objeto.setData(rs.getDate("DATA"));
                objeto.setValor(rs.getDouble("VALOR"));
                objeto.setUf(rs.getString("UF"));
                objeto.setCidade(rs.getString("CIDADE"));
                
                list.add(objeto);
            }
            rs.close();
            p.close();

        } catch (SQLException ex) {
            throw new AppException("Ocorreu um erro ao consultar. Tente novamente, caso o erro persista contate o suporte.",ex);
        }
        
        return list;
    }
}
              
