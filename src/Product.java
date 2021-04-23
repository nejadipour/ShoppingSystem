import java.text.SimpleDateFormat;
import java.util.Date;

public class Product
{
    private String name;
    private String category;
    private float weight;
    private float price;
    private Date manufacture;
    private Date expiration;
    private final SimpleDateFormat dateFormat;


    public Product(String name, String category, float weight, float price, String manufacture, String expiration) throws Exception
    {
        this.dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        this.name = name;
        this.category = category;
        this.weight = weight;
        this.price = price;
        this.manufacture = dateFormat.parse(manufacture);
        this.expiration = dateFormat.parse(expiration);

    }

    public boolean checkDate()
    {
        return !expiration.before(manufacture);

    }

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


    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public float getWeight() {
        return weight;
    }

    public float getPrice() {
        return price;
    }

    public String getManufacture()
    {
        return dateFormat.format(manufacture);

    }

    public String getExpiration()
    {
        return dateFormat.format(expiration);

    }

}
