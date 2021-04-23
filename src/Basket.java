import java.util.Set;

public class Basket extends ProductsList
{
    public Basket()
    {
        super();

    }


    public void addProduct(Product product)
    {
        Integer count = products.get(product);

        if (count == null)
        {
            products.put(product, 1);
        }
        else
        {
            products.replace(product, ++count);

        }


    }


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
