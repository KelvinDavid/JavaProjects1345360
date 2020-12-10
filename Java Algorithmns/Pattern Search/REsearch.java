import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//Kelvin David - 1345360
//Daniel Wilson ID: 1345359
//REsearch: Searches a text file using a given FSM

public class REsearch
{
    public static void main(String[] args)
    {
        //Finite State Machine Arrays
        String[] ch; //character
        int[] n1; //first possible next state
        int[] n2; //second possible next state

        try
        {
            BufferedReader in;
            Scanner scan = new Scanner(System.in);
            //Create Arrays using State Count
            System.out.println("Reading Compiler Output...");
            String x = scan.nextLine();
            System.out.println("read " + x);
            int stateCount = Integer.parseInt(x);
            ch = new String[stateCount];
            n1 = new int[stateCount];
            n2 = new int[stateCount];
            //Extract States and Input into arrays
            while (scan.hasNextLine())
            {
                if(x != null)
                {
                    x = scan.nextLine();
                    System.out.println("read " + x);
                    String[] read = x.split(",");
                    int curIndex = Integer.parseInt(read[0]);
                    ch[curIndex] = read[1];
                    n1[curIndex] = Integer.parseInt(read[2]);
                    n2[curIndex] = Integer.parseInt(read[3]);
                }
            }
            System.out.println("Reading Completed!");
            System.out.println("");
            System.out.println("");
            //FSM DEBUGGING
            System.out.println("Given FSM:");
            System.out.println("");
            for(String s : ch)
            {
                System.out.print(s+ ",");
            }
            System.out.println("");
            System.out.println("");
            for(int i : n1)
            {
                System.out.print(i + ",");
            }
            System.out.println("");
            System.out.println("");
            for(int j : n2)
            {
                System.out.print(j + ",");
            }
            System.out.println("");
            System.out.println("");
            //PATTERN SEARCH
            in = new BufferedReader(new FileReader(args[0]));

            String chP;
            String chC;

            ArrayList<String> matchLines = new ArrayList<String>();
            ArrayList<Integer> matchLinesIndex = new ArrayList<Integer>();

            int count = 0;
            int state = 0;
            int p = 0;
            int m = 0;
            String curLine = in.readLine();
            //Read lines till end of file
            while(curLine != null)
            {
                Deque stack = new Deque();
                System.out.println("processing " + curLine);
                String[] line = curLine.split("");
                addFrontStates(stack, state, n1, n2);
                //if marker exceeds the current line
                while(m < line.length)
                {
                    boolean branch = false;
                    System.out.println("InStack: " + stack.getAll());
                    //loop until the SCAN state is seen
                    while(!stack.checkSCAN())
                    {
                        state = stack.peekFirst();
                        chC = ch[state];
                        chP = line[m+p];
                        System.out.println("M = " + m + " + P = " + p);
                        System.out.println("Looking at State: " + state);
                        System.out.println("state: " + state + " chC:" + chC + " -> " + " chP:" + chP);
                        //If end of FSM
                        if(n1[state] == -1 && n2[state] == -1)
                        {
                            System.out.println("-- Match Found --");
                            state = n1[state];
                            stack.removeAllFirst();
                            break;
                        }
                        //If branching state
                        else if(chC.compareTo("/b") == 0)
                        {
                            System.out.println("State is branching");
                            addBackStates(stack,state,n1,n2);
                            stack.removeAllFirst();
                            branch = true;
                            break;
                        }
                        //If current character passes current state
                        else if(chC.compareTo(chP) == 0)
                        {
                            System.out.println("State: " + state + " passed");
                            addBackStates(stack,state,n1,n2);
                            stack.removeAllFirst();
                            break;
                        }
                        stack.removeFirst();
                    }
                    if(state == -1)
                    {
                        matchLines.add(curLine);
                        matchLinesIndex.add(count);
                        break;
                    }
                    boolean hasQueueReset = stack.HasQueueReset();
                    System.out.println("InStack: " + stack.getAll());
                    System.out.println("Succeed = " + hasQueueReset + " count = " + stack.length());
                    //Checking if there are possible next states
                    if(hasQueueReset)
                    {
                        if(branch == false)
                        {
                            System.out.println("p: " + p);
                            p++;
                            if((p + m) >= line.length)
                            {
                                System.out.println("reset?");
                                state = 0;
                                m++;
                                p = 0;
                                addFrontStates(stack, state, n1, n2);
                            }
                        }
                    }
                    else
                    {
                        state = 0;
                        m++;
                        p = 0;
                        addFrontStates(stack, state, n1, n2);
                    }
                    System.out.println("END ------------Line: " + count +"----------------");
                }
                m = 0;
                p = 0;
                state = 0;
                curLine = in.readLine();
                System.out.println("END OF LINE--------------------------");
                count++;

            }
            System.out.println("END OF FILE-----------------------------");
            System.out.println("");
            //DEBUGGING: Testing output
            System.out.println("MATCHED LINES----------------------");
            int label = 0;
            for(String s : matchLines)
            {
                label++;
                System.out.println(label + "." + s);
            }
            System.out.println("----------------------------------");
            System.out.println(label + " lines out of " + count + " lines contained the pattern");

            //WRITING TO FILE
            File output = new File("REsearchOutput.txt");
            FileOutputStream fos = new FileOutputStream(output);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for(int i = 0; i < matchLines.size(); i++)
            {
                bw.write( " Match found on line: " + matchLinesIndex.get(i) + " : " + matchLines.get(i));
                bw.newLine();
            }
            bw.close();
        }
        catch (Exception e)
        {
            System.out.println("Error :" + e);
        }
    }
    //This adds states to the top of the deque
    public static void addFrontStates(Deque stack, int state, int[]n1, int[]n2)
    {
        if(n1[state] == n2[state])
        {
            stack.addFirst(n1[state]);
        }
        else
        {
            stack.addFirst(n2[state]);
            stack.addFirst(n1[state]);

        }
    }
    //This adds states to the bottom of the deque
    public static void addBackStates(Deque stack, int state, int[]n1, int[]n2)
    {
        if(n1[state] == n2[state])
        {
            stack.addLast(n1[state]);
        }
        else
        {
            stack.addLast(n1[state]);
            stack.addLast(n2[state]);

        }
    }
}
