import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class PropertyManagementImpl implements PropertyManagementInterface
{

    public void addProperty(Property property){
        System.out.println("Adding property with address: "+property.getAddress());
        //write to csv 
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("properties.csv"), true))) {

            StringBuilder sb = new StringBuilder();
            sb.append(property.getOwners());
            sb.append(',');
            sb.append(property.getAddress());
            sb.append(',');
            sb.append(property.getValue() );
            sb.append(',');
            sb.append(property.locationCategory() );
            sb.append('\n');

            writer.write(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public ArrayList<Property> getPropertiesFromFile(){
        ArrayList<Property> properties = new ArrayList<Property>();

        try{
            String splitBy =",";
            BufferedReader br = new BufferedReader(new FileReader("properties.csv"));
            String line = br.readLine();
            int counter = 0;

            while ((line = br.readLine()) != null){
                counter++;
                String[] b = line.split(splitBy);
                // Property(String owners, String address, String eircode, int value, String locationCategory, char ppr
                Property property = new Property(b[0],b[1],"",Integer.parseInt(b[2]),b[3],'n');
                properties.add(property);
                //     for (int x = 0; x < b.length; x++){
                //        System.out.println(b[x]);
                //   }

            }
            System.out.println(counter);
            br.close();
        }
        catch(FileNotFoundException fe){
            System.out.println("File not there!");
        }
        catch(IOException io){
            System.out.println("IO Exception!");
        }
        return properties;
    }

    public void payTaxOnProperty(Property tax, int amount){
        System.out.println("Paying tax on property");
    }

    public double getTaxForProperty (String address) {
        ArrayList<Property> properties = getPropertiesFromFile();

        Property property = null;
        for(int  i =  0; i < properties.size();  i++) {
            if(address.equals((properties.get(i)).getAddress())) {
                property = properties.get(i);
            }
        }

        Tax tax = new Tax();
        return tax.getTaxForProperty(property);
        //   return "tax";
    }

    public double getTaxForOwner (String name) {
        ArrayList<Property> properties = getPropertiesFromFile();

        Property property = null;
        for(int  i =  0; i < properties.size();  i++) {
            if(name.equals((properties.get(i)).getOwners())) {
                property = properties.get(i);
            }
        }
        Tax tax = new Tax();
        return tax.getTaxForProperty(property);
    }

    public String getOverdueTax (int year){
        if(year > 1){
            //int overdue = accumTax - \
        }
        return "";
    }

    public String getOverdueTax (int year, String eircode) {
        return  "";
    } 

}
