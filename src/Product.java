import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * this class stores the data of each product
 * such as its name, weight, price etc
 * @author Alireza Nejadipour
 * @version 1.2
 */

public class Product
{
    private final String name;
    private final String category;
    private final float weight;
    private final float price;
    private final Date manufacture;
    private final Date expiration;
    private final SimpleDateFormat dateFormat;


    /**
     * create a new product with given parameters
     * @param name the name of the product
     * @param category which category the product belongs to
     * @param weight the weight of the product
     * @param price how much does it cost
     * @param manufacture the manufacture date
     * @param expiration the expiration date
     * @throws Exception if an error occurs within the method
     */
    public Product(String name, String category, float weight, float price, String manufacture, String expiration) throws Exception
    {
        // date format throw the program is declared here
        this.dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        this.name = name;
        this.category = category;
        this.weight = weight;
        this.price = price;
        this.manufacture = dateFormat.parse(manufacture);
        this.expiration = dateFormat.parse(expiration);

    }


    /**
     * checks if the expiration date is valid
     * compared to the manufacture date
     * @return if it is valid returns true
     */
    public boolean checkDate()
    {
        return !expiration.before(manufacture);

    }


    /**
     * converts data about product into a single string
     * the string is in format of JSON
     * @return the string made
     */
    @Override
    public String toString()
    {
        return "{\n" +
                "\t\"Product\": {\n" +
                "\t\t\"NAME\": " + "\"" + getName() + "\",\n" +
                "\t\t\"CATEGORY\": " + "\"" + getCategory() + "\",\n" +
                "\t\t\"WEIGHT\": " + "\"" + getWeight() + "\",\n" +
                "\t\t\"PRICE\": " + "\"" + getPrice() + "\",\n" +
                "\t\t\"MANUFACTURE_DATE\": " + getManufacture() + ",\n" +
                "\t\t\"EXPIRATION_DATE\": " + getExpiration() + "\n" +
                "\t}\n" +
                "}";

    }


    /**
     * gets the name of product
     * @return the name field
     */
    public String getName()
    {
        return name;

    }

    /**
     * gets the category of the product
     * @return category field is returned
     */
    public String getCategory()
    {
        return category;

    }

    /**
     * gets the weight of the product
     * @return weight parameter is returned
     */
    public float getWeight()
    {
        return weight;

    }

    /**
     * gets the price of the product
     * @return price field is returned
     */
    public float getPrice()
    {
        return price;

    }

    /**
     * gets the manufacture date
     * in format of string
     * @return converts date to string and returns it
     */
    public String getManufacture()
    {
        return dateFormat.format(manufacture);

    }

    /**
     * gets the expiration date
     * in format of string
     * @return converts date to string and returns it
     */
    public String getExpiration()
    {
        return dateFormat.format(expiration);

    }

}
