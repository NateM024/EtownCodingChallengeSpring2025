import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class TopViewPanel extends JPanel {
    private JLabel scheduleLabel;
    private JLabel creditsLabel;
    private JTextArea noteArea;

    // Constructor 
    public TopViewPanel(){
        initializeComponents();
        addComponents();    
    }

    // Initializes all JComponents
    public void initializeComponents(){
        //new fonts
        Font bigFont = new Font("Arial", Font.BOLD, 26);
        Font smallerFont = new Font("Arial", Font.BOLD, 14);

        //create the schedule title label
        scheduleLabel = new JLabel("", SwingConstants.CENTER);
        scheduleLabel.setFont(bigFont);
        
        //create the credits label
        creditsLabel = new JLabel("", SwingConstants.CENTER);
        creditsLabel.setFont(smallerFont);
        
        //create note area;
        noteArea = new JTextArea("", 2, 40);
        Dimension size = new Dimension(200, 40);
        noteArea.setPreferredSize(size);
        noteArea.setMaximumSize(size);
        noteArea.setEditable(false);
        noteArea.setWrapStyleWord(true);
        noteArea.setLineWrap(true);
        noteArea.setCaretPosition(0);
        noteArea.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 2));
    }

    // Adds all JComponets to Panel
    public void addComponents(){
        setLayout(new BorderLayout(0,0));

        //Create JPanel for the labels
        JPanel northPanel = new JPanel(new FlowLayout());

        // Create JPanel to keep creditslabel base aligned with schedulelabel base
        JPanel creditsWrapper = new JPanel(new BorderLayout());
        creditsWrapper.setBorder(BorderFactory.createEmptyBorder(9, 0, 0, 0));
        creditsWrapper.add(creditsLabel, BorderLayout.NORTH);

        // Add components to northPanel
        northPanel.add(scheduleLabel);
        northPanel.add(Box.createHorizontalStrut(50));
        northPanel.add(creditsWrapper);

        // Add northPanel to panel
        add(northPanel, BorderLayout.NORTH);

        // Create JPanel to keep noteArea contained
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centerPanel.add(noteArea);

        // Add centerPanel to panel
        add(centerPanel, BorderLayout.CENTER);
        

    }

    // Updates the Panel's Labels 
    public void updateComponents(int schedule, String credits, String notes){
        scheduleLabel.setText("<html><u>Schedule " + schedule + "</u></html>");
        creditsLabel.setText("Credits: " + credits);
        noteArea.setText(notes);
        repaint();
    }
}
