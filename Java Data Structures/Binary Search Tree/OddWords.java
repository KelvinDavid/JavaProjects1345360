//Kelvin David 1345360
//OddWords class
//--------------------------------
//This class recieves a file and 
//creates a BST list of the words that
//appear a odd number of times

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

class OddWords
{
	public static void main(String [] args)
	{
		BSTlex oddWords = new BSTlex();
		if(args.length != 1) //if no file entered
		{
			System.err.println("Usage: java OddWords <Filename>");
			return;
		}
	
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(args[0])); //file reader
			String s = br.readLine(); //read first line

			while(s != null) //while file is not read
			{
				String[] line = s.split("\\W+");//grab alpha character
				for(String t : line)
				{
					String c = t.toLowerCase();//convert to lowercase
					if(oddWords.find(c) == true)//if already in BST
					{
						oddWords.remove(c); //remove it
						System.out.println(oddWords.getOutput() + " DELETED");
					}
					else //if not
					{
						oddWords.insert(c); //insert it
						System.out.println(oddWords.getOutput() + " INSERTED");
					}
				}
				s = br.readLine(); //read next line
			}
			oddWords.dump(); //print contents of BST
			System.out.println("");
			System.out.println();
		}
		catch(Exception e)
		{
			System.out.println("Error Encountered: ");
			e.printStackTrace();
		}
	}
}
