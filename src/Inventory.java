public class Inventory extends ProductsList
{
    public Inventory()
    {
        super();

    }


    public void addProduct(Product product, int stock)
    {
        products.put(product, stock);

    }


    public void changeStock(Product product, int stock)
    {
        products.replace(product, stock);

    }

}
