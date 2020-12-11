import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;

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
    
        public void payTaxOnProperty(Property tax, int amount){
         System.out.println("Paying tax on property");
        }
    
        
    
    
    
}
