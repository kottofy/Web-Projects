
/**
 * @nameAndExt DBHelper.java
 * @date Apr 8, 2011
 * @author Kristin Ottofy
 */
package mvcDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */
public class DBHelper
{

    PreparedStatement insertCustomerStatement;
    PreparedStatement insertLineItem;
    PreparedStatement insertOrderInfo;
    PreparedStatement getItemsStatement;
    PreparedStatement getCustomersStatement;
    PreparedStatement getLineItems;
    PreparedStatement loginStatement;
    PreparedStatement orderNumberStatement;
    PreparedStatement getAllOrders;
    PreparedStatement getCCStatement;
    PreparedStatement deleteCustomerStatement;
    PreparedStatement deleteCatalogItem;
    PreparedStatement deleteLineItem;
    PreparedStatement deleteOrderInfo;
    PreparedStatement updateUsername;
    PreparedStatement updateFirstName;
    PreparedStatement updateLastName;
    PreparedStatement updatePassword;
    PreparedStatement updateAddress;
    PreparedStatement updateCity;
    PreparedStatement updateState;
    PreparedStatement updateZip;
    PreparedStatement updateCreditCard;
    PreparedStatement selectFirstName;
    PreparedStatement selectLastName;
    PreparedStatement selectAddress;
    PreparedStatement selectCity;
    PreparedStatement selectState;
    PreparedStatement selectZip;
    PreparedStatement selectCreditCard;
    PreparedStatement selectItemID;
    PreparedStatement selectOrderInfoID;
    PreparedStatement selectID;
    PreparedStatement getMyOrders;

    public DBHelper()
    {
        System.out.println("DBHelper");
        String dbURL = "jdbc:mysql://localhost/kottofy";
        String user = "kottofy";
        String password = "";
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Instantiated Driver!");
            Connection conn = DriverManager.getConnection(dbURL, user, password);

            System.out.println("Established DB Connection!");
            insertCustomerStatement = conn.prepareStatement("INSERT INTO customer(username, password, firstName, lastName, address, city, state, zip, creditCard) VALUES(?,?,?,?,?,?,?,?,?)");
            getItemsStatement = conn.prepareStatement("select * from catalogItem");
            getCustomersStatement = conn.prepareStatement("select * from customer");
            insertLineItem = conn.prepareStatement("INSERT INTO lineItem(quantity, orderNumber, sku) VALUES(?,?,?)");
            insertOrderInfo = conn.prepareStatement("INSERT INTO orderInfo(orderDateTime, shippingDateTime, creditCard, customerID) VALUES(?,?,?,?)");
            loginStatement = conn.prepareStatement("select id from customer where username=? and password=?");
            orderNumberStatement = conn.prepareStatement("select id from orderInfo where customerID=? and orderDateTime=?");
            getAllOrders = conn.prepareStatement("select * from orderInfo");
            getCCStatement = conn.prepareStatement("select creditCard from customer where id=?");
            updateUsername = conn.prepareStatement("update customer set userName=? where userName=?");
            updatePassword = conn.prepareStatement("update customer set password=? where userName=?");
            updateFirstName = conn.prepareStatement("update customer set firstName=? where userName=?");
            updateLastName = conn.prepareStatement("update customer set lastName=? where userName=?");
            updateAddress = conn.prepareStatement("update customer set address=? where userName=?");
            updateCity = conn.prepareStatement("update customer set city=? where userName=?");
            updateState = conn.prepareStatement("update customer set state=? where userName=?");
            updateUsername = conn.prepareStatement("update customer set userName=? where userName=?");
            updateZip = conn.prepareStatement("update customer set zip=? where userName=?");
            updateCreditCard = conn.prepareStatement("update customer set creditCard=? where userName=?");
            selectFirstName = conn.prepareStatement("select firstName from customer where userName=?");
            selectLastName = conn.prepareStatement("select lastName from customer where userName=?");
            selectAddress = conn.prepareStatement("select address from customer where userName=?");
            selectCity = conn.prepareStatement("select city from customer where userName=?");
            selectState = conn.prepareStatement("select state from customer where userName=?");
            selectZip = conn.prepareStatement("select zip from customer where userName=?");
            selectCreditCard = conn.prepareStatement("select creditCard from customer where userName=?");
            selectItemID = conn.prepareStatement("select id from catalogItem where sku=?");
            selectOrderInfoID = conn.prepareStatement("select id from orderInfo where orderDateTime=? and creditCard=? and customerID=?");
            selectID = conn.prepareStatement("select id from customer where userName=?");
            getLineItems = conn.prepareStatement("select * from lineItem where orderNumber=?");
            getMyOrders = conn.prepareStatement("select * from orderInfo where customerID=?");
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList getAllOrders()
    {
        ArrayList allOrders = new ArrayList();

        try
        {
            ResultSet rs = getAllOrders.executeQuery();
            while (rs.next())
            {
                String orderDateTime = rs.getString("orderDateTime");
                String shippingDateTime = rs.getString("shippingDateTime");
                String creditCard = rs.getString("creditCard");
                int customerID = rs.getInt("customerID");

                OrderInfo orderInfo = new OrderInfo(orderDateTime, shippingDateTime, creditCard, customerID);
                allOrders.add(orderInfo);
            }
        }
        catch (Exception e)
        {
            System.out.println("***** getAllOrders() failed... *****");
            System.out.println(e.getMessage());
        }

        return allOrders;
    }

    public ArrayList getMyOrders(int id)
    {
        ArrayList myOrders = new ArrayList();
        ResultSet rs;

        try
        {
            getMyOrders.setInt(1, id);
            rs = getMyOrders.executeQuery();
            while (rs.next())
            {
                String orderDateTime = rs.getString("orderDateTime");
                String shippingDateTime = "Not yet shipped";
                String creditCard = rs.getString("creditCard");

                OrderInfo orderInfo = new OrderInfo(orderDateTime, shippingDateTime, creditCard, id);
                myOrders.add(orderInfo);
            }
        }
        catch (Exception e)
        {
            System.out.println("***** getMyOrders() failed... *****");
            System.out.println(e.getMessage());
        }

        return myOrders;
    }

    public ArrayList getLineItems(int orderNumber)
    {
        ArrayList lineItems = new ArrayList();
        ResultSet rs;

        try
        {
            getLineItems.setInt(1, orderNumber);
            rs = getLineItems.executeQuery();
            while (rs.next())
            {
                int quantity = rs.getInt("quantity");
                String sku = rs.getString("sku");

                LineItem item = new LineItem(orderNumber, quantity, sku);
                lineItems.add(item);
            }
        }
        catch (Exception e)
        {
            System.out.println("***** getLineItems() failed... *****");
            System.out.println(e.getMessage());
        }

        return lineItems;
    }

    public int addOrderInfo(String orderDateTime, String creditCard, int customerID)
    {
        System.out.println("addOrderInfo()");
        int orderInfoID = -1;
        ResultSet rs;
        try
        {
            insertOrderInfo.setString(1, orderDateTime);
            insertOrderInfo.setString(2, null);
            insertOrderInfo.setString(3, creditCard);
            insertOrderInfo.setInt(4, customerID);
            insertOrderInfo.execute();
            System.out.println("orderDateTime: " + orderDateTime);
            System.out.println("creditCard: " + creditCard);
            System.out.println("customerID: " + customerID);
            selectOrderInfoID.setString(1, orderDateTime);
            selectOrderInfoID.setString(2, creditCard);
            selectOrderInfoID.setInt(3, customerID);
            rs = selectOrderInfoID.executeQuery();
            while (rs.next())
            {
                System.out.println("rs.next()");
                orderInfoID = rs.getInt("id");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("orderInfoID: " + orderInfoID);
        return orderInfoID;
    }

    public void addLineItem(int orderNumber, int quantity, int id, String sku)
    {
        try
        {
            insertLineItem.setInt(1, quantity);
            insertLineItem.setInt(2, orderNumber);
            insertLineItem.setString(3, sku);
            insertLineItem.execute();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getItemID(String sku)
    {
        int id = -1;
        ResultSet rs;
        try
        {
            selectItemID.setString(1, sku);
            rs = selectItemID.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("id");
                System.out.println("id: " + id);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public String getID(Customer customer)
    {
        String id = "";
        System.out.println("getID()");
        ResultSet rs;
        try
        {
            selectID.setString(1, customer.getUserName());

            rs = selectID.executeQuery();
            while (rs.next())
            {
                id = rs.getString("id");
                System.out.println("ID: " + id);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public String getFirstName(Customer customer)
    {
        String firstName = "";
        ResultSet rs;
        try
        {
            selectFirstName.setString(1, customer.getUserName());
            rs = selectFirstName.executeQuery();
            while (rs.next())
            {
                firstName = rs.getString("firstName");
                System.out.println("firstName: " + firstName);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return firstName;
    }

    public String getLastName(Customer customer)
    {
        String lastName = "";
        ResultSet rs;
        try
        {
            selectLastName.setString(1, customer.getUserName());
            rs = selectLastName.executeQuery();
            while (rs.next())
            {
                lastName = rs.getString("lastName");
                System.out.println("lastName: " + lastName);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastName;
    }

    public String getAddress(Customer customer)
    {
        String Address = "";
        ResultSet rs;
        try
        {
            selectAddress.setString(1, customer.getUserName());
            rs = selectAddress.executeQuery();
            while (rs.next())
            {
                Address = rs.getString("address");
                System.out.println("Address: " + Address);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Address;
    }

    public String getCity(Customer customer)
    {
        String city = "";
        ResultSet rs;
        try
        {
            selectCity.setString(1, customer.getUserName());
            rs = selectCity.executeQuery();
            while (rs.next())
            {
                city = rs.getString("city");
                System.out.println("city: " + city);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return city;
    }

    public String getZip(Customer customer)
    {
        String zip = "";
        ResultSet rs;
        try
        {
            selectZip.setString(1, customer.getUserName());
            rs = selectZip.executeQuery();
            while (rs.next())
            {
                zip = rs.getString("zip");
                System.out.println("zip: " + zip);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zip;
    }

    public String getCreditCard(Customer customer)
    {
        String creditCard = "";
        ResultSet rs;
        try
        {
            selectCreditCard.setString(1, customer.getUserName());
            rs = selectCreditCard.executeQuery();
            while (rs.next())
            {
                creditCard = rs.getString("creditCard");
                System.out.println("creditCard: " + creditCard);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return creditCard;
    }

    public String getState(Customer customer)
    {
        String state = "";
        ResultSet rs;
        try
        {
            selectState.setString(1, customer.getUserName());
            rs = selectState.executeQuery();
            while (rs.next())
            {
                state = rs.getString("state");
                System.out.println("state: " + state);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return state;
    }

    public ArrayList getCustomers()
    {
        ArrayList customers = new ArrayList();

        try
        {
            ResultSet customerResults = getCustomersStatement.executeQuery();
            while (customerResults.next())
            {
                String username = customerResults.getString("username");
                //     System.out.println(username);
                String password = customerResults.getString("password");
                String firstName = customerResults.getString("firstName");
                String lastName = customerResults.getString("lastName");
                String address = customerResults.getString("address");
                String city = customerResults.getString("city");
                String state = customerResults.getString("state");
                String zip = customerResults.getString("zip");
                String creditCard = customerResults.getString("creditCard");
                Customer customer = new Customer(username, password, firstName, lastName, address, city, state, zip, creditCard);
                customers.add(customer);
            }
        }
        catch (Exception e)
        {
            System.out.println("***** getCustomers() failed... *****");
            System.out.println(e.getMessage());
        }

        return customers;
    }

    public void addCustomer(Customer customer)
    {
        try
        {
            insertCustomerStatement.setString(1, customer.getUserName());
            insertCustomerStatement.setString(2, customer.getPassword());
            insertCustomerStatement.setString(3, customer.getFirstName());
            insertCustomerStatement.setString(4, customer.getLastName());
            insertCustomerStatement.setString(5, customer.getAddress());
            insertCustomerStatement.setString(6, customer.getCity());
            insertCustomerStatement.setString(7, customer.getState());
            insertCustomerStatement.setString(8, customer.getZip());
            insertCustomerStatement.setString(9, customer.getCreditCard());
            insertCustomerStatement.execute();
        }
        catch (Exception e)
        {
            System.out.println("addCustomer Failed");
            System.out.println(e.getMessage());
        }
    }

    public void updateCustomer(Customer customer)
    {
        try
        {
            updateUsername.setString(1, customer.getUserName());
            updateUsername.execute();
            updateFirstName.setString(1, customer.getFirstName());
            updateUsername.execute();
            updateLastName.setString(1, customer.getLastName());
            updateUsername.execute();
            updatePassword.setString(1, customer.getPassword());
            updateUsername.execute();
            updateAddress.setString(1, customer.getAddress());
            updateUsername.execute();
            updateCity.setString(1, customer.getCity());
            updateUsername.execute();
            updateState.setString(1, customer.getState());
            updateUsername.execute();
            updateZip.setString(1, customer.getZip());
            updateUsername.execute();
            updateCreditCard.setString(1, customer.getCreditCard());
            updateUsername.execute();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList getItems()
    {
        ArrayList items = new ArrayList();

        try
        {
            ResultSet itemResults = getItemsStatement.executeQuery();

            while (itemResults.next())
            {
                String description = itemResults.getString("description");
                String sku = itemResults.getString("sku");
                double price = itemResults.getDouble("price");
                CatalogItem catalogItem = new CatalogItem(sku, description, price);
                items.add(catalogItem);
            }

        }
        catch (Exception e)
        {
            System.out.println("***** getItems() failed... *****");
            System.out.println(e.getMessage());
        }

        return items;
    }
}
