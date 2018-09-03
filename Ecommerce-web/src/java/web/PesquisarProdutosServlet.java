/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import beans.PesquisarProdutosBeanRemote;
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
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
public class PesquisarProdutosServlet extends HttpServlet
{

    @EJB
    private PesquisarProdutosBeanRemote bean;

    @EJB
    private TrazProdutosBeanRemote bean2;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("application/json");
        PrintWriter saida = resp.getWriter();

        String nome = "";
        String jsonJava = "";
        ObjectMapper mapper = new ObjectMapper();
        try (BufferedReader leitor = req.getReader())
        {
            nome = leitor.lines().collect(Collectors.joining());
            jsonJava = mapper.writeValueAsString(bean2.filtrarNome(nome));
        } catch (AppException ex)
        {
            Logger.getLogger(PesquisarProdutosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        JsonObject jasao = Json.createObjectBuilder().add("json", jsonJava).build();

        saida.write(jasao.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("application/json");
        PrintWriter saida = resp.getWriter();

        String produto;
        try
        {
            ObjectMapper mapper = new ObjectMapper();

            produto = mapper.writeValueAsString(bean2.listarProdutos());

            saida.write(produto);

        } catch (AppException ex)
        {
            Logger.getLogger(PesquisarProdutosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
