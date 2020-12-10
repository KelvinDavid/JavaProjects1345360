import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Group Members
//Kelvin David ID: 1345360
//Daniel Wilson ID: 1345359
//LZWdecoder
//This program decodes a encoded file using the LZW method
//it uses a 0-255 HEX dictionary as it's initial symbol set

public class LZWdecode
{
    //Nodes for the linked list
    public static class Node
    {
        public String symbol;
        public int index;
        public ArrayList<Node> children;
        public Node next;

        public Node(String sym, int in)
        {
            index = in;
            children = new ArrayList<Node>();
            symbol = sym;
        }

        public void dumpInfo()
        {
            System.out.println("(" + "index: " + index + "," + "symbol: " + symbol + ")");
        }
    }

    //Simple ordered linked list used as a Dictionary Structure
    public static class Dictionary
    {
        private Node head = null;

        public void add(Node n){
            Node curr = head; //pointer
            Node prev = null; //previous pointer
            Node newx = n; //new node
            if(head == null){ //if list is empty add it to head
                head = newx;
                return;
            }
            while(curr != null){ //loop through list
                if(curr.index > newx.index){ //check if pointer is more than new node
                    newx.next = curr; //link new node to current node
                    if(prev == null){ //if looking at first element in list
                        head = newx; //change head to new element
                        return;
                    }
                    prev.next = newx; //link previous pointer to new node
                    return;
                }
                prev = curr; //moves pointers along
                curr = curr.next; // ^^^^^^^^^^^^
            }
            prev.next = newx; //adds node to end
        }

        public void dump()
        {
            Node curr = head;
            while (curr != null){ //loop through whole list and print each node
                System.out.println(curr.index + ": " + curr.symbol);
                curr = curr.next;
            }
            return;
        }

        public Node get(int index){
            Node curr = head;
            while(curr != null){ //loop until the end of the list
                if(curr.index == index){ //checks if index is found
                    return curr;
                }
                else {
                    curr = curr.next; //moves to next node in list
                }
            }
            return null;
        }

        public boolean has(String x){
            Node curr = head;
            while(curr != null){ //loop until the end of the list
                if(curr.symbol.compareTo(x) == 0){ //checks if index is found
                    return true;
                }
                else {
                    curr = curr.next; //moves to next node in list
                }
            }
            return false;
        }

        public int length(){
            Node curr = head;
            int count = 0;
            while(curr != null){ //loop through list and count each node
                curr = curr.next;
                count++;
            }
            return count;
        }
    }



    public static void main(String[] args)
    {
        final long startTime = System.nanoTime();
        //GETTING FILENAME
        File file = new File(args[0]);
        //System.out.println(file.length());
        Scanner scanner;
        FileInputStream fin;
        String filename = args[0];

        try
        {
            //READING THE FILE
            scanner = new Scanner(file);
            List<Integer> encodedFile = new ArrayList<Integer>();
            while(scanner.hasNextInt())
            {
                encodedFile.add(scanner.nextInt());
            }


            //CREATION OF INITIAL DICTIONARY
            Dictionary dictionary = new Dictionary();
            int index = 0;
            for(int i=0; i < 256; i++)
            {
                if(i <= 15)
                {
                    dictionary.add(new Node("0" + Integer.toHexString(i), i));
                }
                else
                {
                    dictionary.add(new Node(Integer.toHexString(i), i));
                }
                index++;
            }
            //DECODING
            String entry;
            String sym = "00";
            int prevcode = -1;
            int curcode;
            String output = "";
            System.out.println("Starting decoding...");
            int tt = 0;
            for(Integer i : encodedFile) //When all phrases have been read
            {
                System.out.println(tt + " " + i + " here!");
                boolean Encoded = false;
                curcode = i; //read a phrase
                if(dictionary.get(curcode) == null) //If the phrase number is incomplete
                {
                    String newStr = dictionary.get(prevcode).symbol + sym;
                    dictionary.add(new Node(newStr, index)); //complete using last used symbol
                    index++;
                    Encoded = true;
                }
                entry = dictionary.get(curcode).symbol;
                output += entry;
                sym = String.valueOf(entry.charAt(0)) + String.valueOf(entry.charAt(1));
                if(prevcode >= 0 && Encoded == false) //if not encoded and prevs phrase exists
                {
                    String newStr = dictionary.get(prevcode).symbol + sym;
                    dictionary.add(new Node(newStr, index));
                    index++;
                }
                prevcode = curcode;
                tt++;
            }
            System.out.println("-DICTIONARY----");
            dictionary.dump();
            System.out.println("-DICTIONARY----");


            //COVERT STRING OUTPUT TO AN INTEGER LIST
            ArrayList<Integer> intConstruct = new ArrayList<Integer>();
            for(int i=0; i < output.length() - 1; i+=2)
            {
                String individual = String.valueOf(output.charAt(i)) + String.valueOf(output.charAt(i+1));
                int individualInt = Integer.valueOf(individual, 16).intValue();
                intConstruct.add(individualInt);
            }

            //CONVERT INTEGERS INTO A BYTE LIST
            byte[] finalOutput = new byte[intConstruct.size()];

            for(int i=0; i < intConstruct.size(); i++)
            {
                int fo = intConstruct.get(i);
                finalOutput[i] = (byte)fo;
            }

            for(int i=0; i < finalOutput.length; i++)
            {
                int fo = intConstruct.get(i);
                finalOutput[i] = (byte)fo;
            }

         //WRITING TO FILE
            String[] split = filename.split("\\.");
            String newFileName = split[0] + "Decoded" + "." +split[1];
            FileOutputStream fos = new FileOutputStream(newFileName);
            fos.write(finalOutput);
            fos.close();
            System.out.println("Decoding finished!");
        }
        catch(Exception e)
        {
            System.out.print("Error encountered: ");
            e.printStackTrace();
        }
        final long duration = System.nanoTime() - startTime;
        System.out.println("Timetaken: " + duration + "ns" + " in nanoTime");
    }
}
