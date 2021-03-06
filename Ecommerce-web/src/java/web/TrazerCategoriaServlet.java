/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import beans.RealizarCompraBeanRemote;
import beans.TrazProdutosBeanRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.AppException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
public class TrazerCategoriaServlet extends HttpServlet
{

    @EJB
    TrazProdutosBeanRemote bean;

    @EJB
    RealizarCompraBeanRemote bean2;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("application/json");
        PrintWriter saida = resp.getWriter();

        String nome;
        String jsonJava = "";
        ObjectMapper mapper = new ObjectMapper();
        try (BufferedReader leitor = req.getReader())
        {
            nome = leitor.lines().collect(Collectors.joining());
            nome = nome.replaceAll("\"", "");
            jsonJava = mapper.writeValueAsString(bean.filtrarCategoria(nome));
        } catch (AppException ex)
        {
            Logger.getLogger(PesquisarProdutosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        saida.write(jsonJava);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("application/json");
        PrintWriter saida = resp.getWriter();

        String pedido;
        try
        {
            ObjectMapper mapper = new ObjectMapper();

            pedido = mapper.writeValueAsString(bean2.criarRel());

            saida.write(pedido);

        } catch (AppException ex)
        {
            Logger.getLogger(PesquisarProdutosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
