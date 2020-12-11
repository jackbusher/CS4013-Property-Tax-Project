import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GUI {
	 JPanel container = new JPanel(); //container panel to fit into frame, holds both panels
     JFrame frame = new JFrame(); //frame for entire GUI
     JPanel panel = new JPanel(); //RHS of screen, holds content of one of 4 chosen utilities
     String taxString;
     double taxValue;
     boolean paid = false;
    
    
    PropertyManagementInterface propertyManagery;
    
    public GUI() {
        
        this.propertyManagery  = new PropertyManagementImpl();
        home(); //home screen is the LHS of screen with 4 different utilities
        }
        
        
    public void home(){
        	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  
        

        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        //JPanel panel2 = new JPanel();
        
        
        container.add(panel1);
        //container.add(panel2);
        
        frame.add(container, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
       // frame.pack();
        frame.setSize((int)screenSize.getWidth()-200,(int)screenSize.getHeight()-200);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();

        
        //create and add jbuttons to panel1
        JButton r = new JButton("Register a Property");
        JButton m = new JButton("Manage Properties");
        JButton v = new JButton("View Payments");
        JButton doe = new JButton("Department of Environnment Management");
        
        r.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        	    register();
        	  } 
        	} );
        m.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        	    manage();
        	  } 
        	} );
        v.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        	    view();
        	  } 
        	} );
        doe.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        	    envMan();
        	  } 
        	} );
        
        panel1.add(Box.createVerticalGlue());
        panel1.add(r);
        panel1.add(Box.createVerticalGlue());
        panel1.add(m);
        panel1.add(Box.createVerticalGlue());
        panel1.add(v);
        panel1.add(Box.createVerticalGlue());
        panel1.add(doe);
        panel1.add(Box.createVerticalGlue());
        
        
        panel1.setVisible(true);
        panel1.revalidate();
        panel1.repaint();
    }
        
        ArrayList<Property> properties = new ArrayList<Property>();
        
   public void register() {
        	System.out.println("Register Property has been called");
        //JLabel label = new JLabel("Enter infomation");
        container.remove(panel);
        panel.removeAll();

        //JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8,2)); // set rhs panel layout
        container.add(panel);
        frame.add(container);
        container.revalidate();
        container.repaint();
        	
        JLabel label2 = new JLabel("<html><span style='font-size:20px'>"+"Owners"+"</span></html>");
        JLabel label3 = new JLabel("<html><span style='font-size:20px'>"+"Value"+"</span></html>");
        JLabel label4 = new JLabel("<html><span style='font-size:20px'>"+"Eircode"+"</span></html>");
        JLabel label5 = new JLabel("<html><span style='font-size:20px'>"+"Location Category"+"</span></html>");
        JLabel label6 = new JLabel("<html><span style='font-size:20px'>"+"Principle Private Residence?(Y/N)"+"</span></html>");
        JLabel label7 = new JLabel("<html><span style='font-size:20px'>"+"Address"+"</span></html>");
        JLabel emptylabel = new JLabel("");

        JTextField ownerTextField = new JTextField(10);
        JTextField valueTextField = new JTextField(20);
        JTextField eircodeTextField = new JTextField(20);
        JTextField locationCategoryTextField = new JTextField(20);
        JTextField pPRTextField = new JTextField(20);
        JTextField addressTextField = new JTextField(20);

        JButton submit = new JButton("<html><span style='font-size:20px'>"+"Submit"+"</span></html>");

        panel.add(label2);
        panel.add(ownerTextField);
        panel.add(label3);
        panel.add(valueTextField);
        panel.add(label4);
        panel.add(eircodeTextField);
        panel.add(label5);
        panel.add(locationCategoryTextField);
        panel.add(label6);
        panel.add(pPRTextField);
        panel.add(label7);
        panel.add(addressTextField);
        panel.add(emptylabel);       
        panel.add(submit);
        
        
        
        submit.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
                  
                  //create property from form fields
                  Property property = new Property(ownerTextField.getText(), addressTextField.getText(),
                  eircodeTextField.getText(), Integer.parseInt(valueTextField.getText()), locationCategoryTextField.getText(), 
                  pPRTextField.getText().charAt(0));
                  

                  //Add property using property manager object
                 propertyManagery.addProperty(property);
                 //Add property to properties array
                 properties.add(property);
                 Tax taxProp = new Tax(property.getOwners(), property.getAddress(), property.eircode(), property.getValue(), property.locationCategory(), property.getPPR() );
                 Tax.addTaxList(taxProp); // add to taxlist
                 // display/center the jdialog when the button is pressed
                JDialog d = new JDialog(frame, "Property Added", true);
                d.setLocationRelativeTo(frame);
                d.setVisible(true);
              }
    });
        


    }
        
    public void manage() { 
    	
    	 
    	 System.out.println("Manage Properties has been called");
         container.remove(panel);
         panel.removeAll();

         container.add(panel);
         frame.add(container);
         container.revalidate();
         container.repaint();

          
          // need to create a layout where you have the option to pay tax on a house. you enter the address of the house you'd like to pay
          //tax on and how much. submit it then it stores the tax paid. also a button to see how much tax remains on a house when entering the address
          //JPanel panel2 = new JPanel();
          panel.setLayout(new GridLayout(8,2));
          JButton viewTax = new JButton("<html><span style='font-size:20px'>"+"View tax on property"+"</span></html>");
          JButton payTax = new JButton("<html><span style='font-size:20px'>"+"Pay tax on property"+"</span></html>");
          JTextField address = new JTextField();
          JTextField addressBox = new JTextField();
          JTextField amount = new JTextField();
          JLabel viewTax2 = new JLabel("<html><span style='font-size:20px'>"+"Enter your address to see how much tax is outstanding"+"</span></html>");

          
          JLabel payTaxHeader = new JLabel("<html><span style='font-size:20px'>"+"Enter your address first and then the amount of tax you'd like to pay off"+"</span></html>");
          panel.add(viewTax2);         
          panel.add(addressBox);
          panel.add(viewTax);
          panel.add(payTaxHeader);         
          panel.add(address);         
          panel.add(amount);
          panel.add(payTax);

          viewTax.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
            	  String address = addressBox.getText();
            	  
            	  if(paid == false) {
            		  updateTaxPayment(false, address); 
            	  }
                        
                // display/center the jdialog when the button is pressed
                JDialog d = new JDialog(frame, taxString);
                          d.setLocationRelativeTo(frame);                          
                           d.setVisible(true);
                           

              }
    });
          
          
          payTax.addActionListener(new ActionListener()
          {
            String row = "";
            String[] tempArr;
            public void actionPerformed(ActionEvent e)
            {
            	address.getText();
            	for(int i=0;i<properties.size();i++) {
                    if(properties.get(i).address.equals( address.getText())) {
                    	
                    	try {
                    	BufferedReader csvReader = new BufferedReader(new FileReader("propertiesTax.csv"));
                    	while ((row = csvReader.readLine()) != null) {
                    	    if (row.contains(address.getText())) {
                    	    	tempArr = row.split(",");
                    	    	taxValue = Double.parseDouble(tempArr[1]);
                    	    	taxValue = taxValue - Double.parseDouble(amount.getText()); //new tax value after payment
                    	    	Tax.taxlist.get(i).accumTax(true, taxValue); //update new tax value in taxlist
                    	    	updateTaxPayment(true, address.getText()); //update csv file with new taxlist
                    	    	paid = true;
                    	    	break;
                    	    }
                    	}
                    	csvReader.close();}
                    	
                     catch (FileNotFoundException e1) {
                        System.out.println(e1.getMessage());
                      } catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                    	
                    	
                    }
                    }
            	
            	
            } //end of actionPerformed
            
          
          
          });

     }
        
     public void view() {
    	 
    	 System.out.println("View Payments has been called");
         container.remove(panel);
         panel.removeAll();

         container.add(panel);
         frame.add(container);
         container.revalidate();
         container.repaint();
         
         JLabel c = new JLabel("Payment List");	//placeholder content
         panel.add(c);
    	 
     }

     public void envMan() {
    	 
    	 System.out.println("Manage DOE has been called");
         container.remove(panel);
         panel.removeAll();

         container.add(panel);
         frame.add(container);
         container.revalidate();
         container.repaint();

         panel.setLayout(new GridLayout(10,1));
         
        JLabel l1 = new JLabel("<html><span style='font-size:20px'>"+"Property Tax Data for a Property. Enter the address of the property"+"</span></html>");
        JLabel l2 = new JLabel("<html><span style='font-size:20px'>"+"Property Tax Data for an owner. Enter a name of an owner"+"</span></html>");
        JButton b1 = new JButton("<html><span style='font-size:20px'>"+"Submit"+"</span></html>");
        JButton b2 = new JButton("<html><span style='font-size:20px'>"+"Submit"+"</span></html>");
        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();

        JLabel l3 = new JLabel("<html><span style='font-size:20px'>"+"Get a list of all overdue property tax for a particular year. You can also choose a certain Eircode in the second box. Leave it blank if you dont want to"+"</span></html>");
        JTextField t3 = new JTextField();
        JTextField t4 = new JTextField();
        JButton b3 = new JButton("<html><span style='font-size:20px'>"+"Submit"+"</span></html>");

        panel.add(l1);
        panel.add(t1);
        panel.add(b1);
        panel.add(l2);
        panel.add(t2);
        panel.add(b2);
        panel.add(l3);
        panel.add(t3);
        panel.add(t4);
        panel.add(b3);

        
         
     }  
     
     
     public String updateTaxPayment(boolean subtract, String address) {

         //check if the address is on record
         for(int i=0;i<Tax.taxlist.size();i++) {
           if(Tax.taxlist.get(i).address.equals(address)) {
        	   System.out.println("updating propertiesTax.csv");

           	//write to csv 
        	   if(subtract) {
               try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("propertiesTax.csv"), false))) {

                   StringBuilder sb = new StringBuilder();
                   for(int j=0; j<Tax.taxlist.size(); j++) {
                   sb.append(Tax.taxlist.get(i).getAddress());
                   sb.append(',');
                   sb.append(Tax.taxlist.get(i).accumTax(true, taxValue));
                   sb.append('\n');
                   }
             
                   writer.write(sb.toString());
             
                   System.out.println("done!");
                   taxString = String.valueOf(Tax.taxlist.get(i).accumTax(true, taxValue));
             
                 } catch (FileNotFoundException l) {
                   System.out.println(l.getMessage());
                 }
        	   }else {
        		   try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("propertiesTax.csv"), false))) {

                       StringBuilder sb = new StringBuilder();
                       for(int j=0; j<Tax.taxlist.size(); j++) {
                       sb.append(Tax.taxlist.get(i).getAddress());
                       sb.append(',');
                       sb.append(Tax.taxlist.get(i).accumTax(false, 0));
                       sb.append('\n');
                       }
                 
                       writer.write(sb.toString());
                 
                       System.out.println("done!");
                       taxString = String.valueOf(Tax.taxlist.get(i).accumTax(false, 0));
                 
                     } catch (FileNotFoundException l) {
                       System.out.println(l.getMessage());
                     }
        	   }
           	
             
           }
         }
		return taxString;
           
     }
    
        
        
        
        public static void main(String[] args) {
            new GUI();

        }
}