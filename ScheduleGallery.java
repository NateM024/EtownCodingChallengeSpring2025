import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ScheduleGallery extends JPanel{
    private String scheduleData;

    public ScheduleGallery(){
        setBorder(new EmptyBorder(20, 20, 20, 20));
        createTables();
        setSize(600,660);
        setVisible(true);
    }

    public void createTables(){
        
    }
}
