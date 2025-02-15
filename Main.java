import javax.swing.JFrame;

public class Main{
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 800;

    public Main(){
        JFrame frame = new JFrame("Etown Schedule Creator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
    }
    public static void main(String[] args){
        //run the program
        new Main();
    }
}