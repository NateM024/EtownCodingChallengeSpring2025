import java.awt.BorderLayout;
import javax.swing.JFrame;


public class MainFrame extends JFrame{
    private final int FRAME_WIDTH = 750;
    private final int FRAME_HEIGHT = 885;
    private Schedule schedule;
    private InputPanel inputPanel;
    private TopViewPanel topViewPanel;
    private BottomPanel bottomPanel;
    private BottomViewPanel bottomViewPanel;

    public MainFrame(){
        //set up JFrame
        setTitle("Etown Schedule Creator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        //create schedule and input panel and bottom panel
        schedule = new Schedule(); 
        inputPanel = new InputPanel();
        bottomPanel = new BottomPanel(this);

        //create topViewPanel and bottomViewPanel but don't add them
        topViewPanel = new TopViewPanel(this, schedule);
        bottomViewPanel = new BottomViewPanel(this, schedule);

        //connect things
        schedule.addInputPanel(inputPanel);
        schedule.addBottomPanel(bottomPanel);
        schedule.addTopViewPanel(topViewPanel);
        inputPanel.addSchedule(schedule);
        bottomPanel.addSchedule(schedule);
        bottomPanel.addInputPanel(inputPanel);
        bottomViewPanel.addTopViewPanel(topViewPanel);

        //add schedule and input panel to frame
        add(inputPanel, BorderLayout.NORTH);
        add(schedule);
        add(bottomPanel, BorderLayout.SOUTH);

        //set JFrame to be visible & unresizable
        setResizable(false);
        setVisible(true);
    }

    // Switchs between two modes: one to view schedules and one to create them
    public void setViewMode(boolean view){
        if(!view){
            remove(bottomViewPanel);
            remove(topViewPanel);
            add(inputPanel, BorderLayout.NORTH);
            add(bottomPanel, BorderLayout.SOUTH);
            revalidate();
            repaint();
        }
        else{            
            remove(bottomPanel);
            remove(inputPanel);
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
/**
 * To do:
 *      -viewing saved schedules
 *          -look through saved ones - done
 *          -replace bottompanel with buttons - done
 *          -remove saved one - tuesday evening - done
 * 
 *          -replace top panel: - thursday afternoon
 *              -schedule name, 
 *              -credits,
 *              -user notes 
 *      -improve code - friday 
 *          -delete commented out code
 *          -add explanatory comments
 *          -implement for loops
 *          -rename classes
 *          -change create to initialize
 *      -update class files
 *      
 */