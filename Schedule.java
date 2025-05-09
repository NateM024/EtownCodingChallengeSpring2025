import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Schedule extends JPanel{
    private TopCreatePanel topCreatePanel;
    private BottomCreatePanel bottomCreatePanel;
    private TopViewPanel topViewPanel;
    private ArrayList<DefaultTableModel> dayModels = new ArrayList<>();
    private ArrayList<JTable> dayTables = new ArrayList<>();
    private DefaultTableModel model1;
    private DefaultTableModel model2;
    private DefaultTableModel model3;
    private DefaultTableModel model4;
    private DefaultTableModel model5;
    private DefaultTableModel model6;
    private JTable table1; 
    private JTable table2;
    private JTable table3; 
    private JTable table4;
    private JTable table5; 
    private JTable table6;
    private int[] firstAndLastRows = new int[2]; 
    private ArrayList<Color> colorList = new ArrayList<>();
    private ArrayList<String> dayList = new ArrayList<>();
 
    // Constructor
    public Schedule(){
        setBorder(new EmptyBorder(20, 20, 15, 20));
        createTimeTable();
        fillColorList();
        fillDayList();
        createDayTables();
        addTables();
        setSize(600,660);
        setVisible(true);
    }

    // Adds a TopCreatePanel object
    public void addTopCreatePanel(TopCreatePanel topCreatePanel){
        this.topCreatePanel = topCreatePanel;
    }

    // Adds a bottomCreatePanel object
    public void addBottomCreatePanel(BottomCreatePanel bottomCreatePanel){
        this.bottomCreatePanel = bottomCreatePanel;
    }
    
    public void addTopViewPanel(TopViewPanel topViewPanel){
        this.topViewPanel = topViewPanel;
    }

    // Fills the colorList ArrayList with colors
    public void fillColorList(){
        colorList.add(new Color(255, 127, 127));
        colorList.add(new Color(173, 216, 230));
        colorList.add(new Color(144, 238, 144));
        colorList.add(new Color(255, 255, 224));
        colorList.add(new Color(255, 182, 193));
    
    }

    // Fills the dayList ArrayList with weekdays
    public void fillDayList(){
        dayList.add("Monday");
        dayList.add("Tuesday");
        dayList.add("Wednesday");
        dayList.add("Thursday");
        dayList.add("Friday");
        dayList.add("Saturday");
    }

    // Creates the JTable that contains times 
    public void createTimeTable(){
          // Column names
          String[] columnNames = {"Time"};

          // Data for the table - 54 rows
          Object[][] data = new Object[55][1];
  
          // Populate the data with time in the required pattern (12-hour format)
          int hour = 7;
          String period = "AM"; 
          for (int i = 0; i < 55; i++) {
              if (i % 6 == 0) {
                  // Increment the hour
                  hour++;
                  // Handle hour reset for 12-hour format
                  if (hour > 12) {
                      hour = 1; 
                  }
                  // Every 6th row gets a time, starting from "8:00 AM"
                  data[i][0] = hour + ":00 " + period;
  
                  // After 12:00 PM, switch to PM
                  if (hour == 11) {
                      period = "PM"; 
                  }
  
              } 
              else {
                  // The other rows are empty
                  data[i][0] = (hour) + ":" + (i%6) + "0";
              }
          }
  
          // Create table for the left table with DefaultTableModel
          model1 = new DefaultTableModel(data, columnNames);
          table1 = new JTable(model1);
    }

    // Creates the JTables representing weekdays
    public void createDayTables(){

        //Column names
        String[] columnNames = {""}; 
        // Create separate table models for the right tables
        Object[][] emptyData = new Object[55][1]; // Empty data for the second table
        model2 = new DefaultTableModel(emptyData, columnNames);
        table2 = new JTable(model2);  
        dayModels.add(model2);
        dayTables.add(table2);

        model3 = new DefaultTableModel(emptyData, columnNames);
        table3 = new JTable(model3);
        dayModels.add(model3);
        dayTables.add(table3);

        model4 = new DefaultTableModel(emptyData, columnNames);
        table4 = new JTable(model4);
        dayModels.add(model4);
        dayTables.add(table4);

        model5 = new DefaultTableModel(emptyData, columnNames);
        table5 = new JTable(model5);
        dayModels.add(model5);
        dayTables.add(table5);

        model6 = new DefaultTableModel(emptyData, columnNames);
        table6 = new JTable(model6);
        dayModels.add(model6);
        dayTables.add(table6);

        // Set the row height for all rows to a fixed value (e.g., 12 pixels)
        int rowHeight = 12;
        
        table1.setRowHeight(rowHeight);
        for(JTable table : dayTables){
            table.setRowHeight(rowHeight);
        }
        // table2.setRowHeight(rowHeight);
        // table3.setRowHeight(rowHeight);
        // table4.setRowHeight(rowHeight);
        // table5.setRowHeight(rowHeight);
        // table6.setRowHeight(rowHeight);

        // Set the column width to 120 pixels for all tables
        table1.getColumnModel().getColumn(0).setPreferredWidth(30);
        for(JTable table : dayTables){
            table.getColumnModel().getColumn(0).setPreferredWidth(100);
        }
        // table2.getColumnModel().getColumn(0).setPreferredWidth(100);
        // table3.getColumnModel().getColumn(0).setPreferredWidth(100);
        // table4.getColumnModel().getColumn(0).setPreferredWidth(100);
        // table5.getColumnModel().getColumn(0).setPreferredWidth(100);
        // table6.getColumnModel().getColumn(0).setPreferredWidth(100);

        // Set the column headers of the second to sixth tables to the respective days
        table2.getColumnModel().getColumn(0).setHeaderValue("Monday");
        table3.getColumnModel().getColumn(0).setHeaderValue("Tuesday");
        table4.getColumnModel().getColumn(0).setHeaderValue("Wednesday");
        table5.getColumnModel().getColumn(0).setHeaderValue("Thursday");
        table6.getColumnModel().getColumn(0).setHeaderValue("Friday");

        //Set the columns to be able to have dynamic text features
        // table2.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());
        // table3.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());
        // table4.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());
        // table5.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());
        // table6.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());
        for(JTable table : dayTables){
            table.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());
        }
    }

    // Adds all JTables to the Panel
    public void addTables(){
        // Set up the layout using GridBagLayout
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        // Add the first table (time one) inside a JScrollPane
        JScrollPane scrollPane1 = new JScrollPane(table1);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scrollbar
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scrollbar
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(scrollPane1, constraints);

        // Add the second table (Monday) inside a JScrollPane
        JScrollPane scrollPane2 = new JScrollPane(table2);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scrollbar
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scrollbar
        constraints.gridx = 1;
        add(scrollPane2, constraints);

        // Add the third table (Tuesday) inside a JScrollPane
        JScrollPane scrollPane3 = new JScrollPane(table3);
        scrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scrollbar
        scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scrollbar
        constraints.gridx = 2;
        add(scrollPane3, constraints);

        // Add the fourth table (Wednesday) inside a JScrollPane
        JScrollPane scrollPane4 = new JScrollPane(table4);
        scrollPane4.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scrollbar
        scrollPane4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scrollbar
        constraints.gridx = 3;
        add(scrollPane4, constraints);

        // Add the fifth table (Thursday) inside a JScrollPane
        JScrollPane scrollPane5 = new JScrollPane(table5);
        scrollPane5.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scrollbar
        scrollPane5.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scrollbar
        constraints.gridx = 4;
        add(scrollPane5, constraints);

        // Add the sixth table (Friday) inside a JScrollPane
        JScrollPane scrollPane6 = new JScrollPane(table6);
        scrollPane6.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scrollbar
        scrollPane6.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scrollbar
        constraints.gridx = 5;
        add(scrollPane6, constraints);
    }

    // Clears all JTables 
    public void resetTables(){
        //clear everything from tables 2-6
        for(DefaultTableModel model : dayModels){
            model.setRowCount(0); 
            model.setColumnCount(0); 
            model.setRowCount(55);
            model.setColumnCount(1);
        }
      
        for(JTable table : dayTables){
            table.getColumnModel().getColumn(0).setPreferredWidth(100);
        }

        // Set the column headers of the second to sixth tables to the respective days
        table2.getColumnModel().getColumn(0).setHeaderValue("Monday");
        table3.getColumnModel().getColumn(0).setHeaderValue("Tuesday");
        table4.getColumnModel().getColumn(0).setHeaderValue("Wednesday");
        table5.getColumnModel().getColumn(0).setHeaderValue("Thursday");
        table6.getColumnModel().getColumn(0).setHeaderValue("Friday");

         //Set the columns to be able to have dynamic text features
        for(JTable table : dayTables){
            table.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());
    
        }
    }
    
    // Helps display a saved schedule
    public void createSavedSchedule(int scheduleNum){
        try (BufferedReader reader = new BufferedReader(new FileReader("SavedSchedules.txt"))){
            int lines = 0;

            //find the right line
            while(lines < scheduleNum){
                //need to check if count > scheduleNum?
                reader.readLine();
                lines++;
            }
            resetTables();
            String data = reader.readLine();

            data = data.substring(7);
            for(int i = 0; i < 5; i++){
                data = addSavedScheduleData(dayModels.get(i), dayTables.get(i), data, dayList.get(i+1), i);
            }

            //Get credits and notes from data string
            String credits = data.substring(data.indexOf("Credits:")+8, data.indexOf("Notes:"));
            String notes = data.substring(data.indexOf("Notes:"));

            //Then send to topviewpanel along with scheduleNum
            topViewPanel.updateComponents(scheduleNum+1, credits, notes);
            
        }   
        catch(IOException e){
            System.out.println("Trouble retrieving saved schedules");
        } 
    }
    
    // Search through saved data to display a new schedule
    public String addSavedScheduleData(DefaultTableModel model, JTable table, String data, String day, int end){
        while(data.indexOf("|") < data.indexOf(day) && data.indexOf("|") != -1){
            int rowNum = Integer.parseInt(data.substring(0, data.indexOf("|")));
            data = data.substring(data.indexOf("|")+1);
            
            String className = data.substring(0, data.indexOf("|"));
            data = data.substring(data.indexOf("|")+1);
            
            int rowHeight = Integer.parseInt(data.substring(0, data.indexOf("|")));
            data = data.substring(data.indexOf("|")+1);

            //find color
            int r = Integer.parseInt(data.substring(data.indexOf("=")+1, data.indexOf(",")));
            data = data.substring(data.indexOf(",")+1);
            int g = Integer.parseInt(data.substring(data.indexOf("=")+1, data.indexOf(",")));
            data = data.substring(data.indexOf(",")+1);
            int b = Integer.parseInt(data.substring(data.indexOf("=")+1, data.indexOf("]")));
            data = data.substring(data.indexOf("|")+1);

            firstAndLastRows[0] = rowNum;
            firstAndLastRows[1] = rowNum+(rowHeight/12);
            addToTables(table, model, className, new Color(r,g,b));
        }    
        return data;
    }

    // Helps add a new class to the schedule
    public void createNewClass(String name, String days, String time, int credits){
        // Determine what days the class is on
        if(days.equals("By Arrangement")){
            topCreatePanel.updateErrorLabel("This class meets by arrangement, so it cannot be added.", true);
        }
        else if(days.equals("Online")){
            topCreatePanel.updateErrorLabel("This class meets online, so it cannot be added.", true);
        }
        else if(days.equals("Field")){
            topCreatePanel.updateErrorLabel("This class is a field placement which does not meet on predetermined days, so it cannot be added.", true);
        }
        else{
            if(checkAvailability(days, time)){
                for(int i = 0; i < days.length(); i++){
                    if(days.substring(i, i+1).equals("M")){
                        addToTables(table2, model2, name, null);
                    }
                    else if(days.substring(i, i+1).equals("T")){
                        addToTables(table3, model3, name, null);
                    }
                    else if(days.substring(i, i+1).equals("W")){
                        addToTables(table4, model4, name, null);
                    }
                    else if(days.substring(i, i+1).equals("H")){
                        addToTables(table5, model5, name, null);
                    }
                    else if(days.substring(i, i+1).equals("F")){
                        addToTables(table6, model6, name, null);
                    }
                }

                //update colors
                Color tempColor = colorList.remove(0);
                colorList.add(tempColor);

                //update credits displayed
                bottomCreatePanel.updateCredits(credits);

                //clear error label from any previous text
                topCreatePanel.updateErrorLabel("", true); 
                
                //reset array
                firstAndLastRows = new int[2];
            }
            
        }
    }

    // Checks if a class can be added in all days it runs
    public boolean checkAvailability(String days, String time){
        for(int i = 0; i < days.length(); i++){
            if(days.substring(i, i+1).equals("M")){
                if(!canAddClass(table2, time)){
                    return false;
                }
            }
            else if(days.substring(i, i+1).equals("T")){
                if(!canAddClass(table3, time)){
                    return false;
                }
            }
            else if(days.substring(i, i+1).equals("W")){
                if(!canAddClass(table4, time)){
                    return false;
                }
            }
            else if(days.substring(i, i+1).equals("H")){
                if(!canAddClass(table5, time)){
                    return false;
                }
            }
            else if(days.substring(i, i+1).equals("F")){
                if(!canAddClass(table6, time)){
                    return false;
                }
            }
        }
        return true;
    }

    // Checks if there is room to add a class in a specific day and time
    public boolean canAddClass(JTable table, String time){
        //Need to know how many rows are in the table
        int rows = table.getRowCount();

        //First hour
        int firstHour = 0;
        try {
            firstHour = Integer.parseInt(time.substring(0,2));
 
        } catch (NumberFormatException e) {
            topCreatePanel.updateErrorLabel("Error in receiving class time. Choose a new class", true);
            return false;
        }
         
        if(firstHour < 8){
            firstHour += 12;
        }
        firstHour = ((firstHour - 8) * 6 + rows)%rows;
        //Turn time into row number
        int firstRow = firstHour + Integer.parseInt(time.substring(3,4));
 
        //Last hour
        int lastHour = 0;
        try{
            lastHour = Integer.parseInt(time.substring(8,10));
            if(lastHour > 4 && lastHour <= 8){ //check if the class ends at time not shown on schedule
                topCreatePanel.updateErrorLabel("The schedule is unable to display this class", true);
                return false;
            }
        }catch(NumberFormatException e){
            topCreatePanel.updateErrorLabel("Error in receiving class time. Choose a new class", true);
            return false;
        }
 
        if(lastHour < 8){
            lastHour += 12;
        }
        lastHour = ((lastHour - 8) * 6 + rows)%rows;

        //Turn time into row number
        int lastRow = lastHour + Integer.parseInt(time.substring(11,12));
        if(lastRow >= rows){
            topCreatePanel.updateErrorLabel("Class cannot fully fit in the schedule", true);
        }
        
        for(int j = 0; j < table.getRowCount(); j++){
            if(table.getRowHeight(j) > 12){
                if(firstRow == j){ //if the class is the same time as one already on the schedule
                    topCreatePanel.updateErrorLabel("There is already a class during this time", true);
                    return false;
                }
                else if(firstRow <= j + table.getRowHeight(j)/12 && j + table.getRowHeight(j)/12 <= lastRow){
                    topCreatePanel.updateErrorLabel("There is already a class during this time", true);
                    return false;
                }
                else if(firstRow > j){
                    firstRow -= table.getRowHeight(j) / 12 - 1;
                    lastRow -= table.getRowHeight(j) / 12 - 1;
                }
                else if(lastRow < j){

                }
            }
        }
        firstAndLastRows[0] = firstRow;
        firstAndLastRows[1] = lastRow;
        return true;
    }

    // Adds a new class to the JTables
    public void addToTables(JTable table, DefaultTableModel model, String name, Color color){
        //remove rows taken up by new class

        for(int j = firstAndLastRows[1]-1; j > firstAndLastRows[0]; j--){
            model.removeRow(j);
        }
        //in case the last row happens to be negative
        if(firstAndLastRows[1] <= 0){
            firstAndLastRows[1] = firstAndLastRows[1] + model.getRowCount();
        }
        
        //send correct color to the cell
        if(color == null){
            ((WordWrapCellRenderer) table.getCellRenderer(firstAndLastRows[0], 0)).setColor(colorList.get(0), firstAndLastRows[0]);
        }
        else{
            ((WordWrapCellRenderer) table.getCellRenderer(firstAndLastRows[0], 0)).setColor(color, firstAndLastRows[0]);
        }
        //helps ensures colors stay in the right cells
        ((WordWrapCellRenderer) table.getCellRenderer(firstAndLastRows[0], 0)).resetCount();

        //send class name to the cell
        table.setValueAt(name, firstAndLastRows[0], 0);
        //change the row height
        table.setRowHeight(firstAndLastRows[0], table1.getRowHeight(firstAndLastRows[0]) * (firstAndLastRows[1] - firstAndLastRows[0]));
    }

    // Turns a schedule's data into a String to be saved 
    public String saveSchedule(){
        String saveString = "";
        saveString += "Monday";
        int classes = 0;

        for(int i = 0; i < model2.getRowCount(); i++){
            if(table2.getValueAt(i, 0) != null){
                //saves row #, class name, row height, and color
                String color =  ((WordWrapCellRenderer) table2.getCellRenderer(i, 0)).getColor(classes);
                saveString += "|" + i + "|" + table2.getValueAt(i, 0) + "|" + table2.getRowHeight(i) + "|" + color;
                classes++;
            }
        }
        classes = 0;
        saveString += "Tuesday";
        for(int i = 0; i < model3.getRowCount(); i++){
            if(table3.getValueAt(i, 0) != null){
                //saves row #, class name, row height, and color
                String color =  ((WordWrapCellRenderer) table3.getCellRenderer(i, 0)).getColor(classes);
                saveString += "|" + i + "|" + table3.getValueAt(i, 0) + "|" + table3.getRowHeight(i) + "|" + color;
                classes++;
            }
        }
        classes = 0;
        saveString += "Wednesday";
        for(int i = 0; i < model4.getRowCount(); i++){
            if(table4.getValueAt(i, 0) != null){
                //saves row #, class name, row height, and color
                String color =  ((WordWrapCellRenderer) table4.getCellRenderer(i, 0)).getColor(classes);
                saveString += "|" + i + "|" + table4.getValueAt(i, 0) + "|" + table4.getRowHeight(i) + "|" + color;
                classes++;
            }
        }
        classes = 0;
        saveString += "Thursday";
        for(int i = 0; i < model5.getRowCount(); i++){
            if(table5.getValueAt(i, 0) != null){
                //saves row #, class name, row height, and color
                String color =  ((WordWrapCellRenderer) table5.getCellRenderer(i, 0)).getColor(classes);
                saveString += "|" + i + "|" + table5.getValueAt(i, 0) + "|" + table5.getRowHeight(i) + "|" + color;
                classes++;
            }
        }
        classes = 0;
        saveString += "Friday";
        for(int i = 0; i < model6.getRowCount(); i++){
            if(table6.getValueAt(i, 0) != null){
                //saves row #, class name, row height, and color
                String color =  ((WordWrapCellRenderer) table6.getCellRenderer(i, 0)).getColor(classes);
                saveString += "|" + i + "|" + table6.getValueAt(i, 0) + "|" + table6.getRowHeight(i) + "|" + color;
                classes++;
            }
        }

        saveString += "Saturday";

        return saveString;
    }

    // Inner class to dynamically display a course in a JTable
    public class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
        private Color defaultColor = getBackground();
        private ArrayList<Color> colors = new ArrayList<>();
        private ArrayList<Integer> occupiedRows = new ArrayList<>();
        private int count = 0;
        private int classes = 0;

        // Constructor
        public WordWrapCellRenderer() {
            setWrapStyleWord(true);
            setLineWrap(true);
            setOpaque(true);
            setBorder(new EmptyBorder(2, 5, 2, 5));
        }

        // Resets the count variable
        public void resetCount(){
            count = 0;
        } 

        // Adds a new color to the JTable
        public void setColor(Color c, int row){
            if(occupiedRows.isEmpty()){
                occupiedRows.add(row);
                colors.add(c);
                return;
            }
            for(int i = 0; i < occupiedRows.size(); i++){
                if(occupiedRows.get(i) > row){
                    occupiedRows.add(i, row);
                    colors.add(i, c);
                    return;
                }
            }
            occupiedRows.add(row);
            colors.add(c);

        }

        // Returns a specific color
        public String getColor(int index){
            return colors.get(index).toString();
        }

        // Helps to display accurate text and color
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            // if(isSelected){
            //     try {
            //         return table;
            //     } catch (Exception e) {

            //     }
            // }
            
            //set the text value
            setText(value != null ? value.toString() : ""); 

            //change background color of the cell
            if(value != null){
                if(count < colors.size())
                    setBackground(colors.get(count));
                count++;
            }
            else{
                setBackground(defaultColor);
            }

            return this;
        }
    }

}
