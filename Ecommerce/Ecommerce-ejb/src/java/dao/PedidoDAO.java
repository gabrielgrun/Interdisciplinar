package dao;

import exceptions.AppException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Pedido;
import util.ConnectionUtil;

/**
 *
 * @author Gabriel
 */
public class PedidoDAO {

    private Connection connection;

    public PedidoDAO() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public void inserirPedido(Pedido pedido) throws AppException {
        try {
            String SQL = "INSERT INTO PEDIDO(ENDERECO, BAIRRO, NUMERO, CEP, COMPLEMENTO, CPF, DATA, VALOR, UF, CIDADE) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, pedido.getEndereco());
            p.setString(2, pedido.getBairro());
            p.setInt(3, pedido.getNumero());
            p.setString(4, pedido.getCep());
            p.setString(5, pedido.getComplemento());
            p.setString(6, pedido.getCpf());
            p.setDate(7, pedido.getData());
            p.setDouble(8, pedido.getValor());
            p.setString(9, pedido.getUf());
            p.setString(10, pedido.getCidade());
            p.execute();
            p.close();
        } catch (SQLException ex) {
            throw new AppException("a", ex);
        }
    }

    public List<Pedido> criarRel() throws AppException {
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
            throw new AppException("Ocorreu um erro ao consultar. Tente novamente, caso o erro persista contate o suporte.", ex);
        }

        return list;
    }

    public List<Pedido> criarRel(Date datai, Date dataf) throws AppException {
        Pedido objeto;
        List<Pedido> list = new ArrayList<>();
        try {
            String SQL = "SELECT PEDIDOITEM.CPEDIDO"
                    + "   FROM PEDIDOITEM"
                    + "   INNER JOIN PEDIDO ON (PEDIDO.CPEDIDO = PEDIDOITEM.CPEDIDO)"
                    + "   WHERE PEDIDO.DATA BETWEEN ? AND ? ";
            PreparedStatement p;
            p = connection.prepareStatement(SQL);
            p.setDate(1, datai);
            p.setDate(2, dataf);

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
            throw new AppException("Ocorreu um erro ao consultar. Tente novamente, caso o erro persista contate o suporte.", ex);
        }

        return list;
    }
}
