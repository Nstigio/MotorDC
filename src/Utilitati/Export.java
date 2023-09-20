package Utilitati;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Bogdan
 */
public class Export {
    
    public static void PrintToTXT(String filePath, ArrayList array){
            double[] timp = (double[]) array.get(0);
            double[] curent = (double[]) array.get(1);
            double[] turatie = (double[]) array.get(2);
            
            
            try {
            // Writer pentru timp    
            FileWriter timpWriter = new FileWriter(filePath+"\\timp_java.txt");
            
            BufferedWriter timpBufferWriter = new BufferedWriter(timpWriter);    
            // writer pentru curent
            FileWriter curentWriter = new FileWriter(filePath+"\\curent_java.txt");
            BufferedWriter curentBufferWriter = new BufferedWriter(curentWriter);
            //writer pentru turatie
            FileWriter turatieWriter = new FileWriter(filePath+"\\turatie_java.txt");
            BufferedWriter turatieBufferWriter = new BufferedWriter(turatieWriter);
            
            for (double d : curent) {
                curentBufferWriter.write(Double.toString(d));
                curentBufferWriter.newLine();
            }
            for (double d : turatie) {
                turatieBufferWriter.write(Double.toString(d));
                turatieBufferWriter.newLine();
            }
            for (double d : timp) {
                timpBufferWriter.write(Double.toString(d));
                timpBufferWriter.newLine();
            }
            
            curentBufferWriter.close();
            curentWriter.close();
            turatieBufferWriter.close();
            turatieWriter.close();
            timpBufferWriter.close();
            timpWriter.close();
            System.out.println("Successfully wrote array contents to file.");
        } catch (IOException e) {
            System.err.println("Error writing array contents to file: " + e.getMessage());
        }
            
        }
}
