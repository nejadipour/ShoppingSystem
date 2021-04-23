/**
 * the class holds details of the inventory
 * and some needed and particular methods
 * @author Alireza Nejadipour
 * @version 3.6
 */

public class Inventory extends ProductsList
{
    /**
     * create a new inventory
     */
    public Inventory()
    {
        super();

    }


    /**
     * new product is added to the list by given parameters
     * @param product the product that should be added
     * @param stock how many of this product is available
     */
    public void addProduct(Product product, int stock)
    {
        products.put(product, stock);

    }

}
