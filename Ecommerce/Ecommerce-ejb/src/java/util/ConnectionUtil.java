package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Gabriel
 */
public class ConnectionUtil
{

    private static Connection connection;

    public static Connection getConnection() throws Exception
    {

        if (connection == null)
        {
            System.out.println("Iniciando...");

            try
            {
                // Testando o carregamento da classe.
                Class.forName("org.firebirdsql.jdbc.FBDriver");

                // Dados de cnexão
                String servidor = "localhost";
                String dataBase = "D:\\Tecnico Informática\\Interdisciplinar\\ECOMMERCE_BD.FDB";
                String user = "SYSDBA";
                String password = "masterkey";
                // Prepara a URL de conexão
                // jdbc:forebirdsql:IP/3050:/diretorio/do/banco.fdb?param=adicionais
                String url = "jdbc:firebirdsql:" + servidor + "/3050:" + dataBase + "?enconding=win1252";

                // Obtem a conexão com o banco de dados
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Conexão estabelecida.");
            } catch (ClassNotFoundException ex)
            {
                throw new Exception(ex);
            } catch (SQLException ex)
            {
                throw new Exception(ex);
            }
        }

        return connection;
    }

    public static void main(String[] args)
    {
        System.out.println("Teste");
        try
        {
            ConnectionUtil.getConnection();
        } catch (Exception ex)
        {
            System.out.println("Erro de SQL");
        }
    }
}
