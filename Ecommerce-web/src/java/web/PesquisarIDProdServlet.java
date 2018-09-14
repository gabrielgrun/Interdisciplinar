/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import beans.TrazProdutosBeanRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.AppException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
public class PesquisarIDProdServlet extends HttpServlet
{

    @EJB
    TrazProdutosBeanRemote bean2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("application/json");
        PrintWriter saida = resp.getWriter();

        String produto = "";
        int cproduto = Integer.parseInt(req.getParameter("cproduto"));
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            produto = mapper.writeValueAsString(bean2.filtrarID(cproduto));
            saida.write(produto);
        } catch (AppException ex)
        {
            Logger.getLogger(PesquisarIDProdServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
