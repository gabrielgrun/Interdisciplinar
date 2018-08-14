package dao;

import exceptions.AppException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import util.ConnectionUtil;

/**
 *
 * @author Gabriel
 */
public class ClienteDAO
{

    private Connection connection;

    public ClienteDAO() throws Exception
    {
        connection = ConnectionUtil.getConnection();
    }

    public void inserirCliente(Cliente cliente) throws AppException
    {
        try
        {
            String SQL = "INSERT INTO CLIENTE(ENDERECO, BAIRRO, NUMERO, CEP, COMPLEMENTO, CPF, UF, CIDADE) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, cliente.getEndereco());
            p.setString(2, cliente.getBairro());
            p.setInt(3, cliente.getNumero());
            p.setString(4, cliente.getCep());
            p.setString(5, cliente.getComplemento());
            p.setString(6, cliente.getCpf());
            p.setString(7, cliente.getUf());
            p.setString(8, cliente.getCidade());
            p.execute();
            p.close();
        } catch (SQLException ex)
        {
            throw new AppException("a", ex);
        }
    }

    public Cliente findById(int id) throws Exception
    {
        Cliente objeto = new Cliente();
        String SQL = "SELECT *"
                + "   FROM CLIENTE"
                + "   WHERE CLIENTE.CCLIENTE = ?";

        try
        {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, id);

            ResultSet rs = p.executeQuery();

            while (rs.next())
            {

                objeto = new Cliente();
                objeto.setEndereco(rs.getString("ENDERECO"));
                objeto.setBairro(rs.getString("BAIRRO"));
                objeto.setNumero(rs.getInt("NUMERO"));
                objeto.setCep(rs.getString("CEP"));
                objeto.setComplemento(rs.getString("COMPLEMENTO"));
                objeto.setCpf(rs.getString("CPF"));
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                objeto.setUf(rs.getString("UF"));
                objeto.setCidade(rs.getString("CIDADE"));
            }
            rs.close();
            p.close();

        } catch (SQLException ex)
        {
            throw new Exception(ex);
        }

        return objeto;
    }

    public List<Cliente> findAll() throws Exception
    {

        List<Cliente> list = new ArrayList<>();
        Cliente objeto;
        String SQL = "SELECT CLIENTE"
                + "   FROM CLIENTE "
                + "   ORDER BY CCLIENTE";

        try
        {
            PreparedStatement p = connection.prepareStatement(SQL);

            ResultSet rs = p.executeQuery();

            while (rs.next())
            {

                objeto = new Cliente();
                objeto.setEndereco(rs.getString("ENDERECO"));
                objeto.setBairro(rs.getString("BAIRRO"));
                objeto.setNumero(rs.getInt("NUMERO"));
                objeto.setCep(rs.getString("CEP"));
                objeto.setComplemento(rs.getString("COMPLEMENTO"));
                objeto.setCpf(rs.getString("CPF"));
                objeto.setUf(rs.getString("UF"));
                objeto.setCidade(rs.getString("CIDADE"));
                list.add(objeto);
            }
            rs.close();
            p.close();

        } catch (SQLException ex)
        {
            throw new Exception("Ocorreu um erro ao consultar. Tente novamente, caso o erro persista contate o suporte.", ex);
        }

        return list;
    }
}
