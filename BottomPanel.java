import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class BottomPanel extends JPanel implements ActionListener{
    private JLabel creditsLabel;
    private JButton viewButton;
    private JButton saveButton;
    private JButton refNumButton;
    private int credits = 0; 
    private MainFrame mainFrame;
    private Schedule schedule;
    private InputPanel inputPanel;

    public BottomPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        createComponents();
        addComponents();
    }

    public void addInputPanel(InputPanel ip){
        inputPanel = ip;
    }

    public void createComponents(){
        //label to show number of credits
        creditsLabel = new JLabel("Credits: " + credits);
        creditsLabel.setPreferredSize(new Dimension(100, 30));

        //button to view saved schedules
        viewButton = new JButton("View Saved Schedules");
        viewButton.setPreferredSize(new Dimension(175, 25));
        viewButton.addActionListener(this);

        //button to save the current schedule
        saveButton = new JButton("Save Schedule");
        saveButton.setPreferredSize(new Dimension(175, 25));
        saveButton.addActionListener(this);

        //button to see course listings
        refNumButton = new JButton("View Courses");
        refNumButton.setPreferredSize(new Dimension(150, 25));
        refNumButton.addActionListener(this);

    }

    public void addComponents(){
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        add(creditsLabel);
        add(refNumButton);
        add(viewButton);
        add(saveButton);
        setBorder(new EmptyBorder(10, 0, 10, 0)); 
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == viewButton){
            //change schedule to past one
            credits = 0;
            schedule.createSavedSchedule(0);
            mainFrame.setViewMode(true);
        }
        else if(e.getSource() == saveButton){
            //prompt user with joptionpane to save the schedule
            // Create a JTextArea
            JTextArea textArea = new JTextArea(10, 30); // 10 rows and 30 columns
            textArea.setText("Enter any notes here: ");
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setCaretPosition(0); // Scroll to the top of the text area

            // Create a JScrollPane to make the JTextArea scrollable
            JScrollPane scrollPane = new JScrollPane(textArea);
            
            // Create a JOptionPane and pass the JScrollPane containing the JTextArea
            JOptionPane.showMessageDialog(null, scrollPane, "Saving the Schedule", JOptionPane.PLAIN_MESSAGE );
            // //create print writer
            // try (PrintWriter out = new PrintWriter(new FileWriter("C:/Users/nmars/My VS Code/EtownCodingChallengeSpring2025/SavedSchedules.txt", true))){
            //     out.println(schedule.saveSchedule());
            //     out.close();
            //     inputPanel.updateErrorLabel("Schedule Saved Successfully!");
            // }
            // catch (Exception ex) { 
            //     System.out.println("Error saving schedule: " + ex); 
            // } 
    
        }
        else if(e.getSource() == refNumButton){
            try {
                if(Desktop.isDesktopSupported()){
                    Desktop desktop = Desktop.getDesktop();
                    desktop.browse(new URI("https://www.etown.edu/offices/registration-records/courselistings.aspx"));
                }
            } 
            catch (Exception ex) {
                System.out.println("desktop not supported");
            }
        }
    }

    public void addSchedule(Schedule schedule){
        this.schedule = schedule;
    }

    public void updateCredits(int num){
        credits += num;
        creditsLabel.setText("Credits: " + credits);
    }
}
