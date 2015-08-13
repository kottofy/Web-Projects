/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mvcDB.*;

/**
 *
 * @author kottofy
 */
public class Edit extends HttpServlet
{

    DBHelper dbHelper = new DBHelper();

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
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = request.getRequestDispatcher("Shop.jsp");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String creditCard = request.getParameter("creditCard");
        boolean usedUsername = false;

        Customer customer = new Customer(username, password, firstName, lastName, address, city, state, zip, creditCard);

        if (session.getAttribute("username") != null)
        {
            System.out.println("User is logged in");
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            dbHelper.updateCustomer(customer);
        }
        else
        {
            System.out.println("User is not logged in");
            ArrayList customers = dbHelper.getCustomers();
            for (int i = 0; i < customers.size(); i++)
            {
                Customer c = (Customer) customers.get(i);
                if (username.matches(c.getUserName()))
                {
                    usedUsername = true;
                    break;
                }
            }

            if (usedUsername == false)
            {
                dbHelper.addCustomer(customer);
            }
            else
            {
                 dispatcher = request.getRequestDispatcher("Edit.jsp");
            }
        }
        dispatcher.forward(request, response);
    }
}
