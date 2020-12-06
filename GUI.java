import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
	 JPanel container = new JPanel(); //container panel to fit into frame, holds both panels
     JFrame frame = new JFrame(); //frame for entire GUI
     JPanel panel = new JPanel(); //RHS of screen, holds content of one of 4 chosen utilities
    
    
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
        
        
        
   public void register() {
        	System.out.println("Register Property has been called");
        //JLabel label = new JLabel("Enter infomation");
        container.remove(panel);
        panel.removeAll();

        //JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,2)); // set rhs panel layout
        container.add(panel);
        frame.add(container);
        container.revalidate();
        container.repaint();
        	
        JLabel label2 = new JLabel("<html><span style='font-size:20px'>"+"Owners"+"</span></html>");
        JLabel label3 = new JLabel("<html><span style='font-size:20px'>"+"Value"+"</span></html>");
        JLabel label4 = new JLabel("<html><span style='font-size:20px'>"+"Eircode"+"</span></html>");
        JLabel label5 = new JLabel("<html><span style='font-size:20px'>"+"Location Category"+"</span></html>");
        JLabel label6 = new JLabel("<html><span style='font-size:20px'>"+"Principle Private Residence?(Y/N)"+"</span></html>");
        JLabel emptylabel = new JLabel("");

        JTextField ownerTextField = new JTextField(10);
        JTextField valueTextField = new JTextField(20);
        JTextField eircodeTextField = new JTextField(20);
        JTextField locationCategoryTextField = new JTextField(20);
        JTextField pPRTextField = new JTextField(20);

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
        panel.add(emptylabel);       
        panel.add(submit);
        
        submit.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
                  
                  //create property from form fields
                  Property property = new Property(ownerTextField.getText(), "CREATE ADDRESS FORMFILELD",
                  eircodeTextField.getText(), Integer.parseInt(valueTextField.getText()), locationCategoryTextField.getText(), 
                  pPRTextField.getText().charAt(0));

                  //Add propery using propery manager object
                 propertyManagery.addProperty(property);
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
         
         JLabel c = new JLabel("Property List");	//placeholder content
         panel.add(c);
    	 
     }
        
     public void view() {
    	 
    	 System.out.println("View Payemnts has been called");
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
         
         JLabel c = new JLabel("List of all properties/owners and way to sort them");	//placeholder content
         panel.add(c);
	 
     }

        
        
        
        public static void main(String[] args) {
            new GUI();

        }
}