public class Inventory extends ProductsList
{
    public Inventory()
    {
        super();

    }


    public void changeStock(Product product, int stock)
    {
        products.replace(product, stock);

    }

}
