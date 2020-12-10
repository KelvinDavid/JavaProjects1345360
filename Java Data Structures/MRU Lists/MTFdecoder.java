//Kelvin David 1345360
//MTFdecoder
//----------------------------------
//This program is purposed for 
//getting a MTF encoded file 
//and converting it back to a
//regular text file
//----------------------------------
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.FileWriter;
import java.io.Writer;
import java.io.BufferedWriter;

class MTFdecoder
{
	public static void main(String [] args)
	{
		//Holds wordLists
		WordList input = new WordList();
		//Check if file has been identified
		if (args.length != 1)
		{
			System.err.println("Usage: java MTFencoder <Filename>");
			return;
		}
		
		try
		{
			
			//Individulizes into words
			//read and write files
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			BufferedWriter wr = new BufferedWriter(new FileWriter("DecodedOutput.txt"));
			String s = br.readLine();
			String key = "";
			String delims = " .,;:?!-\t\n";
			
			while (s != null)
			{
				String c;
				String[] word = s.split(" ");
				if(word[0].equals("0") && word.length > 1) //checks if it's a 0
				{
					//covert into word
					//store word
					input.add(word[1]);		
				}
				//Turn the copy of the token into a index
				c = word[0];
				//Finds the token in the wordList	
				String out = input.getWord(Integer.parseInt(c));
				//if a * create new line in the output file
				if(out.equals("*"))
				{
					wr.newLine();
					input.order(input.getWord(Integer.parseInt(c)));
				}
				//else print the given word at [c] index
				else
				{
					wr.write(out);
					wr.write(" ");
					input.order(input.getWord(Integer.parseInt(c)));
				}
				s = br.readLine();
			}
			wr.close();
		}
		catch(Exception e) 
		{
			System.out.print("Error encountered: ");
			e.printStackTrace();
		}
	}
}
