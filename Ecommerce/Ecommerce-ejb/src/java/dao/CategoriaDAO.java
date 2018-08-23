package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.CategoriaDTO;
import util.ConnectionUtil;

/**
 *
 * @author Gabriel
 */
public class CategoriaDAO
{

    private Connection connection;

    public CategoriaDAO() throws Exception
    {
        connection = ConnectionUtil.getConnection();
    }

    public CategoriaDTO findById(int id) throws Exception
    {
        CategoriaDTO objeto = new CategoriaDTO();
        String SQL = "SELECT * "
                + "   FROM CATEGORIA"
                + "   WHERE CATEGORIA.CCATEGORIA = ?";

        try
        {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, id);

            ResultSet rs = p.executeQuery();

            while (rs.next())
            {

                objeto = new CategoriaDTO();
                objeto.setcCategoria(rs.getInt("CCATEGORIA"));
                objeto.setNome(rs.getString("NOME"));
            }
            rs.close();
            p.close();

        } catch (SQLException ex)
        {
            throw new Exception(ex);
        }

        return objeto;
    }

    public List<CategoriaDTO> findAll() throws Exception
    {

        List<CategoriaDTO> list = new ArrayList<>();
        CategoriaDTO objeto;
        String SQL = "SELECT *"
                + "   FROM CATEGORIA "
                + "   ORDER BY CCATEGORIA";

        try
        {
            PreparedStatement p = connection.prepareStatement(SQL);

            ResultSet rs = p.executeQuery();

            while (rs.next())
            {

                objeto = new CategoriaDTO();
                objeto.setcCategoria(rs.getInt("CCATEGORIA"));
                objeto.setNome(rs.getString("NOME"));

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
