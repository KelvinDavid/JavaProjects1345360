//Kelvin David 1345360
//MTFencoder
//----------------------------------------------------
//This program is purposed for taking a text file
//and encode it into MTF (Move to Front) format
//----------------------------------------------------

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.FileWriter;
import java.io.Writer;
import java.io.BufferedWriter;


class MTFencoder
{
	public static void main(String [] args)
	{
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
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			BufferedReader rr = new BufferedReader(new FileReader(args[0]));
			//Read the first line
			String s = br.readLine();
			//This holds the encoded format
			String d = "";
			//the delimiters
			String delims = " .,;:?!-\t";
			
			//While the whole file isn't read
			while (s != null)
			{
				//Holds the individual tokens
				StringTokenizer st = new StringTokenizer(s,delims);
				//While the line hasn't been read
				while (st.hasMoreTokens())
				{
					//This reads the token
					String t = st.nextToken();
					//If token is not in list
					if(input.find(t) == -1) 
					{
						input.add(t);
						//add the new word in "0word" format
						d = d + Integer.toString(input.find(t)) + t + " ";
					}
					//If the token is in the list
					else
					{
						//Add as integer like "12"
						d = d + Integer.toString(input.find(t)) + " ";
						input.add(t);
					}
				}
				//Checks if to move to next line
				if(input.find("*") == -1)
				{
					input.add("*");
					d = d + Integer.toString(input.find("*")) + "*" + " ";
				}
				//Executes if * already exists
				else
				{
					d = d + Integer.toString(input.find("*")) + " ";
					input.add("*");
					input.order(input.getWord(input.find("*")));
				}
				s = br.readLine();
			}
			input.dump(); //debugging
			
			//Encode WordList
			BufferedWriter wr = new BufferedWriter(new FileWriter("Output.mtf"));
			String[] print = d.split(" ");
			for(String di : print)
			{
				//This formats any tokens that first come in i.e. 0 word
				if(di.charAt(0) == '0' && di.length() > 1)
				{
					String index = di.replaceAll("[^\\d-]", "");
					String word = di.replace("0", "");
					wr.write(index + " " + word);
					wr.newLine();
				}
				else
				{
					wr.write(di); //write to text file
					wr.newLine();				
				}
			}
			wr.close();
			
		}
		catch(Exception e) 
		{
			System.out.print("Error encountered: ");
			e.printStackTrace();
		}
	}
}//
