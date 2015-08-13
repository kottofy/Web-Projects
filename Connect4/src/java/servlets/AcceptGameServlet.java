/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import db.DBHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kottofy
 */
public class AcceptGameServlet extends HttpServlet
{

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        //THIS METHOD HANDLES FOR CHALLENGER1
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        DBHelper myDBHelper = new DBHelper();

        String player = (String) session.getAttribute("player");
        String challenger1 = (String) session.getAttribute("challenger1");

        System.out.println("PLAYER: " + player);
        System.out.println("CHALLENGER: " + challenger1);

        out.println("<xml>");
        out.println("<challenger1Name>" + challenger1 + "</challenger1Name>");
        ;
        out.println("</xml>");

    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        //THIS METHOD HANDLES FOR CHALLENGER2
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        DBHelper myDBHelper = new DBHelper();

        String player = (String) session.getAttribute("player");
        String challenger2 = (String) session.getAttribute("challenger2");

        System.out.println("PLAYER: " + player);
        System.out.println("CHALLENGER: " + challenger2);

        out.println("<xml>");
        out.println("<challenger2Name>" + challenger2 + "</challenger2Name>");
        out.println("</xml>");
    }
}
