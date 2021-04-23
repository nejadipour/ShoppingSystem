import java.util.*;

public class Main
{
    private Inventory inventory;
    private Basket basket;
    private Scanner scanner;
    private String command;

    public Main()
    {
        this.inventory = new Inventory();
        this.basket = new Basket();
        this.scanner = new Scanner(System.in).useDelimiter("\n");

    }

    public void initInventory() throws Exception
    {
        Product product1 = new Product("Carrot", "Vegetables", 5, 20, "15-3-2020", "15-3-2021");
        inventory.addProduct(product1, 10);

        Product product2 = new Product("Apple", "Fruits", 10, 50, "1-4-2020", "1-8-2020");
        inventory.addProduct(product2, 50);

        Product product3 = new Product("12xEggs", "Egg", 100, 40, "1-1-2020", "1-6-2020");
        inventory.addProduct(product3, 20);

        Product product4 = new Product("Oats", "Grains", 70, 100, "1-6-2020", "1-1-2021");
        inventory.addProduct(product4, 45);

        Product product5 = new Product("Salmon", "Seafood", 150, 250, "1-1-2020", "1-2-2020");
        inventory.addProduct(product5, 5);

        Product product6 = new Product("Stake", "Meat", 800, 1000, "1-3-2020", "1-9-2020");
        inventory.addProduct(product6, 5);

        Product product7 = new Product("Milk", "Dairy", 100, 20, "10-1-2020", "25-1-2020");
        inventory.addProduct(product7, 20);

        Product product8 = new Product("Cheese", "Dairy", 150, 10, "1-2-2020", "15-3-2020");
        inventory.addProduct(product8, 50);

    }


    public void printProducts()
    {
        System.out.println(inventory.toString());

    }


    public void printBasket()
    {
        System.out.println("Itemsincart:");
        System.out.println(basket.toString());
        System.out.println("\ntotal price : " + basket.totalPrice());

    }


    public void userCommand()
    {
        System.out.print("Your Command : ");
        command = scanner.next();

    }


    public int numExtractor(String input)
    {
        return Integer.parseInt(input.replaceAll("[^0-9]", ""));

    }


    public void handle() throws Exception
    {
        while (!command.equals("checkout"))
        {
            if (command.contains("add"))
            {
                addToBasket(numExtractor(command));

            }
            else if (command.contains("remove"))
            {
                removeFromBasket(numExtractor(command));
                System.out.println("Product removed from basket.");

            }
            else if (command.equals("cart"))
            {
                printBasket();

            }
            else if (command.equals("products"))
            {
                printProducts();

            }
            else if (command.contains("change stock"))
            {
                changeStock(numExtractor(command));

            }
            else if (command.equals("new product"))
            {
                newProduct();

            }
            else if (command.contains("completely delete"))
            {
                completeDelete(numExtractor(command));
                System.out.println("Product deleted completely.");

            }
            else
                System.out.println("Invalid input.");

            userCommand();

        }

        System.out.println("It was a pleasure doing business with you.");
        System.exit(0);

    }


    public Product findProduct(int index, String listName)
    {
        Iterator<Product> iterator = inventory.getProducts().keySet().iterator();
        if (listName.equals("basket"))
            iterator = basket.getProducts().keySet().iterator();


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


    public void addToBasket(int index)
    {
        Product product = findProduct(index, "inventory");

        int stock = inventory.getProducts().get(product);
        Integer count = basket.getProducts().get(product);

        if (stock == 0)
        {
            System.out.println("Not available.");

        }
        else
        {
            basket.addProduct(product);
            inventory.getProducts().replace(product, --stock);

            System.out.println("Product added to basket successfully.");

        }

    }


    public void removeFromBasket(int index)
    {
        Product product = findProduct(index, "basket");

        int stock = inventory.getProducts().get(product);
        Integer count = basket.getProducts().get(product);

        if (count == null)
            count = 0;

        basket.removeProduct(product);
        inventory.getProducts().replace(product, stock + count);

    }


    public void changeStock(int index)
    {
        Product product = findProduct(index, "inventory");

        System.out.print("Enter the new stock of this product : ");
        int stock = scanner.nextInt();

        if (stock < 0)
        {
            System.out.println("Invalid input.");

        }
        else
        {
            inventory.changeStock(product, stock);

            System.out.println("Stock changed successfully.");

        }

    }


    public void newProduct() throws Exception
    {
        System.out.print("Enter the name : ");
        String name = scanner.next();

        System.out.print("Enter the category : ");
        String category = scanner.next();

        System.out.print("Enter the weight : ");
        float weight = scanner.nextFloat();

        System.out.print("Enter the price : ");
        float price = scanner.nextFloat();

        System.out.print("Enter the manufacture date : ");
        String manufacture = scanner.next();

        System.out.print("Enter the expiration date : ");
        String expiration = scanner.next();

        System.out.print("Enter the stock : ");
        int stock = scanner.nextInt();

        Product product = new Product(name, category, weight, price, manufacture, expiration);

        if (product.checkDate())
        {
            if (inventory.getProducts().get(product) == null)
            {
                inventory.addProduct(product, stock);
                System.out.println("Product added.");

            }
            else
            {
                inventory.getProducts().replace(product, stock);
                System.out.println("Product updated.");

            }

        }
        else
        {
            System.out.println("Invalid date input.");

        }

    }


    public void completeDelete(int index)
    {
        Product product = findProduct(index, "inventory");

        basket.removeProduct(product);
        inventory.removeProduct(product);

    }



    public static void main(String[] args) throws Exception
    {
        Main run = new Main();
        run.initInventory();
        run.printProducts();
        run.userCommand();
        run.handle();

    }

}
