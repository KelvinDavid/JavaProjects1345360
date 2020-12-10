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
//it uses a A to P dictionary as it's initial symbol set

public class LZWdecode
{
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
            //System.out.println("(" + "index: " + index + "," + "symbol: " + symbol + ")");
        }
    }

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
                //System.out.println(curr.index + ": " + curr.symbol);
                curr = curr.next;
            }
            return;
        }

        public Node get(int index){
            Node curr = head;
            while(curr != null){ //loop until the end of the list
                if(curr.index == index){ //checks if value is found
                    return curr;
                }
                else {
                    curr = curr.next; //moves to next node in list
                }
            }
            return null;
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

        File file = new File(args[0]);
        //System.out.println(file.length());
        Scanner scanner;
        FileInputStream fin;
        String filename = args[0];

        try
        {
            scanner = new Scanner(file);
            List<Integer> encodedFile = new ArrayList<Integer>();
            while(scanner.hasNextInt())
            {
				int current = scanner.nextInt();
                encodedFile.add(current);
            }


            Dictionary dictionary = new Dictionary();



            char[] inSymbols = "ABCDEFGHIJKLMNOP".toCharArray();
            int index = 0;
            for(char c : inSymbols)
            {
                dictionary.add(new Node(String.valueOf(c), index));
                index++;
            }
            String entry;
            char ch = 'a';
            int prevcode = -1;
            int curcode;
            String output = "";
            System.out.println("decode in process....");
            for(Integer i : encodedFile)
            {
                boolean Encoded = false;
                curcode = i;
                if(dictionary.get(i) == null)
                {
                    String newStr = dictionary.get(prevcode).symbol + String.valueOf(ch);
                    dictionary.add(new Node(newStr, index));
                    index++;
                    Encoded = true;
                }
                entry = dictionary.get(i).symbol;
                output += entry;
                ch = entry.charAt(0);
                if(prevcode >= 0 && Encoded == false)
                {
                    String newStr = dictionary.get(prevcode).symbol + String.valueOf(ch);
                    dictionary.add(new Node(newStr, index));
                    index++;
                }
                prevcode = curcode;
            }
			String decodeString = new String("ABCDEFGHIJKLMNOP");
            char[] charConstruct = output.toCharArray();
            ArrayList<Integer> intConsrtuct = new ArrayList<Integer>();
            for(char c : charConstruct)
            {
                intConsrtuct.add(decodeString.indexOf(c));
            }

            byte[] finalOutput = new byte[intConsrtuct.size()/2];
            int in = 0;
            for(int i=0; i < intConsrtuct.size() - 1; i+=2)
            {
                byte n1 = (byte) (((byte)((int)intConsrtuct.get(i)) << 4) & (byte) 0xF0);
                byte n2 = (byte) ((byte)((int)intConsrtuct.get(i+1)) & 0x0F);
                byte fullbyte = (byte)(n1 | n2);
                finalOutput[in] = fullbyte;
                in++;
            }
            String[] split = filename.split("\\.");
            String newFileName = split[0] + "Decoded" + "." +split[1];
            FileOutputStream fos = new FileOutputStream(newFileName);
            fos.write(finalOutput);
            fos.close();
            System.out.print("decoding finished!");
        }
        catch(Exception e)
        {
            System.out.print("Error encountered: ");
            e.printStackTrace();
        }
    }
}
