import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ProductsList
{
    protected HashMap<Product, Integer> products;

    public ProductsList()
    {
        products = new HashMap<>();

    }


    public void removeProduct(Product product)
    {
        products.remove(product);

    }


    public void updateProduct(Product product, int num)
    {
        products.replace(product, num);

    }


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


    @Override
    public String toString()
    {
        StringBuilder list = new StringBuilder();
        int num = 1;
        Set<Product> productsKey = products.keySet();

        if (productsKey.size() == 0)
        {
            if (this instanceof Inventory)
                return "We are out of stock.";

            else
                return "List is Empty.";

        }

        for (Product product : productsKey)
        {
            list.append(num).append(")").append(product.toString());

            if (this instanceof Inventory)
            {
                list.append("instock: ").append(products.get(product));

            }
            else
                list.append("count: ").append(products.get(product));

            list.append("\n");

            num++;

        }

        return list.toString();

    }

    public HashMap<Product, Integer> getProducts()
    {
        return products;

    }

}
