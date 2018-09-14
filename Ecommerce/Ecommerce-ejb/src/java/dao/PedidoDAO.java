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
import dto.ClienteDTO;
import dto.PedidoDTO;
import util.ConnectionUtil;

/**
 *
 * @author Gabriel
 */
public class PedidoDAO
{

    private Connection connection;

    public PedidoDAO() throws Exception
    {
        connection = ConnectionUtil.getConnection();
    }

    public void inserirPedido(PedidoDTO pedido) throws AppException
    {
        try
        {
            String SQL = "INSERT INTO PEDIDO(DATA, CCLIENTE) VALUES(?,?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setDate(1, pedido.getData());
            p.setInt(2, pedido.getcCliente().getcCliente());
            p.execute();
            p.close();
        } catch (SQLException ex)
        {
            throw new AppException("a", ex);
        }
    }

    public List<PedidoDTO> criarRel() throws AppException
    {
        PedidoDTO objeto;
        ClienteDTO cliente;
        List<PedidoDTO> list = new ArrayList<>();
        try
        {
            String SQL = "SELECT * "
                    + "   FROM PEDIDOITEM"
                    + "   INNER JOIN PEDIDO ON (PEDIDO.CPEDIDO = PEDIDOITEM.CPEDIDO)";
            PreparedStatement p;
            p = connection.prepareStatement(SQL);

            ResultSet rs = p.executeQuery();

            while (rs.next())
            {

                objeto = new PedidoDTO();
                cliente = new ClienteDTO();
                objeto.setcPedido(rs.getInt("CPEDIDO"));
                cliente.setEndereco(rs.getString("ENDERECO"));
                cliente.setBairro(rs.getString("BAIRRO"));
                cliente.setNumero(rs.getInt("NUMERO"));
                cliente.setCep(rs.getString("CEP"));
                cliente.setComplemento(rs.getString("COMPLEMENTO"));
                cliente.setCpf(rs.getString("CPF"));
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                objeto.setData(new java.sql.Date(rs.getDate("DATA").getTime()));
                cliente.setUf(rs.getString("UF"));
                cliente.setCidade(rs.getString("CIDADE"));

                objeto.setcCliente(cliente);
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

    public List<PedidoDTO> criarRel(Date datai, Date dataf) throws AppException
    {
        PedidoDTO objeto;
        ClienteDTO cliente;
        List<PedidoDTO> list = new ArrayList<>();
        try
        {
            String SQL = "SELECT PEDIDOITEM.CPEDIDO"
                    + "   FROM PEDIDOITEM"
                    + "   INNER JOIN PEDIDO ON (PEDIDO.CPEDIDO = PEDIDOITEM.CPEDIDO)"
                    + "   WHERE PEDIDO.DATA BETWEEN ? AND ? ";
            PreparedStatement p;
            p = connection.prepareStatement(SQL);
            p.setDate(1, datai);
            p.setDate(2, dataf);

            ResultSet rs = p.executeQuery();

            while (rs.next())
            {

                objeto = new PedidoDTO();
                cliente = new ClienteDTO();
                objeto.setcPedido(rs.getInt("CPEDIDO"));
                cliente.setEndereco(rs.getString("ENDERECO"));
                cliente.setBairro(rs.getString("BAIRRO"));
                cliente.setNumero(rs.getInt("NUMERO"));
                cliente.setCep(rs.getString("CEP"));
                cliente.setComplemento(rs.getString("COMPLEMENTO"));
                cliente.setCpf(rs.getString("CPF"));
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                objeto.setData(rs.getDate("DATA"));
                cliente.setUf(rs.getString("UF"));
                cliente.setCidade(rs.getString("CIDADE"));

                objeto.setcCliente(cliente);
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

    public PedidoDTO ultimoPedido() throws Exception
    {

        PedidoDTO objeto = new PedidoDTO();
        String SQL = "SELECT FIRST 1 * "
                + "   FROM PEDIDO "
                + "   ORDER BY CPEDIDO DESC ";

        try
        {
            PreparedStatement p = connection.prepareStatement(SQL);

            ResultSet rs = p.executeQuery();
            while (rs.next())
            {
                objeto = new PedidoDTO();
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                objeto.setcPedido(rs.getInt("CPEDIDO"));
                objeto.setData(new java.sql.Date(rs.getDate("DATA").getTime()));
            }
            rs.close();
            p.close();

        } catch (SQLException ex)
        {
            throw new Exception("Ocorreu um erro ao consultar. Tente novamente, caso o erro persista contate o suporte.", ex);
        }

        return objeto;
    }
}
