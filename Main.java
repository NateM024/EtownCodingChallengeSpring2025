import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Main{
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 800;
    private JFrame frame;
    private InputPanel iPanel;


    public Main(){
        //intialize frame and set it up
        frame = new JFrame("Etown Schedule Creator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        //initialize and add input panel 
        iPanel = new InputPanel();
        frame.add(iPanel, BorderLayout.NORTH);

        //set frame to be visible
        frame.setVisible(true);
    }

    public static void main(String[] args){
        //run the program
        new Main();
    }
}

//questions:
//import individual packages or all of swing?
//how often to commit code?