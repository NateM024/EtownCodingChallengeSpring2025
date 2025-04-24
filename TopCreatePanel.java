import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TopCreatePanel extends JPanel implements ActionListener{
    private Schedule schedule;
    private JLabel refNumLabel;
    private JTextField refNumField;
    private JLabel errorLabel;
    private JButton enterClass;
    private String classTime = "";
    private String classDays = "";
    private String className = "";
    private int classCredits = 0;
    private int totalCredits = 0;

    public TopCreatePanel(){
        //set panel features 
        setLayout(new GridBagLayout());
        setBackground(new Color(235, 235, 235));
        initializeComponents();
        addComponents();
    }
    
    public void addSchedule(Schedule schedule){
        this.schedule = schedule;
    }

    public void initializeComponents(){
        //initialize components and set their size
        
        //refnumlabel
        refNumLabel = new JLabel("Enter the Class Reference Number:", SwingConstants.CENTER);
        refNumLabel.setPreferredSize(new Dimension(220, 20));

        //refNumField
        refNumField = new JTextField("12345");
        refNumField.setPreferredSize(new Dimension(100, 20));

        //errorlabel
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setPreferredSize(new Dimension(400, 20));
        errorLabel.setForeground(Color.RED);

        //enterClass button
        enterClass = new JButton("Enter Class");
        enterClass.setPreferredSize(new Dimension(100, 20));
        enterClass.addActionListener(this);
    }

    public void addComponents(){
        //add components
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(0, 20, 0, 0);

        g.gridx = 0;
        g.gridy = 0;

        add(refNumLabel, g);

        g.gridy = 1;
        add(refNumField, g);

        g.gridy = 2;
        add(errorLabel, g);

        g.gridy = 3;
        add(enterClass, g);
    }

    public void updateErrorLabel(String message, boolean red){
        if(red)
            errorLabel.setForeground(Color.RED);
        else
            errorLabel.setForeground(Color.GREEN);
        errorLabel.setText(message);

    }

    public void actionPerformed(ActionEvent e) {
        //when button is clicked retrieve info from both textfields
        if(e.getSource() == enterClass){
            try {
                findClass(refNumField.getText());
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }   
    }

    public void findClass(String refNum) throws IOException{
        //create string to look for
        String code = "<td>" + refNum + "</td>";

        try (BufferedReader reader = new BufferedReader(new FileReader("FilteredClasses.txt"))) {
            String line;
            //go through each line
            while ((line = reader.readLine()) != null) {
                //find string we are looking for
                if(line.indexOf(code) > -1){
                    getClassInfo(line);
                    if(totalCredits + classCredits > 20){ // check if the total credits would exceed 20
                        updateErrorLabel("You cannot register for more than 20 credits", true);
                    }
                    else{ //else add the class
                        totalCredits += classCredits;
                        schedule.createNewClass(className, classDays, classTime, classCredits);
                    }
                    className = "";
                    classDays = "";
                    classTime = "";
                    classCredits = 0;
                    return;
                }
            }
            //if the class was not found
            updateErrorLabel("Invalid Reference Number", true);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void getClassInfo(String line){
        int tags = 0;
        for(int i = 0; i < line.length(); i++){
            if(line.substring(i,i+1).equals(">")){
                tags++;
                //first, get the class name
                if(tags == 7){
                    int j = i + 1;
                    while(j+1 < line.length()){
                        if(line.substring(j,j+1).equals("<")){
                            i = j;
                            j = line.length();
                        }
                        else{
                            className += line.substring(j,j+1);
                            j++;
                        }
                    }
                } 
                //get the class days
                else if(tags == 17){
                    int spaces = 0;
                    int j = i;
                    //find days by looking for 4 spaces
                    while(j+1 < line.length() && spaces < 4){
                        if(line.substring(j,j+1).equals(" ")){
                            spaces++;
                        }
                        j++;
                    }
                    String temp = line.substring(j, line.length());
                    String daysLine = line.substring(j, (j+temp.indexOf(" ")));

                    //by arrangement, online, field, or m, t, w, h, f 
                    if(daysLine.contains("By")){
                        classDays = "By Arrangement";
                        classTime = "None";
                    }
                    else if(daysLine.contains("ONLN")){
                        classDays = "Online";
                        classTime = "None";
                    }
                    else if(daysLine.contains("FIELD")){
                        classDays = "Field";
                        classTime = "None";
                    }
                    else{
                        classDays = daysLine;
                        //get class time
                        int start = j + daysLine.length()+2;
                        classTime = line.substring(start, start + 15).trim();
                    }
                }
                else if(tags == 19){
                    try {
                        classCredits = Integer.parseInt(line.substring(i+1,i+2));
                    } catch (Exception e) {
                        System.out.println("Error in Retrieving class credits");
                    }
                }
            }
        }

        //check and remove "&amp;" from className, test with refnum: 48959
        if(className.contains("&amp;")){
            className = className.substring(0, className.indexOf("&amp;")) + className.substring(className.indexOf("&amp;")+6);
        }
    }
}
