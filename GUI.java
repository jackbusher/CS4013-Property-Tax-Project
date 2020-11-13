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
        
        JLabel label = new JLabel("Enter infomation");
        JLabel label2 = new JLabel("Owners");
        JLabel label3 = new JLabel("Value");
        JLabel label4 = new JLabel("Eircode");

        JTextField textfield;
        textfield = new JTextField(20);
        textfield.setBounds(400,40,165,20);
        frame.add(textfield);

        label.setBounds(10, 20, 150, 25);
        label2.setBounds(10, 40, 150, 25);
        label3.setBounds(10, 60, 150, 25);
        label4.setBounds(10, 80, 150, 25);
        
        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);

    }
        public static void main(String[] args) {
            new GUI();

        }
}