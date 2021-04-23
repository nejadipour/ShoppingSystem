import java.util.Set;

/**
 * data related to basket is stored in this class
 * @author Alireza Nejadipour
 * @version 2.6
 */

public class Basket extends ProductsList
{
    /**
     * create a new basket
     */
    public Basket()
    {
        super();

    }


    /**
     * adds a product into the list
     * if it is already in the basket the value will be updated
     * @param product the product selected to be added to the basket
     */
    public void addProduct(Product product)
    {
        Integer count = products.get(product);

        // check if it is in the basket or not
        if (count == null)
        {
            products.put(product, 1);
        }
        else
        {
            products.replace(product, ++count);

        }

    }


    /**
     * calculates the total price of products in the basket
     * @return the price will be returned
     */
    public float totalPrice()
    {
        float total = 0;
        Set<Product> productsKey = products.keySet();

        for (Product product : productsKey)
        {
            total += product.getPrice() * products.get(product);

        }

        return total;

    }

}
