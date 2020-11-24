

public class PropertyManagementImpl implements PropertyManagementInterface
{
    
     public void addProperty(Property property){
         System.out.println("Adding property with address: "+property.getAddress());
         //write to csv 
        }
    
    public void payTaxOnProperty(Property tax, int amount){
         System.out.println("Paying tax on property");
    }
    
    
}
