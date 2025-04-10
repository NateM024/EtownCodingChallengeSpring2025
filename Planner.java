import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Planner{
    
    public static void main(String[] args) {
        // Create frame for the application with fixed size of 600px in height
        JFrame frame = new JFrame("Time Table with Selective Borders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 660);  // Set the frame size to 600x600
        frame.setLayout(new BorderLayout());

        // Define the column names (only Monday to Friday now)
        String[] columnNames = {"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        // Generate time slots in 10-minute increments for a day (from 8:00 AM to 5:50 PM)
        String[][] data = generateTimeSlots();

        // Create a DefaultTableModel to manage the table data
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Create the JTable with the model
        JTable table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);

                // Highlight rows that are full hours (e.g., 8:00 AM, 9:00 AM)
                // if (row % 6 == 0) {  // Assuming the first time slot is 8:00 AM, 6 rows represent 1 hour
                //     ((JComponent) comp).setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Show border for full hour rows
                // } else {
                    ((JComponent) comp).setBorder(BorderFactory.createEmptyBorder());  // No border for other rows
                //}

                return comp;
            }
        };

        // Calculate and set row height based on 600px height for 60 rows (one per time slot)
        int rowHeight = 660 / 60;  // 60 rows, so we divide the height by 60
        table.setRowHeight(rowHeight); // Set the height of each row
        table.setFillsViewportHeight(true);

        // Make sure the table headers are visible
        table.getTableHeader().setReorderingAllowed(false); // Disable column reordering
        table.getTableHeader().setResizingAllowed(true);    // Allow column resizing
        table.setTableHeader(table.getTableHeader());        // Make sure header is set

        // Add the table directly to the frame (no JScrollPane)
        frame.add(table.getTableHeader(), BorderLayout.NORTH); // Add headers explicitly
        frame.add(table, BorderLayout.CENTER); // Add the table data

        // Display the frame
        frame.setVisible(true);
    }

    private static String[][] generateTimeSlots() {
        // Generate time slots for the day (from 8:00 AM to 5:50 PM)
        String[][] timeSlots = new String[60][6]; // 60 slots (10-minute increments), 6 columns (days + time column)

        // String[] hours = {"8:00 AM", "8:10 AM", "8:20 AM", "8:30 AM", "8:40 AM", "8:50 AM", "9:00 AM", "9:10 AM", "9:20 AM", "9:30 AM", 
        //                   "9:40 AM", "9:50 AM", "10:00 AM", "10:10 AM", "10:20 AM", "10:30 AM", "10:40 AM", "10:50 AM", 
        //                   "11:00 AM", "11:10 AM", "11:20 AM", "11:30 AM", "11:40 AM", "11:50 AM", "12:00 PM", "12:10 PM", 
        //                   "12:20 PM", "12:30 PM", "12:40 PM", "12:50 PM", "1:00 PM", "1:10 PM", "1:20 PM", "1:30 PM", 
        //                   "1:40 PM", "1:50 PM", "2:00 PM", "2:10 PM", "2:20 PM", "2:30 PM", "2:40 PM", "2:50 PM", 
        //                   "3:00 PM", "3:10 PM", "3:20 PM", "3:30 PM", "3:40 PM", "3:50 PM", "4:00 PM", "4:10 PM", 
        //                   "4:20 PM", "4:30 PM", "4:40 PM", "4:50 PM", "5:00 PM"};
        String[] hours = {"8:00 AM", "", "", "", "", "",
                          "9:00 AM", "", "", "", "", "", 
                          "10:00 AM", "", "", "", "", "", 
                          "11:00 AM", "", "", "", "", "", 
                          "12:00 PM", "", "", "", "", "", 
                          "1:00 PM", "", "", "", "", "", 
                          "2:00 PM", "", "", "", "", "", 
                          "3:00 PM", "", "", "", "", "", 
                          "4:00 PM", "", "", "", "", "", 
                          "5:00 PM"};

        // Populate the time slots array with the times
        for (int i = 0; i < hours.length; i++) {
            timeSlots[i][0] = hours[i]; // First column is the time

            // Set the days columns (empty for now)
            for (int j = 1; j <= 5; j++) {  // Only Monday to Friday (5 days)
                timeSlots[i][j] = ""; // Empty cells for the events/tasks
            }
        }

        return timeSlots;
    }
}
