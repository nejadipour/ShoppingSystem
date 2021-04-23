import java.util.*;

/**
 * the system will run in this class
 * @author Alireza Nejadipour
 * @version 3.6
 */

public class Main
{
    private final Inventory inventory;
    private final Basket basket;
    private final Scanner scanner;
    private String command;

    /**
     * create a new Main
     */
    public Main()
    {
        this.inventory = new Inventory();
        this.basket = new Basket();
        this.scanner = new Scanner(System.in).useDelimiter("\n");

    }


    /**
     * adds new products to the inventory before the run
     * @throws Exception if an error occurs within the method
     */
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


    /**
     * all the products in the inventory will be placed
     */
    public void printProducts()
    {
        System.out.println(inventory.toString());

    }


    /**
     * all the products in the basket will be placed
     * then the total price will be printed
     */
    public void printBasket()
    {
        System.out.println("Itemsincart:");
        System.out.println(basket.toString());
        System.out.println("\ntotal price : " + basket.totalPrice());

    }


    /**
     * scans the input of user every time
     */
    public void userCommand()
    {
        System.out.print("Your Command : ");
        command = scanner.next();

    }


    /**
     * extracts numbers in the string
     * @param input the string that number should be extracted from
     * @return number will be returned
     */
    public int numExtractor(String input)
    {
        return Integer.parseInt(input.replaceAll("[^0-9]", ""));

    }


    /**
     * decides what to happen based on the user's command
     * @throws Exception if an error occurs within the method
     */
    public void handle() throws Exception
    {
        /*
        The system supports these key words :
        add k : adds the product by index k from the inventory to the basket
        remove k : removes the product by the index k from basket
        cart : prints products in the basket
        products : prints all the products in the inventory
        change stock k : changes the stock of product with index k in the inventory
        new product : if u wanna add a new product to the inventory u should type this keyword
        completely delete k : deletes product with index k in the inventory from every where
        checkout : your shop is done and the program is finished
         */
        while (!command.equals("checkout"))
        {
            if (command.contains("add"))
            {
                addToBasket(numExtractor(command));

            }
            else if (command.contains("remove"))
            {
                removeFromBasket(numExtractor(command));

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

        // the message before exit
        System.out.println("It was a pleasure doing business with you.");
        System.exit(0);

    }


    /**
     * adds the product by index to the basket
     * calls related methods
     * @param index the index of the product in the list of products of the inventory
     */
    public void addToBasket(int index)
    {
        Product product = inventory.findProduct(index);

        int stock = inventory.getProducts().get(product);

        // check if there is any of the product in the inventory
        if (stock == 0)
        {
            System.out.println("Not available.");

        }
        else
        {
            basket.addProduct(product);
            inventory.updateProduct(product, --stock);

            System.out.println("Product added to basket successfully.");

        }

    }


    /**
     * removes the product by index from the basket
     * calls related methods
     * @param index the index of the product in the list of products of the basket
     */
    public void removeFromBasket(int index)
    {
        Product product = basket.findProduct(index);

        int stock = inventory.getProducts().get(product);
        Integer count = basket.getProducts().get(product);

        // handle what to happen by the number of product in the basket
        if (count == 1)
        {
            basket.removeProduct(product);

        }
        else
        {
            System.out.print("Remove all of them? (y/n) : "); // input should be 'y' or 'n'
            if (scanner.next().equals("y"))
            {
                basket.removeProduct(product);

            }
            else
            {
                System.out.print("Tell me how many of this product to remove : (from " + count + ") : ");
                int inp = scanner.nextInt();
                if (inp > count)
                {
                    System.out.println("Invalid input.");
                    return;

                }
                else
                {
                    basket.updateProduct(product, count - inp);
                    count = inp;

                }

            }

        }

        inventory.updateProduct(product, stock + count);
        System.out.println("Product removed from basket.");

    }


    /**
     * scans the new stock and changes the stock of product by given index
     * @param index the index of the product in the list of products of the inventory
     */
    public void changeStock(int index)
    {
        Product product = inventory.findProduct(index);

        System.out.print("Enter the new stock of this product : ");
        int stock = scanner.nextInt();

        if (stock < 0)
        {
            System.out.println("Invalid input.");

        }
        else
        {
            inventory.updateProduct(product, stock);

            System.out.println("Stock changed successfully.");

        }

    }


    /**
     * scans all details needed to make a new product
     * and adds it to the list
     * @throws Exception if an error occurs within the method
     */
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
            // check if the product is already available or not
            if (inventory.getProducts().get(product) == null)
            {
                inventory.addProduct(product, stock);
                System.out.println("Product added.");

            }
            else
            {
                inventory.updateProduct(product, stock);
                System.out.println("Product updated.");

            }

        }
        else
        {
            System.out.println("Invalid date input.");

        }

    }


    /**
     * removes the product by index from the inventory
     * it removes the product if it is in the basket too
     * @param index the index of the product in the list of products of the inventory
     */
    public void completeDelete(int index)
    {
        Product product = inventory.findProduct(index);

        basket.removeProduct(product);
        inventory.removeProduct(product);

    }



    public static void main(String[] args) throws Exception
    {
        Main run = new Main();
        run.initInventory();
        // first of all products should be printed
        run.printProducts();
        run.userCommand();
        run.handle();

    }

}
