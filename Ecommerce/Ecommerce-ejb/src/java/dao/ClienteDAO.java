package dao;

import exceptions.AppException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import dto.ClienteDTO;
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

    public void inserirCliente(ClienteDTO cliente) throws AppException
    {
        try
        {
            String SQL = "INSERT INTO CLIENTE(ENDERECO, BAIRRO, NUMERO, CEP, COMPLEMENTO, CPF, UF, CIDADE, NOME) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, cliente.getEndereco());
            p.setString(2, cliente.getBairro());
            p.setInt(3, cliente.getNumero());
            p.setString(4, cliente.getCep());
            p.setString(5, cliente.getComplemento());
            p.setString(6, cliente.getCpf());
            p.setString(7, cliente.getUf());
            p.setString(8, cliente.getCidade());
            p.setString(9, cliente.getNome());
            p.execute();
            p.close();
        } catch (SQLException ex)
        {
            throw new AppException("a", ex);
        }
    }

    public ClienteDTO findById(int id) throws Exception
    {
        ClienteDTO objeto = new ClienteDTO();
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

                objeto = new ClienteDTO();
                objeto.setNome(rs.getString("NOME"));
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

    public List<ClienteDTO> findAll() throws Exception
    {

        List<ClienteDTO> list = new ArrayList<>();
        ClienteDTO objeto;
        String SQL = "SELECT CCLIENTE"
                + "   FROM CLIENTE "
                + "   ORDER BY CCLIENTE";

        try
        {
            PreparedStatement p = connection.prepareStatement(SQL);

            ResultSet rs = p.executeQuery();

            while (rs.next())
            {

                objeto = new ClienteDTO();
                objeto.setNome(rs.getString("NOME"));
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

    public ClienteDTO ultimoCliente() throws Exception
    {

        ClienteDTO objeto = new ClienteDTO();
        String SQL = "SELECT FIRST 1 CLIENTE.CCLIENTE"
                + "   FROM CLIENTE "
                + "   ORDER BY CCLIENTE DESC";

        try
        {
            PreparedStatement p = connection.prepareStatement(SQL);

            ResultSet rs = p.executeQuery();

            while (rs.next())
            {
                objeto = new ClienteDTO();
                objeto.setcCliente(rs.getInt("CCLIENTE"));
                objeto.setNome(rs.getString("NOME"));
                objeto.setEndereco(rs.getString("ENDERECO"));
                objeto.setBairro(rs.getString("BAIRRO"));
                objeto.setNumero(rs.getInt("NUMERO"));
                objeto.setCep(rs.getString("CEP"));
                objeto.setComplemento(rs.getString("COMPLEMENTO"));
                objeto.setCpf(rs.getString("CPF"));
                objeto.setUf(rs.getString("UF"));
                objeto.setCidade(rs.getString("CIDADE"));
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
