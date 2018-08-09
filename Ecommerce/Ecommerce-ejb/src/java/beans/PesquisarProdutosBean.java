/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.AppException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Categoria;
import model.Marca;
import model.Produto;
import util.ConnectionUtil;

/**
 *
 * @author Gabriel
 */
@Stateless
public class PesquisarProdutosBean implements PesquisarProdutosBeanRemote, PesquisarProdutosBeanLocal
{

    public Connection con;

    public PesquisarProdutosBean()
    {
        try
        {
            con = ConnectionUtil.getConnection();
        } catch (Exception ex)
        {
            Logger.getLogger(PesquisarProdutosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Produto pesquisar(String jsonJs) throws AppException
    {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        String condicoes = "";

        try
        {
            map = mapper.readValue(jsonJs, new TypeReference<Map<String, String>>()
            {
            });

            for (String key : map.keySet())
            {
                if (key.equals("preco"))
                {
                    condicoes += "AND " + key + map.get(key);
                }
                condicoes += "AND " + key + "=" + map.get(key);
            }
            //if(){}
            //for (Object value : map.values()) {
            // valores

            //}
        } catch (IOException ex)
        {
            if (!jsonJs.equals(""))
            {
                throw new AppException("Erro no Json", ex);
            }
        }
        Produto objeto = new Produto();
        String SQL = "SELECT *"
                + "  FROM PRODUTO "
                + "  WHERE PRODUTO.CPRODUTO > -1"
                + condicoes;
        try
        {
            //                + "  AND PRODUTO.CMARCA ="
//                + "  AND PRODUTO.CCATEGORIA ="
//                + "  AND PRODUTO.PRECO" + fs + sd;

            //implementar maior segurança com os ?
            PreparedStatement p = con.prepareStatement(SQL);
            p.executeQuery();

            ResultSet rs = p.executeQuery();

            while (rs.next())
            {

                objeto = new Produto();
                objeto.setNome(rs.getString("NOME"));
                objeto.setDescricao("DESCRICAO");
                objeto.setFoto(rs.getString("FOTO"));
                objeto.setPreco(rs.getDouble("PRECO"));
                objeto.setPromocao(rs.getDouble("PROMOCAO"));
                objeto.setQtde(rs.getInt("QTDE"));

                Marca marca = new Marca();
                Categoria categoria = new Categoria();

                categoria = new Categoria();
                categoria.setcCategoria(rs.getInt("CCATEGORIA"));
                categoria.setNome(rs.getString("NOME"));

                marca.setcMarca(rs.getInt("CMARCA"));
                marca.setNome(rs.getString("NOME"));

                objeto.setcCategoria(categoria);
                objeto.setcMarca(marca);
            }
            rs.close();
            p.close();
        } catch (SQLException ex)
        {
            throw new AppException("Ocorreu um erro, por favor entre em contato com o suporte!", ex);
        }

        return objeto;
    }
}
