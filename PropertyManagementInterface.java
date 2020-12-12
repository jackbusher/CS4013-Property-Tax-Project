import java.util.ArrayList;
public interface PropertyManagementInterface
{

    public void addProperty(Property property);

    public void payTaxOnProperty(Property tax, int amount);

    public ArrayList<Property> getPropertiesFromFile();

    public double getTaxForProperty (String address);

    public double getTaxForOwner (String name);

    public String getOverdueTax (int year);

    public String getOverdueTax (int year, String eircode);
}
