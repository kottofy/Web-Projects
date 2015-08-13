/**
 * @date Apr 4, 2011
 * @author kottofy
 */

package mvcDB;

/**
 * @name CatalogItem
 */
public class CatalogItem {

String sku, description;
double price;

    public CatalogItem() {
        sku = "";
        description = "";
        price = 0.00;
    }

    public CatalogItem(String sku, String description, double price) {
        this.sku = sku;
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    
}
