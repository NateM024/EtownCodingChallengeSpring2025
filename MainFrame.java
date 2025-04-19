import java.awt.BorderLayout;
import javax.swing.JFrame;


public class MainFrame extends JFrame{
    private final int FRAME_WIDTH = 750;
    private final int FRAME_HEIGHT = 885;
    private Schedule schedule;
    private TopCreatePanel topCreatePanel;
    private TopViewPanel topViewPanel;
    private BottomCreatePanel bottomCreatePanel;
    private BottomViewPanel bottomViewPanel;

    public MainFrame(){
        //set up JFrame
        setTitle("Etown Schedule Creator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        //create schedule and input panel and bottom panel
        schedule = new Schedule(); 
        topCreatePanel = new TopCreatePanel();
        bottomCreatePanel = new BottomCreatePanel(this);

        //create topViewPanel and bottomViewPanel but don't add them
        topViewPanel = new TopViewPanel();
        bottomViewPanel = new BottomViewPanel(this, schedule);

        //connect things
        schedule.addTopCreatePanel(topCreatePanel);
        schedule.addBottomCreatePanel(bottomCreatePanel);
        schedule.addTopViewPanel(topViewPanel);
        topCreatePanel.addSchedule(schedule);
        bottomCreatePanel.addSchedule(schedule);
        bottomCreatePanel.addTopCreatePanel(topCreatePanel);
        bottomViewPanel.addTopViewPanel(topViewPanel);

        //add schedule and input panel to frame
        add(topCreatePanel, BorderLayout.NORTH);
        add(schedule);
        add(bottomCreatePanel, BorderLayout.SOUTH);

        //set JFrame to be visible & unresizable
        setResizable(false);
        setVisible(true);
    }

    // Switchs between two modes: one to view schedules and one to create them
    public void setViewMode(boolean view){
        if(!view){
            remove(bottomViewPanel);
            remove(topViewPanel);
            add(topCreatePanel, BorderLayout.NORTH);
            add(bottomCreatePanel, BorderLayout.SOUTH);
            revalidate();
            repaint();
        }
        else{            
            remove(bottomCreatePanel);
            remove(topCreatePanel);
            add(topViewPanel, BorderLayout.NORTH);
            add(bottomViewPanel, BorderLayout.SOUTH);
            revalidate();
            repaint();
        }
    }

    public static void main(String[] args){
        //run the program
        new MainFrame();
    }
}
