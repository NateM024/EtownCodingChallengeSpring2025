import java.awt.BorderLayout;
import javax.swing.JFrame;


public class MainFrame{
    private final int FRAME_WIDTH = 750;
    private final int FRAME_HEIGHT = 885;
    private JFrame frame;
    private Schedule schedule;
    private BottomPanel bottomPanel;
    private BottomViewPanel bottomViewPanel;

    public MainFrame(){
        //intialize frame and set it up
        frame = new JFrame("Etown Schedule Creator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        //create schedule and input panel and bottom panel
        schedule = new Schedule(); 
        InputPanel inputPanel = new InputPanel();
        bottomPanel = new BottomPanel(this);

        //create viewPanel but don't add it
        bottomViewPanel = new BottomViewPanel(this, schedule);

        //connect things
        schedule.addInputPanel(inputPanel);
        schedule.addBottomPanel(bottomPanel);
        inputPanel.addSchedule(schedule);
        bottomPanel.addSchedule(schedule);

        //add schedule and input panel to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(schedule);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        //set frame to be visible & unresizable
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void setViewMode(boolean view){
        if(!view){
            frame.remove(bottomViewPanel);
            frame.add(bottomPanel, BorderLayout.SOUTH);
            frame.repaint();
        }
        else{            
            frame.remove(bottomPanel);
            frame.add(bottomViewPanel, BorderLayout.SOUTH);
            frame.revalidate();
            frame.repaint();
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
 *          -remove saved ones 
 *          -replace top panel: 
 *              -schedule name, 
 *              -credits,
 *              -user notes
 *      -improve code
 *          -delete commented out code
 *          -add explanatory comments
 *          -implement for loops
 *          -rename classes
 *      -update class files
 *      -export to excel
 *      -rate my professor
 *      
 */