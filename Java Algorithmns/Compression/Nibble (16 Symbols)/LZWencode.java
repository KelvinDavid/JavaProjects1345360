

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

//Group Members
//Kelvin David ID: 1345360
//Daniel Wilson ID: 1345359
//This LZWencode.java program encodes a file using the LZW methoid
//through the use of a TrieNibble class consisting of 
//16 initial symbols (representing 4 bits / half a byte)

public class LZWencode
{
    public static void main(String[] args)
    {
        try
        {
            File file = new File(args[0]);
            byte[] fileContent = Files.readAllBytes(file.toPath());
            //Is used to be able to store the output that the Trie creates
            List<Integer> encoded;
            //Creates a new Trie and passes in the file byte array
            TrieNibble T = new TrieNibble(fileContent);
            //Starts the data process and store what is returned to
            ArrayList<Integer> sequenceNumbers = T.start();
            //Outputs the encoded file with a sequence of "," seperated values
            System.out.println("Finished");
			//Allows for the sequence numbers to be output to a text file and sets the name of the file to be
			//the passed in arg
			FileWriter writePrint = new FileWriter(args[0] + ".txt", false);
			PrintWriter printLine = new PrintWriter(writePrint);
			//For each int in the seqence of numbers
			for (int i : sequenceNumbers) {
                printLine.printf("%s" + "%n", i);
            }
			//Closes the print writer as the operations have finished
			printLine.close();			
        }
        catch(Exception e)
        {
            System.out.print("Error encountered: ");
            e.printStackTrace();
        }
    }
}
