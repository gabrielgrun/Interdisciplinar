/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import exceptions.AppException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Pedido;
import model.PedidoItem;

/**
 *
 * @author Gabriel
 */
@Stateless
public class RealizarCompraBean implements RealizarCompraBeanRemote, RealizarCompraBeanLocal{

    public boolean realizarCompra(Pedido pedido, PedidoItem pedidoItem) throws AppException{
        
        
        //if produto.qtde > 0
        String sql = "INSERT INTO PEDIDO(ENDERECO, BAIRRO, NUMERO, CEP, COMPLEMENTO, CPF, DATA, VALOR, UF, CIDADE) VALUES(?,?,?,?,?,?,?,?,?,?)";
        String sql2 = "INSERT INTO PEDIDOITEM(QTDE, VALOR, CPRODUTO, CPEDIDO) VALUES(?,?,?,?)";
        
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:firebirdsql:localhost/3050:D:\\Tecnico Informática\\Interdisciplinar\\ECOMMERCE_BD.FDB", "SYSDBA", "masterkey");
        } catch (SQLException ex) {
            throw new AppException("Ocorreu um erro! Contate o suporte!");
        }
        
        try {
            PreparedStatement p = con.prepareStatement(sql);
            p.setString(1, pedido.getEndereco());
        } catch (SQLException ex) {
            throw new AppException("Ocorreu um erro! Contate o suporte!");
        }
        return false;
    }
}
