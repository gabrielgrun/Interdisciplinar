package dao;

import exceptions.AppException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dto.PedidoItemDTO;
import util.ConnectionUtil;

/**
 *
 * @author Gabriel
 */
public class PedidoItemDAO
{

    private Connection connection;

    public PedidoItemDAO() throws Exception
    {
        connection = ConnectionUtil.getConnection();
    }

    public void save(PedidoItemDTO pedidoItem) throws AppException
    {
        try
        {
            String SQL = "INSERT INTO PEDIDOITEM (QTDE, VALOR, CPRODUTO, CPEDIDO) VALUES(?, ?, ?, ?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, pedidoItem.getQtde());
            p.setDouble(2, pedidoItem.getValor());
            p.setInt(3, pedidoItem.getcProduto().getcProduto());
            p.setInt(4, pedidoItem.getcPedido().getcPedido());
            p.execute();
            p.close();
        } catch (SQLException ex)
        {
            throw new AppException("Ocorreu um erro ao inserir. Tente novamente, caso o erro persista contate o suporte.", ex);
        }
    }

}
