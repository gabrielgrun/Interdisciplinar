package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Gabriel
 */
public class ConnectionUtil
{

    public static Connection getConnection() throws Exception
    {
        InitialContext ctx;
        Connection con = null;

        try
        {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/EcommerceResource");

            con = ds.getConnection();
        } catch (NamingException | SQLException ex)
        {
            throw new Exception("Erro de conexão ao banco de dados! Verifique o log do aplicativo.", ex);
        }

        return con;
    }

    public static void main(String[] args)
    {
        try
        {
            ConnectionUtil.getConnection();
        } catch (Exception ex)
        {
            Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
