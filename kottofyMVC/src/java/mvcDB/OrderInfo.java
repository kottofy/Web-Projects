package mvcDB;

/**
 * @date Mar 30, 2011
 * @author kottofy
 */

/**
 * @name OrderInfo
 */
public class OrderInfo {

    private String orderDateTime;
    private String  shippingDateTime;
    private String creditCard;
    private int customerID;

    public OrderInfo()
    {
        orderDateTime = "";
        shippingDateTime = "";
        creditCard = "";
        customerID = 0;
    }

    public OrderInfo(String orderDateTime, String shippingDateTime, String creditCard, int customerID)
    {
        this.orderDateTime = orderDateTime;
        this.shippingDateTime = shippingDateTime;
        this.creditCard = creditCard;
        this.customerID = customerID;
    }

    public String getCreditCard()
    {
        return creditCard;
    }

    public void setCreditCard(String creditCard)
    {
        this.creditCard = creditCard;
    }

    public int getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }

    public String getOrderDateTime()
    {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime)
    {
        this.orderDateTime = orderDateTime;
    }

    public String getShippingDateTime()
    {
        return shippingDateTime;
    }

    public void setShippingDateTime(String shippingDateTime)
    {
        this.shippingDateTime = shippingDateTime;
    }

}
