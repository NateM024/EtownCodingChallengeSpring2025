import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BottomViewPanel extends JPanel implements ActionListener{
    private MainFrame mainFrame;
    private Schedule schedule;
    private TopViewPanel topViewPanel;
    private JButton deleteButton;
    private JButton backButton;
    private JButton forwardButton;
    private JButton createScheduleButton;
    private JLabel scheduleNumLabel;
    private String space = "                                    ";
    private int scheduleListLength;
    private int currentSchedule;
    
    // Constructor
    public BottomViewPanel(MainFrame mainFrame, Schedule schedule){
        this.mainFrame = mainFrame;
        this.schedule = schedule;
        initializeComponents();
        addComponents();    
    }

    // Adds a TopViewPanel object
    public void addTopViewPanel(TopViewPanel topViewPanel){
        this.topViewPanel = topViewPanel;
    }

    // Initializes all JComponents
    public void initializeComponents(){
        //create delete button
        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(175, 25));
        deleteButton.addActionListener(this);

        //create backward button
        backButton = new JButton("Previous");
        backButton.setPreferredSize(new Dimension(175, 25));
        backButton.addActionListener(this);

        //create forward button
        forwardButton = new JButton("Next");
        forwardButton.setPreferredSize(new Dimension(175, 25));
        forwardButton.addActionListener(this);

        //create jlabel to show schedule numbers
        currentSchedule = 1;
        scheduleListLength = getNumberOfSchedules();
        scheduleNumLabel = new JLabel(space + "Schedule: " + currentSchedule + "/" + scheduleListLength);
        scheduleNumLabel.setPreferredSize(new Dimension(300, 15));  
        
        //create JButton to create a new schedule
        createScheduleButton = new JButton("Create Schedule");
        createScheduleButton.setPreferredSize(new Dimension(175, 25));
        createScheduleButton.addActionListener(this);
    }

    // Adds all JComponents to the Panel
    public void addComponents(){
        setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();

        //add the delete button to the left
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        add(deleteButton, g);

        //create a jpanel to hold the back and forward buttons together
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        middlePanel.add(backButton);
        middlePanel.add(forwardButton);

        //add middle panel
        g.gridx = 1;
        g.gridwidth = 2;
        add(middlePanel, g);

        //add schedule num label below back/forward buttons
        g.gridy = 1;
        g.gridwidth = 2;
        add(scheduleNumLabel, g);

        //add create schedule button to the right
        g.gridx = 3;
        g.gridy = 0;
        g.gridwidth = 1;
        add(createScheduleButton, g);
    }

    // Returns the total number of saved schedules in the txt file
    public int getNumberOfSchedules(){
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("SavedSchedules.txt"))){
            while(reader.readLine() != null){
                count++;
            }
        }
        catch(IOException e){
            System.out.println("Trouble retrieving saved schedules");
        } 
        return count;
    }

    // Updates the label displaying the current and total schedules
    public void updateScheduleNumLabel(){
        scheduleNumLabel.setText(space +  "Schedule: " + currentSchedule + "/" + scheduleListLength);
    }

    // Removes a schedule from the txt file
    public void removeSchedule(){
        ArrayList<String> linesList = new ArrayList<>();
        
        //read through txt file, add to allLines list
        try (BufferedReader reader = new BufferedReader(new FileReader("SavedSchedules.txt"))){
            String line;
            int lineCount = 0;

            //go through all lines
            while ((line = reader.readLine()) != null) {
                if (lineCount != currentSchedule) {
                    linesList.add(line); //add line to arraylist if not the line to be removed
                }
                lineCount++;
            }
        }   
        catch(IOException e){
            System.out.println("Trouble retrieving saved schedules");
        } 

        //write all lines in allLines list to txt file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("SavedSchedules.txt"))) {
            for (int i = 0; i < linesList.size(); i++) {
                writer.write(linesList.get(i));
                if (i < linesList.size() - 1) {
                    writer.newLine();  // Only add newline if it's not the last line
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    // Method used whenever a button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == deleteButton){ //if the user presses the delete button
            //prompt with joptionpane double-checking
            int response = JOptionPane.showConfirmDialog(schedule, "Are you sure you want to delete this schedule?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) { //if the user does want to delete the schedule
                //remove the line from the text file
                removeSchedule();
                //update scheduleListLength && possibly currentSchedule 
                if(currentSchedule == scheduleListLength)
                    currentSchedule--;
                scheduleListLength--;
                updateScheduleNumLabel();
            } 
        }
        else if(e.getSource() == backButton){ //if the user presses the back button
            //decrease the number of the current schedule and display the previous schedule
            currentSchedule--;
            if(currentSchedule == 0){
                currentSchedule = scheduleListLength;
            }
            schedule.createSavedSchedule((currentSchedule-1));
            updateScheduleNumLabel();
        }
        else if(e.getSource() == forwardButton){ //if the user presses the forward button
            //increase the number of the current schedule and display the next schedule
            currentSchedule++;
            if(currentSchedule > scheduleListLength){
                currentSchedule = 1;
            }
            schedule.createSavedSchedule((currentSchedule-1));
            updateScheduleNumLabel();
        }
        else if(e.getSource() == createScheduleButton){ //if the user presses the create schedule button
            //update mainframe to show empty schedule
            mainFrame.setViewMode(false);
            schedule.resetSchedule();
        }
    }
}
