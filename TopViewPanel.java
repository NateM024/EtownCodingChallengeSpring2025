import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class TopViewPanel extends JPanel {
    private MainFrame mainFrame;
    private Schedule schedule;
    private JLabel scheduleLabel;
    private JLabel creditsLabel;
    private JTextArea noteArea;

    // Constructor 
    public TopViewPanel(MainFrame mainFrame, Schedule schedule){
        this.mainFrame = mainFrame;
        this.schedule = schedule;
        initializeComponents();
        addComponents();    
    }

    // Initializes all JComponents
    public void initializeComponents(){
        //new fonts
        Font bigFont = new Font("Arial", Font.BOLD, 24);
        Font smallerFont = new Font("Arial", Font.BOLD, 14);

        //create the schedule title label
        int number = getScheduleNumber();
        scheduleLabel = new JLabel("<html><u>Schedule " + number + "</u></html>", SwingConstants.CENTER);
        scheduleLabel.setFont(bigFont);
        //scheduleLabel.setPreferredSize(new Dimension(175, 50));
        
        //create the credits label
        int credits = getScheduleCredits();
        creditsLabel = new JLabel("Credits: " + credits, SwingConstants.CENTER);
        creditsLabel.setFont(smallerFont);
        
        //create note area;
        noteArea = new JTextArea("Notesienwtuibewi nvib vwb rvbwjerbwqbrv qwr vwqri wer wqvr ewvi rqbbrjh evbhwq hbrjqweb vlewbr b", 2, 40);
        Dimension size = new Dimension(200, 35);
        noteArea.setPreferredSize(size);
        noteArea.setMaximumSize(size);
        noteArea.setEditable(false);
        noteArea.setWrapStyleWord(true);
        noteArea.setLineWrap(true);
        noteArea.setCaretPosition(0);
        noteArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    // Adds all JComponets to Panel
    public void addComponents(){
        setLayout(new BorderLayout(0,0));

        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.add(scheduleLabel);
        northPanel.add(creditsLabel);

        add(northPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centerPanel.add(noteArea);

        add(centerPanel, BorderLayout.CENTER);
        

    }

    // Returns the 
    public int getScheduleNumber(){
        return 1;
    }

    // Returns the
    public int getScheduleCredits(){
        return 1;
    }

    // Returns the 
    public String getScheduleNotes(){
        return "";
    }

    // Updates the Panel's Labels 
    public void updateComponents(int schedule, String credits, String notes){
        scheduleLabel.setText("<html><u>Schedule " + schedule + "</u></html>");
        creditsLabel.setText("Credits: " + credits);
        noteArea.setText(notes);
        repaint();
    }
}
