import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPanel extends JPanel{
    private JLabel urlLabel;
    private JTextField urlField;
    private JLabel refNumLabel;
    private JTextField refNumField;
    private JLabel errorLabel;
    private JButton enterClass;

    public InputPanel(){
        //set panel features 
        setLayout(new GridBagLayout());
        setBackground(new Color(235, 235, 235));
        
        //call methods to initalize, format, and add jcomponents
        initializeComponents();
        addComponents();
    }
    
     /*
     * initalizes all JComponents and sets their preferred size
     */
    public void initializeComponents(){
        //initialize components and set their size
        urlLabel = new JLabel("Enter the URL with the Class Listings:");
        urlLabel.setPreferredSize(new Dimension(220, 22));
        urlField = new JTextField("https://");
        urlField.setPreferredSize(new Dimension(200, 22));
        refNumLabel = new JLabel("Enter the Reference Number of the Class You Want to Add");
        refNumLabel.setPreferredSize(new Dimension(300, 22));
        refNumField = new JTextField("12345");
        refNumField.setPreferredSize(new Dimension(200, 22));
        errorLabel = new JLabel();
        errorLabel.setPreferredSize(new Dimension(200, 22));
        enterClass = new JButton("Enter Class");
        enterClass.setPreferredSize(new Dimension(200, 22));
    }

    public void addComponents(){
         //add components
        GridBagConstraints g = new GridBagConstraints();
        //to increase space between components, increase their height in previous method or add: 
        //g.insets = new Insets(10, 0, 10, 0);

        g.gridx = 0;
        g.gridy = 0;
        add(urlLabel, g);

        g.gridy = 1;
        add(urlField, g);

        g.gridy = 2;
        add(refNumLabel, g);

        g.gridy = 3;
        add(refNumField, g);

        g.gridy = 4;
        add(errorLabel, g);

        g.gridy = 5;
        add(enterClass, g);
    }
}
