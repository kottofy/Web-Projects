/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mvcDB.DBHelper;

/**
 *
 * @author kottofy
 */
public class Orders extends HttpServlet {

    DBHelper db = new DBHelper();
   
    /**
     * VIEW MY ORDERS
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        String customerID = (String) session.getAttribute("id");
        int id = Integer.parseInt(customerID);

        ArrayList myOrders = db.getMyOrders(id);

        request.setAttribute("myOrders", myOrders);

        RequestDispatcher dispatcher = request.getRequestDispatcher("MyOrders.jsp");
        dispatcher.forward(request, response);
    } 

    /**
     * VIEW ALL ORDERS
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        String customerID = (String) session.getAttribute("id");
        int id = Integer.parseInt(customerID);

        ArrayList allOrders = db.getAllOrders();

        request.setAttribute("allOrders", allOrders);

        RequestDispatcher dispatcher = request.getRequestDispatcher("AllOrders.jsp");
        dispatcher.forward(request, response);
    }
}
