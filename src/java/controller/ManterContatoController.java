/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Contato;


/**
 *
 * @author iza Ribeiro
 */
/*@WebServlet(name = "ManterContatoController", urlPatterns = {"/ManterContatoController"})*/
public class ManterContatoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        
        if(acao.equals("confirmarOperacao")){
           confirmarOperacao(request, response);
        
        }else{
            if(acao.equals("prepararOperacao")){
                prepararOperacao(request, response);
            }
        }
    }
    
    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try{
        String operacao = request.getParameter("operacao");
        request.setAttribute("operacao", operacao);
        request.setAttribute("clientes", Cliente.obterTodosOsClientes());
        
        if(!operacao.equals("Incluir")){
            long id = Long.parseLong(request.getParameter("id").trim());
            Contato contato = Contato.obterContato((long)id);
            request.setAttribute("contato", contato);
        }
                RequestDispatcher view = request.getRequestDispatcher("/manterContato.jsp");
                view.forward(request, response);
                
    }catch(ServletException e){
                throw e;
            }catch(IOException e){
                throw new ServletException(e);
            }catch(SQLException e){
                throw new ServletException(e);
            }catch(ClassNotFoundException e){
                throw new ServletException(e);
            }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
         public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String operacao = request.getParameter("operacao");
        
        long id = Long.parseLong(request.getParameter("txtIdContato"));
        String numero = request.getParameter("txtNumeroContato");

        
         long cliente = Long.parseLong(request.getParameter("optCliente"));
        try {
            Cliente cli = null;
            if (cliente != 0) {
                cli = Cliente.obterCliente((long)id);
            }     
            
            Contato contato = new Contato(id, numero,cliente);
            if (operacao.equals("Incluir")) {
                contato.gravar();
            } else {
                if (operacao.equals("Editar")) {
                    contato.alterar();
                } else {
                    if (operacao.equals("Excluir")) {
                        contato.excluir();
                    }
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaContatoController");
            view.forward(request, response);
        } catch (IOException e) {
            throw new ServletException(e);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        } catch (ServletException e) {
            throw e;
        }
    }
}
