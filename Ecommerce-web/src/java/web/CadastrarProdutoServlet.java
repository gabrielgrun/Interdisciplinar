/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import beans.CadastrarProdutoBeanRemote;
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
import model.Produto;

/**
 *
 * @author Gabriel
 */
public class CadastrarProdutoServlet extends HttpServlet
{

    @EJB
    private CadastrarProdutoBeanRemote bean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("application/json");
        PrintWriter saida = resp.getWriter();

        String content = "";
        try (BufferedReader leitor = req.getReader())
        {
            content = leitor.lines().collect(Collectors.joining());
        }

        ObjectMapper mapper = new ObjectMapper();

        Produto produto = mapper.readValue(content, Produto.class);

        String retorno = "";

        try
        {
            bean.cadastrarProduto(produto);
            retorno = "Produto cadastrado com sucesso!";
        } catch (AppException ex)
        {
            try
            {
                throw new AppException("Erro ao cadastrar produto! Por favor, contate o suporte!", ex);
            } catch (AppException ex1)
            {
                Logger.getLogger(CadastrarProdutoServlet.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        JsonObject json = Json.createObjectBuilder().add("message", retorno).build();

        saida.write(json.toString());
    }

}
