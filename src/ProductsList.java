import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * super class of classes Basket and Inventory
 * contains HashMap of products
 * the key is product
 * if subclass is basket the value is the number of that product in the basket
 * if subclass is inventory the value is product's stock
 * common methods like remove, update or find are here
 * @author Alireza Nejadipour
 * @version 4.4
 */

public class ProductsList
{
    protected HashMap<Product, Integer> products;

    /**
     * create a new ProductList
     */
    public ProductsList()
    {
        products = new HashMap<>();

    }


    /**
     * gets the product and removes it from the list
     * @param product the product that should be removed
     */
    public void removeProduct(Product product)
    {
        products.remove(product);

    }


    /**
     * gets a product and updates its value in the HashMap
     * @param product the key
     * @param num new value
     */
    public void updateProduct(Product product, int num)
    {
        products.replace(product, num);

    }


    /**
     * gets the index
     * iterates through the HashMap and returns the product as key
     * @param index the index of the element
     * @return product will be returned
     */
    public Product findProduct(int index)
    {
        Iterator<Product> iterator = products.keySet().iterator();

        int i = 0;

        while(iterator.hasNext())
        {
            if (i == index - 1)
            {
                break;

            }

            iterator.next();
            i++;

        }

        return iterator.next();

    }


    /**
     * all data of the list will be converted to a single string
     * the method decides to write value in format of count or stock
     * @return the string made by the method
     */
    @Override
    public String toString()
    {
        Set<Product> productsKey = products.keySet();

        if (productsKey.size() == 0)
        {
            if (this instanceof Inventory)
                return "We are out of stock.";

            else
                return "List is Empty."; // there is nothing in the basket

        }

        // label is printed after each product and shows the value
        String label = "";
        if (this instanceof Inventory)
            label = "instock";
        else
            label = "count";

        StringBuilder list = new StringBuilder();
        int num = 1;

        for (Product product : productsKey)
        {
            list.append(num).append(")").append(product.toString());

            list.append(label).append(": ").append(products.get(product));

            list.append("\n");

            num++;

        }

        return list.toString();

    }


    /**
     * gets the HashMap of products and values
     * @return the products field is returned
     */
    public HashMap<Product, Integer> getProducts()
    {
        return products;

    }

}
