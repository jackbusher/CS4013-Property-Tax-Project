import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    
    public GUI() {

        JFrame frame = new JFrame();     

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(200,200,100,100));
        panel.setLayout(new GridLayout(0,1));
        
        

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);
        
        //JLabel label = new JLabel("Enter infomation");
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
        
        

        panel.setLayout(new GridLayout(6,2));

    }
        public static void main(String[] args) {
            new GUI();

        }
}