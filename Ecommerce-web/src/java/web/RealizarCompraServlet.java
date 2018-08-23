/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import beans.RealizarCompraBeanRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.AppException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dto.ClienteDTO;
import dto.PedidoDTO;
import dto.PedidoItemDTO;

/**
 *
 * @author Gabriel
 */
public class RealizarCompraServlet extends HttpServlet
{

    @EJB
    private RealizarCompraBeanRemote bean;

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

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        mapper.setDateFormat(formatter);
        PedidoDTO pedido = mapper.readValue(content, PedidoDTO.class);
        PedidoItemDTO pedidoItem = mapper.readValue(content, PedidoItemDTO.class);
        ClienteDTO cliente = mapper.readValue(content, ClienteDTO.class);

        boolean ret;
        String retorno = "";

        try
        {
            ret = bean.realizarCompra(pedido, pedidoItem, cliente);

            if (ret)
            {
                retorno = "Compra realizada com sucesso!";
            } else
            {
                retorno = "Compra cancelada!";
            }
        } catch (AppException ex)
        {
            retorno = ex.getMessage();
        }

        JsonObject json = Json.createObjectBuilder().add("message", retorno).build();

        saida.write(json.toString());
    }

}
