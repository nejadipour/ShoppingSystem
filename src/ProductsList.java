import java.util.HashMap;
import java.util.Set;

public class ProductsList
{
    protected HashMap<Product, Integer> products;

    public ProductsList()
    {
        products = new HashMap<>();

    }

    // TODO: ۲۳/۰۴/۲۰۲۱ methods needed to add or remove a product


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

            list.append("\n");

            num++;

        }

        return list.toString();

    }

}
