/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import beans.PesquisarProdutosBeanRemote;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("application/json");
        PrintWriter saida = resp.getWriter();
        JsonObject jsonJava = null;

        String jsonJs = "";
        ObjectMapper mapper = new ObjectMapper();
        try (BufferedReader leitor = req.getReader())
        {
            jsonJs = leitor.lines().collect(Collectors.joining());
            jsonJava = mapper.writeValueAsString(bean.pesquisar(jsonJs));
        } catch (AppException ex)
        {
            Logger.getLogger(PesquisarProdutosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        jsonJava = Json.createObjectBuilder().add("json", jsonJava.toString()).build();
    }
}
