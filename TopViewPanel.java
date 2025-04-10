import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TopViewPanel extends JPanel {
    private MainFrame mainFrame;
    private Schedule schedule;
    private JLabel scheduleTitle;
    private JTextArea noteArea;

    public TopViewPanel(MainFrame mainFrame, Schedule schedule){
        this.mainFrame = mainFrame;
        this.schedule = schedule;
        createComponents();
        addComponents();    
    }

    public void createComponents(){
        //create the schedule title label
        String title = getScheduleTitle();
        scheduleTitle = new JLabel();
        scheduleTitle.setPreferredSize(new Dimension(175, 50));
        
        //create the note area
        noteArea = new JTextArea(3, 15);
        noteArea.setLineWrap(true);
        noteArea.setWrapStyleWord(true);
        String notes = getScheduleNotes();
        noteArea.setText(notes);

    }

    public void addComponents(){
        add(scheduleTitle, BorderLayout.NORTH);
        add(noteArea);
    }


    public String getScheduleTitle(){
        return "";
    }

    public String getScheduleNotes(){
        return "";
    }
}
