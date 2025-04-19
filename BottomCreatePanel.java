import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class BottomCreatePanel extends JPanel implements ActionListener{
    private JLabel creditsLabel;
    private JButton viewButton;
    private JButton saveButton;
    private JButton refNumButton;
    private JButton clearButton;
    private int credits = 0; 
    private MainFrame mainFrame;
    private Schedule schedule;
    private TopCreatePanel topCreatePanel;

    // Constructor
    public BottomCreatePanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        initializeComponents();
        addComponents();
    }

    // Adds TopCreatePanel object
    public void addTopCreatePanel(TopCreatePanel topCreatePanel){
        this.topCreatePanel = topCreatePanel;
    }

    // Add Schedule object
    public void addSchedule(Schedule schedule){
        this.schedule = schedule;
    }

    // Initalizes all JComponents in Panel
    public void initializeComponents(){
        //label to show number of credits
        creditsLabel = new JLabel("Credits: " + credits);
        creditsLabel.setPreferredSize(new Dimension(80, 30));

        //button to view saved schedules
        viewButton = new JButton("View Schedules");
        viewButton.setPreferredSize(new Dimension(125, 25));
        viewButton.addActionListener(this);

        //button to save the current schedule
        saveButton = new JButton("Save Schedule");
        saveButton.setPreferredSize(new Dimension(125, 25));
        saveButton.addActionListener(this);

        //button to see course listings
        refNumButton = new JButton("View Courses");
        refNumButton.setPreferredSize(new Dimension(125, 25));
        refNumButton.addActionListener(this);

        //button to clear current schedule
        clearButton = new JButton("Clear Schedule");
        clearButton.addActionListener(this);

    }

    // Adds all JComponents to the Panel
    public void addComponents(){
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        add(creditsLabel);
        add(refNumButton);
        add(viewButton);
        add(saveButton);
        add(clearButton);
        setBorder(new EmptyBorder(10, 0, 10, 0)); 
    }

    
    // Method used whenever a button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == viewButton){
            //change schedule to past one
            credits = 0;
            schedule.createSavedSchedule(0);
            mainFrame.setViewMode(true);
        }
        else if(e.getSource() == saveButton){
           //not finnish
           if(credits > 0){
                saveSchedule();
           }
           else{
                topCreatePanel.updateErrorLabel("Cannot Save an Empty Schedule", true);
           }
        }
        else if(e.getSource() == refNumButton){
            try {
                if(Desktop.isDesktopSupported()){
                    Desktop desktop = Desktop.getDesktop();
                    desktop.browse(new URI("https://www.etown.edu/offices/registration-records/courselistings.aspx"));
                }
            } 
            catch (Exception ex) {
                System.out.println("Desktop not supported");
            }
        }
        else if(e.getSource() == clearButton){
            schedule.resetSchedule();
            credits = 0;
            creditsLabel.setText("Credits: " + credits);
        }
    }
    
    // Saves the current schedule to a txt file
    public void saveSchedule(){
        //create a JPanel to add to a JOptionPane
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());
        //create a JLabel
        JLabel label = new JLabel("Enter any notes here:");

        //create a JTextArea
        JTextArea textArea = new JTextArea(4,20); 
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setCaretPosition(0);
        
        //add the jlabel and jscrollpane to the jpanel
        pane.add(label, BorderLayout.NORTH);
        pane.add(textArea, BorderLayout.SOUTH);


        //create a JOptionPane and pass the JPanel containing the JTextArea
        int response = JOptionPane.showConfirmDialog(mainFrame, pane, "Saving the Schedule", JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE );
        //if the user clicks yes to save the schedule
        if (response == JOptionPane.YES_OPTION) {
            //create string including credits and notes
            String info = "Credits:" + credits + "Notes: " + textArea.getText();
            try (PrintWriter out = new PrintWriter(new FileWriter("SavedSchedules.txt", true))){
                    out.print("\n" + schedule.saveSchedule() + info);
                    out.close();
                    topCreatePanel.updateErrorLabel("Schedule Saved Successfully!", false);
                }
                catch (Exception ex) { 
                    ex.printStackTrace();
                    //System.out.println("Error saving schedule: " + ex); 
                } 
        }
    }

    //Updates the number of credits displayed
    public void updateCredits(int num){
        credits += num;
        creditsLabel.setText("Credits: " + credits);
    }
}
