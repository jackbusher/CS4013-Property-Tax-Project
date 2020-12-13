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
     String[][] data = new String[200][200];
     String[] csvArray = new String[200];
     int addressCount=0;
    
    
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
          JButton viewTax = new JButton("<html><span style='font-size:20px'>"+"Calculate tax on property"+"</span></html>");
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
            		  
            		  updateTaxPayment(false, address, 0); 
            	  
                        
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
                    	    	updateTaxPayment(true, address.getText(), Double.parseDouble(amount.getText())); //update csv file with new taxlist
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
         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         frame.add(container);
         container.revalidate();
         container.repaint();
         
         JLabel c = new JLabel("Payment List");	//placeholder content
         panel.add(c);
         
         
         c.setFont(new Font("Calibri", Font.PLAIN, 24));

         String csvFile = "propertiesTax.csv";
         BufferedReader br = null;
         String line = "";
         String cvsSplitBy = ",";
         int biggestRow = 2;

         try {

             br = new BufferedReader(new FileReader(csvFile));

			int rowCount=0;
			String[] tempArr1;
			while ((line = br.readLine()) != null) {

            	 tempArr1 = line.split(cvsSplitBy);
            	 
            	 if(tempArr1.length > biggestRow) {
            	 biggestRow = tempArr1.length;}

            	 data[rowCount] = tempArr1;
            	 rowCount++;

             }
			
			for(int p=0; p<data.length; p++) {	//resize arrays to be filled with null values in order to increase headings
			if(data[p].length < biggestRow) {
				int rowL = data[p].length;
				
				String[] temp = new String[biggestRow];
				for(int x=0; x<rowL; x++) {
	            			temp[x] = data[p][x];
	            			data[p] = temp;
	             }
			}
			}


          // Table 
             JTable j;

          // Column Names 
             String[] columnNames = new String[biggestRow];
             columnNames[0] = "Address";
             columnNames[1] = "Balance"; 
             
             for(int x=0; x<biggestRow-2; x++) {
            	 columnNames[x+2] = "Payment " + (x+1);
             }
             

             // Initializing the JTable 
             j = new JTable(data, columnNames); 

             j.setFont(new Font("Calibri", Font.BOLD, 14));
             j.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 20));
             j.setRowHeight(24);

             // adding it to JScrollPane 
             JScrollPane sp = new JScrollPane(j); 

             // Table 
             panel.add(sp); 

             // Frame Size 
             j.setSize(500, 200); 

             panel.setVisible(true);



         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         } finally {
             if (br != null) {
                 try {
                     br.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }
    	 
     }

     public void envMan() {
    	 

    	 System.out.println("Manage DOE has been called");
         container.remove(panel);
         panel.removeAll();

         container.add(panel);
         frame.add(container);
         container.revalidate();
         container.repaint();

         panel.setLayout(new GridLayout(7,2));

        JLabel l1 = new JLabel("<html><span style='font-size:20px'>"+"Property Tax Data for a Property. Enter the address of the property"+"</span></html>");
        JLabel l2 = new JLabel("<html><span style='font-size:20px'>"+"Property Tax Data for an owner. Enter a name of an owner"+"</span></html>");
        JButton b1 = new JButton("<html><span style='font-size:20px'>"+"Submit"+"</span></html>");
        JButton b2 = new JButton("<html><span style='font-size:20px'>"+"Submit"+"</span></html>");
        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();

        JLabel l3 = new JLabel("<html><span style='font-size:20px'>"+"Enter a year and eircode(optional) to get tax stats from that year (and area)"+"</span></html>");
        JTextField t3 = new JTextField();
        JTextField t4 = new JTextField();
        JButton b3 = new JButton("<html><span style='font-size:20px'>"+"Submit"+"</span></html>");
        JLabel l4 = new JLabel("<html><span style='font-size:20px'>" +"Enter an eircode to get stats on this area" +"</span><html>");
        JTextField t5 = new JTextField();
        JButton b4 = new JButton("<html><span style='font-size:20px'>"+"Submit"+"</span></html>");

        panel.add(l1);
        panel.add(t1);
        panel.add(b1);
        panel.add(l2);
        panel.add(t1);
        panel.add(t2);
        panel.add(b1);
        panel.add(b2);

        panel.add(l3);
        panel.add(t3);
        panel.add(l4);
        panel.add(t3);        
        panel.add(t4);
        panel.add(b3);
        panel.add(t5);
        panel.add(b3);     
        panel.add(b4);

	 
     }
     
     
     public String updateTaxPayment(boolean subtract, String address, double payment) {
    	 String[] rowArr = null;
    	 int countTrack =0;
    	 boolean addressHere = false;
    	 int count=0;
    	 
         //check if the address is on record
         for(int i=0;i<Tax.taxlist.size();i++) {
           if(Tax.taxlist.get(i).address.equals(address)) {
        	   System.out.println("updating propertiesTax.csv");
        	   
        	   try { //read csv
               	BufferedReader csvReader = new BufferedReader(new FileReader("propertiesTax.csv"));
               	String row;
               count =0;
				try {
					while ((row = csvReader.readLine()) != null) {
						
						 csvArray[count] = row; 
						 count++;
						 
					    if (row.contains(address)) {
					    	 rowArr = row.split(","); //array of content of one row
					    	 countTrack = count-1;
					    	 addressHere = true;
					    	
					    }
					}
					
					csvReader.close();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
               	try {
					csvReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}}
        	   catch (FileNotFoundException e1) {
                   System.out.println(e1.getMessage());
                 }

           	//write to csv 
        	   if(subtract) {
               try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("propertiesTax.csv"), false))) {

                   StringBuilder sb = new StringBuilder();
                   
                   for(int r=0; r<addressCount; r++) {
                	   
                	   if(r == countTrack) {
                		   sb.append(Tax.taxlist.get(i).getAddress());
                           sb.append(',');
                           sb.append(taxValue);
                    	   for(int x=0; x<rowArr.length-2; x++) {
                    		   sb.append(',');
                    		   sb.append(rowArr[x+2]);
                               
                    	   }	
                    	   if(payment>0) {
                    		   sb.append(',');
                    		   sb.append(payment);
                        	   sb.append('\n');
                    	   }else {
                    	   sb.append('\n');
                    	   }
                	   }else {
                	   sb.append(csvArray[r]);}
                	   if(r == addressCount-1) {
                		 //  sb.append(',');
                		   sb.append('\n');
                	   }
                	   System.out.println("updating csv " + csvArray[r]);
                	   
                   }
                   
                   
                   	
             
                   writer.write(sb.toString());
                   writer.flush();
                   writer.close();
             
                   System.out.println("done!");
                   taxString = String.valueOf(Tax.taxlist.get(i).accumTax(true, taxValue));
             
                 } catch (FileNotFoundException l) {
                   System.out.println(l.getMessage());
                 }
        	   }else {
        		   if(addressHere == false) { //if address is already present no need to write tax
        			   addressCount++;
        		   try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("propertiesTax.csv"), true))) {

                       StringBuilder sb = new StringBuilder();
                       
                       if(count>=Tax.taxlist.size()) {
                    	   count--;
                       }

                       sb.append(Tax.taxlist.get(count).getAddress());
                       sb.append(',');
                       sb.append(Tax.taxlist.get(count).accumTax(false, 0));
                       sb.append('\n');
                      
                 
                       writer.write(sb.toString());
                       System.out.println(sb);
                 
                       System.out.println("Done!");
                       taxString = String.valueOf(Tax.taxlist.get(i).accumTax(false, 0));
                 
                     } catch (FileNotFoundException l) {
                       System.out.println(l.getMessage());
                     }
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