

package controllers;

import mvcDB.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kottofy
 */
public class Login extends HttpServlet
{

    DBHelper dbHelper = new DBHelper();

    /**
     * CREATES A NEW USER OR UPDATES ACCOUNT
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
     * CHECKS LOGIN INFORMATION
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
        System.out.println("DOPOST");
        HttpSession session = request.getSession();
        session.setAttribute("username", null);
        session.setAttribute("password", null);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Edit.jsp");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username: " + username + " password: " + password);

        ArrayList customers = dbHelper.getCustomers();

        if (username != null)
        {
            if (password != null)
            {
                for (int i = 0; i < customers.size(); i++)
                {
                    Customer customer = (Customer) customers.get(i);
                    System.out.println("customer list:" + customer.getUserName());
                    if (username.matches(customer.getUserName()) && password.matches(customer.getPassword()))
                    {
                        System.out.println("Login: ");
                        session.setAttribute("username", username);
                        session.setAttribute("password", password);

                        String id = dbHelper.getID(customer);
                        session.setAttribute("id", id);
                        String firstName = dbHelper.getFirstName(customer);
                        session.setAttribute("firstName", firstName);
                        String lastName = dbHelper.getLastName(customer);
                        session.setAttribute("lastName", lastName);
                        String address = dbHelper.getAddress(customer);
                        session.setAttribute("address", address);
                        String city = dbHelper.getCity(customer);
                        session.setAttribute("city", city);
                        String state = dbHelper.getState(customer);
                        session.setAttribute("state", state);
                        String zip = dbHelper.getZip(customer);
                        session.setAttribute("zip", zip);
                        String creditCard = dbHelper.getCreditCard(customer);
                        session.setAttribute("creditCard", creditCard);
                        System.out.println("id: " + id);
                        System.out.println("firstName: " + firstName);
                        System.out.println("lastName: " + lastName);
                        System.out.println("address: " + address);
                        System.out.println("city: " + city);
                        System.out.println("state: " + state);
                        System.out.println("zip: "+ zip);
                        System.out.println("creditCard: "+creditCard);

                        dispatcher = request.getRequestDispatcher("Shop.jsp");
                        break;
                    }
                    else
                    {
                        dispatcher = request.getRequestDispatcher("Edit.jsp");
                    }
                }
                System.out.println("username1: " + session.getAttribute("username"));
            }
            else
            {
                dispatcher = request.getRequestDispatcher("Edit.jsp");
            }
        }
        dispatcher.forward(request, response);
    }
}
