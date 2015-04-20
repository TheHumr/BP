package servlets;

import dao.PrispevekDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Prispevek;
import model.User;

/**
 *
 * @author TheHumr
 */
public class Servlet extends HttpServlet {

    PrispevekDAO prispevekDao = new PrispevekDAO();
    UserDAO userDao = new UserDAO();
    User user;
    Prispevek prispevek;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String cesta = request.getServletPath();
        switch (cesta) {
            case "/prihlasit":
                prihlasit(request, response);
                break;
            case "/odhlasit":
                odhlasit(request, response);
                break;
            case "/registrace":
                registrovat(request, response);
                break;
            case "/vytvoritPrispevek":
                vytvoritPrispevek(request, response);
                break;  
                
        }

    }

    private void prihlasit(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (over(username, password)) {
            request.getSession().setAttribute("user", user);

            presmeruj(request, response, "/home");
        } else {

            presmeruj(request, response, "/?status=logerror");
        }
    }

    private void registrovat(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("username");
        String heslo = request.getParameter("password");
        if (!login.equals("") && !heslo.equals("")) {
            user = new User(0, login, heslo);
            userDao.insert(user);

            presmeruj(request, response, "/?status=userreg");
        } else {
            presmeruj(request, response, "/?status=userregerror");
        }
    }
    
    private void vytvoritPrispevek(HttpServletRequest request, HttpServletResponse response) {
        String text = request.getParameter("text");
        if (!"".equals(text)) {
            user =(User) request.getSession().getAttribute("user");
            prispevek = new Prispevek(0, user, text);
            prispevekDao.insert(prispevek);
            presmeruj(request, response, "/home");
        } else {
            presmeruj(request, response, "/home?status=prispevekError");
        }
    }
    

    private boolean over(String username, String password) {
        user = userDao.getByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void presmeruj(HttpServletRequest request, HttpServletResponse response, String url) {
        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
        response.setHeader("Location", request.getContextPath() + url);

    }

    private void odhlasit(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("user", null);
        presmeruj(request, response, "/?status=logout");

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
