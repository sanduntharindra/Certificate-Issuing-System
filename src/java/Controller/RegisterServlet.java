/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sandun
 */
public class RegisterServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
        String User = request.getParameter("Uname");
        String Pnum = request.getParameter("Phno");
        String Mail = request.getParameter("Mail");
        String Pass = request.getParameter("pw");
        String Confirm = request.getParameter("Approval");
        
        if((User.equals("")||Pnum.equals("")||Mail.equals("")||Pass.equals(""))){
        
            RequestDispatcher dispatcher=request.getRequestDispatcher("RegFailed.jsp");
            dispatcher.forward(request, response);
            
        }else{
            
            Beans.users u1 = new Beans.users();
            
            PrintWriter out = response.getWriter();
            DAO.RegisterDAO rb = new DAO.RegisterDAO();
        
                try {
                    rb.input(User,Pnum,Mail,Pass,Confirm);
                    u1.setUnames(User);
                    u1.setPno(Pnum);
                    u1.setMail(Mail);
                    u1.setPassword(Pass);
                    u1.setApproval(Confirm);

                    request.setAttribute("user", u1);

                    RequestDispatcher dispatcher=request.getRequestDispatcher("RegSuccess.jsp");
                    dispatcher.forward(request, response);
                }
                catch (Exception e) {
                    out.println(e);
                }

            
        }
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
