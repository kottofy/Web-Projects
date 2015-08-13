/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
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
public class Shop extends HttpServlet
{

    DBHelper db = new DBHelper();
    ArrayList cart = new ArrayList();
    ArrayList quantityList = new ArrayList();

    /**
     * CHECKOUT
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
        HttpSession session = request.getSession();
        ArrayList cartSession = (ArrayList) session.getAttribute("cart");
        String orderDateTime = getDateTime();
        System.out.println("Date: " + orderDateTime);
        session.setAttribute("orderDateTime", orderDateTime);
        String creditCard = (String) session.getAttribute("creditCard");
        String customerID = (String) session.getAttribute("id");
        int id = Integer.parseInt(customerID);
        int itemID;
        String firstName = (String) session.getAttribute("firstName");
        System.out.println("FIRST NAME: " + firstName);
        String lastName = (String) session.getAttribute("lastName");
        String address = (String) session.getAttribute("address");
        String city = (String) session.getAttribute("city");
        String state = (String) session.getAttribute("state");
        String zip = (String) session.getAttribute("zip");

        System.out.println("2id: " + id);
        System.out.println("2firstName: " + firstName);
        System.out.println("2lastName: " + lastName);
        System.out.println("2address: " + address);
        System.out.println("2city: " + city);
        System.out.println("2state: " + state);
        System.out.println("2zip: " + zip);
        System.out.println("2creditCard: " + creditCard);

        //create orderInfo
        int orderInfoID = db.addOrderInfo(orderDateTime, creditCard, id);
        session.setAttribute("orderInfoID", orderInfoID);

        int quantInt = 0;

        //create lineItems for orderInfo
        for (int i = 0; i < cartSession.size(); i++)
        {
            CatalogItem item = (CatalogItem)cartSession.get(i);
            System.out.println("doGet: " + item.getSku());
            itemID = db.getItemID(item.getSku());
            db.addLineItem(orderInfoID, Integer.parseInt(quantityList.get(quantInt).toString()), itemID, item.getSku());
            quantInt++;
        }

        ArrayList order = db.getLineItems(orderInfoID);
        request.setAttribute("order", order);

        RequestDispatcher dispatcher = request.getRequestDispatcher("CheckOut.jsp");
        dispatcher.forward(request, response);
    }

    private String getDateTime()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
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
        System.out.println("DOOOPOOSSSST");
        HttpSession session = request.getSession();
        ArrayList items = db.getItems();
        //       System.out.println(((CatalogItem) items.get(0)).getDescription());
        request.setAttribute("items", items);
        String sku = "skuOops";
        String quantity = "quantityOops";
        Enumeration params = request.getParameterNames();
        String[] paramValues = request.getParameterValues("quantity");

        for (int i = 0; paramValues != null && i < paramValues.length; i++)
        {
            if (!paramValues[i].matches(""))
            {
                quantity = paramValues[i];
            }
        }
        while (params.hasMoreElements())
        {
            String param = params.nextElement().toString();
            for (int i = 0; i < items.size(); i++)
            {
                if (param.matches(((CatalogItem) items.get(i)).getSku()))
                {
                    sku = param;
                    cart.add(((CatalogItem) items.get(i)));
                }
            }
        }
        for (int i = 0; i < cart.size(); i++)
        {
            System.out.println("cart(" + i + "): " + ((CatalogItem)cart.get(i)).getSku());
        }
        if (!quantity.matches("quantityOops"))
        {
            quantityList.add(quantity);
        }
        session.setAttribute("cart", cart);
        System.out.println("quantity: " + quantity);
        System.out.println("sku: " + sku);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Shop.jsp");
        dispatcher.forward(request, response);
    }
}
