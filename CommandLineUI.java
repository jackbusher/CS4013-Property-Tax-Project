

public class CommandLineUI
{
   
    
     PropertyManagementInterface propertyManagery;
     
     public CommandLineUI(){
        this.propertyManagery  = new PropertyManagementImpl(); 
     }
     
     public static void main(String[] args) {
    	 new GUI();
     }
}
