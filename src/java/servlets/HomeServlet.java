/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.PrispevekDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Prispevek;
import model.User;

/**
 *
 * @author TheHumr
 */
public class HomeServlet extends HttpServlet {

    PrispevekDAO prispevekDao = new PrispevekDAO();
    UserDAO userDao = new UserDAO();
    User user;
    Prispevek prispevek;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("filterUsername") == null) {
            PrispevekDAO pDao = new PrispevekDAO();
            List<Prispevek> prispevky = pDao.getAll();
            request.setAttribute("prispevky", prispevky);
            request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
        } else {
            filtrovatPrispeveky(request, response);
        }

    }

    private void filtrovatPrispeveky(HttpServletRequest request, HttpServletResponse response) {
        String filterUsername = request.getParameter("filterUsername");
        if (!"".equals(filterUsername)) {
            try {
                user = (User) userDao.getByUsername(filterUsername);
                PrispevekDAO pDao = new PrispevekDAO();
                request.setAttribute("filterUsername", filterUsername);
                if (user != null) {
                    List<Prispevek> prispevky = pDao.getAllByUser(user);
                    request.setAttribute("prispevky", prispevky);
                    request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);

                } else {
                    List<Prispevek> prispevky = pDao.getAll();
                    request.setAttribute("prispevky", prispevky);
                    presmeruj(request, response, "/home?status=filterError2&username=" + filterUsername);
                }
            } catch (ServletException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            presmeruj(request, response, "/home?status=filterError");
        }
    }

    private void presmeruj(HttpServletRequest request, HttpServletResponse response, String url) {
        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
        response.setHeader("Location", request.getContextPath() + url);

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

}
