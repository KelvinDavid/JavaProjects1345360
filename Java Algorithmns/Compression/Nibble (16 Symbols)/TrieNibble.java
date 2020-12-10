
import java.io.File;
import java.lang.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Enumeration;
import java.util.Collections;

//Group Members
//Kelvin David ID: 1345360
//Daniel Wilson ID: 1345359
//TrieNibble.java is used to create a Trie with the symbol set
//of 16 symbols in order to repsresent all combinations of a 4 bit nibble
//It navigates the Trie through the use of nodes setup as a linked list
//across siblings (.next) and bellow parrents (.lower)

public class TrieNibble
    {
        //Stores the starting node which is used to navigate the Trie
        private Node head;
        //Stores the bite array which used to proccess the file into the trie
        byte[] fileByteArray;
        //Stores the representaion of each nibble in a arraylist of chars
        ArrayList<Character> splitBytesNibbles = new ArrayList<Character>();

        //Creates an array storing symbols with the values of the alphabet from A to P
        char[] symbols = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P' };

        //Creates an array used to be able to setup the intial dictionary (As head is created seperate, 'A' added outside loop)
        char[] setupDict = new char[] { 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P' };

        //Is an arraylist which is used to be able to store the phrase numbers to be returned to the encoder
        ArrayList<Integer> phraseNums = new ArrayList<Integer>();

		//Is used to be able to enumerate through the splitBytesNibbles
		Enumeration<Character> splitBytesNibblesE;

        //Stores the current phrase which is used to be able to detrmine what the current phrase is and
        //what the missmatch character ends up being
        ArrayList<Character> currentPhrase = new ArrayList<Character>();

        //Stores the current phrase number to allow for it to be incremented and keep track of what phrase number
        //an added phrase number needs to be
        int phrase = 0;

        public TrieNibble(byte[] fBA)
        {
            //Passes in the bite array which is used to be able to access the bytes of the file
            fileByteArray = fBA;
        }

        //Is used to be able to create the Nodes which allow for the Trie to be created and traversed
        private class Node {
            public char symbol;
            //Stores the required nodes to enable traversal of the link lists
            public Node parentNode;
            public Node next;
            public Node lower;
            int phraseNum;

            public Node(char s, Node p)
            {
                symbol = s;
                phraseNum = phrase;
                //Allows for a node to print out its phrase seqence of symbols once it has been added for debugging
                //pouposes
                parentNode = p;
                //Has both a next and lower node field for navigate through the trie like a linked list
                next = null;
                lower = null;
                //Adds one to the phrase num when new node is created
                phrase++;				
            }
        }

        //Starts the operations to be able to create the trie and return the final phrase numbers output
        //First starts by spliting all of the passed in bits and stores them into a arrayList of symbols
        //called splitBytesNibbles
        public ArrayList<Integer> start() {
	       	System.out.println("Creating Dictionary:");
	        //Creates the dictionary to allow for the starting nodes to be setup
	        setupDict();
	        //Prints out that it is starting the splitting proccess
	        System.out.println("Splitting byte array into nibbles:");
			//Creates the 
			byte firstByte;
			byte secondByte;
	        //Reads the current byte and stores it into the
	        for (byte b : fileByteArray) {
	            //Is used to be able to split the byte into 2 nibbles
	            //(From 8 bits into 4 sets of 4 bits)
	            firstByte = (byte) ((b >> 4) & (byte) 0x0F);
	            secondByte = (byte) (b & 0x0F);
	            //Uses the binary bite value as an int to index a symbol between A to P
	            splitBytesNibbles.add(symbols[firstByte]);
	            splitBytesNibbles.add(symbols[secondByte]);
	        }				
			//Assigns the splitBytes to an enumeration for it to go through each ellement
			splitBytesNibblesE = Collections.enumeration(splitBytesNibbles);
	        //Prints out a message to say that the byte array has been converted into a ArrayList of nibbles
	        System.out.println("Finished splitting byte array into nibbles");
	        //Goes through the entire array list of symbols to insert the values into the byte tree until
	        //it has run out of symbols to add
	        insertValues();		        
			//Once all the data has been inserted, return the list of phrase numbers
		    return phraseNums;
        }

        //Allows for data to be inserted into the t
        private void insertValues()
        {
            //Is used to be able to hold a parent reference to the current node
            Node parrent = null;
            //Starts at the root node
            Node current = head;
			//Is used to be able to know if what the last node encontered is from a lower
			//and also can be used to set the prevous .next as a new node
            Node prev = null;
            //Is used to be able to store the current symbol
            char currentS;			
			boolean lower;	
			//Is used to be able to create a new node to be the
			Node newNode;
            //Gets the next ellement in the arraylist
            currentS = splitBytesNibblesE.nextElement().charValue();
            //Goes though a loop of reading symbols from the splitBytesNibbiles arraylist
            while(splitBytesNibblesE.hasMoreElements()){
                //Goes through the loop until the reference of current is null meaning there is a place that the
                while (current != null){
                    if(current.symbol == currentS){
                        //Adds the current symbol to the current phrase
                        currentPhrase.add(currentS);
                        //If there is still more ellements to go through
                        if(splitBytesNibblesE.hasMoreElements()){
                            //Updates the parent to be the prev current
                            parrent = current;
                            //Goes down a level in the Trie by setting current to its .lower reference
                            current = current.lower;
                            //Gets the next byte from the array
                            currentS = splitBytesNibblesE.nextElement().charValue();
							//Sets the prev node reference to be null as it is now on a new lower layer
							prev = null;
                        }
                        //Else there are no more nibbles to be read
                        else{
                            //Returns as it has finished adding all the data
                            return;
                        }
                    }
                    //Else naviagates to the next value in the linked list
                    else{
						//Sets the prev node to be current
						prev = current;
						//Naviagates to the next node in the linked list
                        current = current.next;
                    }
                }
				//Adds the encountered phrase number from the parrent to the phraseNums list
                phraseNums.add(parrent.phraseNum);
                //Creates a new node with the value of the current symbol, parrent phase num and
				//Parrent
                newNode = new Node(currentS, parrent);
				//Checks if it was going down a layer or going through the .next of a node
				//by checking if the value of prev is a node (for .next) or null (for .lower)
				if (prev != null){
					//If the last encounted node was from .next then set its .next to be
					//the new node
					prev.next = newNode;
				}
				//set the parrent.lower to be the newly created node
				parrent.lower = newNode;
                //Clears the current phrase byte arrayList
                currentPhrase = new ArrayList<Character>();
                //Goes back to the start of the Trie by setting current to be head
                current = head;
            }
            //Once all nibbles have been added return to the start methoid
            return;
        }

        //Sets up the inital set of 16 (A to P symbols) used for the dictionary
        private void setupDict(){
            //Starts at the root node
            Node current;
			//Creates a reference for the head node to start
			head = new Node('A', null);
			System.out.println(head.symbol + " " + head.phraseNum);
			//Sets the prev to start out as head
			Node prev = head;
            //Goes through each of the different values and adds them to be starting nodes
            for(char c: setupDict) {
                //Creates a new node and sets it as the parrent to be null as it does not
				//have a upper layer
                current = new Node(c, null);
				//Sets the .next to be the new node
				prev.next = current;
                //Moves on to the next value of current
				System.out.println(current.symbol + " " + current.phraseNum);
				//Moves prev to being the next node
				prev = current;
            }
        }
    }
