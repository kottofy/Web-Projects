/**
 * @date Apr 4, 2011
 * @author kottofy
 */

package mvcDB;

/**
 * @name LineItem
 */
public class LineItem {

    int orderNumber, quantity;
    String sku;

    public LineItem()
    {
        orderNumber = 0;
        quantity = 0;
        sku = "";
    }

    public LineItem(int orderNumber, int quantity, String sku) {
        this.orderNumber = orderNumber;
        this.quantity = quantity;
        this.sku = sku;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSku()
    {
        return sku;
    }

    public void setSku(String sku)
    {
        this.sku = sku;
    }
}
