/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import beans.CadastrarClienteBeanRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ClienteDTO;
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
public class CadastrarClienteServlet extends HttpServlet
{

    @EJB
    CadastrarClienteBeanRemote bean;

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

        ClienteDTO cliente = mapper.readValue(content, ClienteDTO.class);

        String retorno = "";

        try
        {
            bean.cadastrarCliente(cliente);

        } catch (AppException ex)
        {
            retorno = ex.getMessage();
        }

        JsonObject json = Json.createObjectBuilder().add("message", retorno).build();

        saida.write(json.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("application/json");
        PrintWriter saida = resp.getWriter();

        String cliente;
        try
        {
            ObjectMapper mapper = new ObjectMapper();

            cliente = mapper.writeValueAsString(bean.ultimoCliente());

            saida.write(cliente);

        } catch (AppException ex)
        {
            Logger.getLogger(PesquisarProdutosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
