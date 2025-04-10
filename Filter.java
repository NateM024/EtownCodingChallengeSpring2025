import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Filter {
    
    public static void main(String[] args){
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/nmars/My VS Code/EtownCodingChallengeSpring2025/Classes.txt"))) {
            try (PrintWriter out = new PrintWriter(new File("C:/Users/nmars/My VS Code/EtownCodingChallengeSpring2025/FilteredClasses.txt"))){ 
                String line;

                //go through each line
                while ((line = reader.readLine()) != null) {
                    //remove useless lines
                    if(line.equals("\t\t\t</tr><tr>") || line.equals("") || line.indexOf("background-color:") > -1 || line.indexOf("(1W)") > -1){

                    }
                    else{
                        //remove specific characters
                        String newLine = "";
                        for(int i = 0; i < line.length(); i++){
                            if(i < line.length()-2){
                                if(!(line.substring(i,i+2).equals("  "))){
                                    newLine += line.substring(i,i+1);
                                }
                                else{
                                    i+=2;
                                }
                            }
                            else{
                                newLine += line.substring(i,i+1);
                            }
                        }
                        out.println(newLine);
                    }
                }
                reader.close();
                out.close();
            } catch (Exception e) { 
                System.out.println("File Error"); 
                return; 
            } 
            

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
