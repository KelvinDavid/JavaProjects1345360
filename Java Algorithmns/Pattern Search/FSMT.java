import java.util.ArrayList;
import java.util.Arrays;

//Kelvin David - 1345360
//Daniel Wilson ID: 1345359
//FSMcompiler: Converts a given regex into a FSM

public class FSMT
{
    public static String[] p;
    public static int j;
    public static int state = 1;


    public static String[] ch;
    public static int[] n1;
    public static int[] n2;
    public static boolean espace = false;

    public static ArrayList<String> nonVocabList;
    public static ArrayList<String> operatorList;

    public FSMT(){}

    public void runFSM(String[] extractP)
    {
        //Holds the function lists
        nonVocabList = new ArrayList<String>(Arrays.asList("*","?","|",".","[","]"));
        operatorList = new ArrayList<String>( Arrays.asList("*","?"));

        //Extracts the Regex from the input
        p = extractP;
        ch = new String[p.length+2];
        n1 = new int[p.length+2];
        n2 = new int[p.length+2];

        //Start Compiling
        int intial = expression();
        //Set start state
        setState(0, "/s", intial, intial);
        //Count the amount of states in the FSM
        int stateCount = 1;
        for(int i=0; i < ch.length;i++)
        {
            if(ch[i] != null)
            {
                stateCount++;
            }
        }
        //System out the amount of states
        System.out.println(stateCount);
        //System out the states in CSV order
        for(int i=0; i < ch.length;i++)
        {
            if(ch[i] != null)
            {
                System.out.println(i + "," + ch[i] + "," + n1[i] + "," + n2[i]);
            }
        }
        System.out.println(stateCount-1 + "," + "/e" + "," + -1 + "," + -1);
    }

    //Creates a state in the FSM
    public static void setState(int s, String c, int nn1, int nn2)
    {
        ch[s] = c;
        n1[s] = nn1;
        n2[s] = nn2;
    }

    //Creates Expression FSMs
    public static int expression()
    {
        int r;
        //Create a term
        r=term();
        if(p[j].compareTo("/f") == 0)
        {
            return 1;
        }
        //if end of p
        if(r == 0)
        {
            return 0;
        }
        //If there is more valid expressions
        if(isvocab(p[j])||p[j].compareTo("[") == 0)
        {
            expression();
        }
        return(r);
    }

    //Creates Term FSMs
    public static int term()
    {
        int f = state-1;
        int t1 = factor();
        int r = t1;
        int t2;
        //If end of p
        if(p[j].compareTo("/f") == 0)
        {
            return 0;
        }
        //if repetition
        if(p[j].compareTo("*") == 0 || p[j].compareTo("?") == 0)
        {
            setState(state,"/b",state+1,t1);
            j++;
            r=state;
            state++;
        }
        //if alternation
        if(p[j].compareTo("|") == 0){
            //modify end states of the factors used
            if(n1[f] == n2[f])
            {
                n2[f]=state;
            }
            n2[f]=state;
            n1[f]=state;
            f=state-1;
            j++;
            r=state;
            state++;
            t2=term();
            //Create branching state
            setState(r,"/b",t1,t2);
            if(n2[f]==n2[f])
            {
                n2[f]=state;
            }
            n1[f]=state;
        }
        return r;
    }

    //Create Factor FSMs
    public static int factor()
    {
        if(p[j].compareTo("/f") == 0)
        {
            return 0;
        }
        int r = state;
        //if normal vocab
        if(isvocab(p[j]) || espace == true)
        {
            setState(state,p[j],state+1,state+1);
            espace =false;
            j++;
            r=state;
            state++;
        }
        else
        {
            //if concatenation
            if(p[j].compareTo("[") == 0)
            {
                j++;
                r = expression();
                if(p[j].compareTo("]") == 0)
                {
                    j++;
                }
                else
                {
                    error("Bracket Error");
                }
            }
            else
            {
                error("Factor Error");
            }
        }
        return r;
    }

    //Checks if the current marker is vocab or function
    public static boolean isvocab(String curCh)
    {
        //If next character is literal
        if(curCh.compareTo("\\") == 0)
        {
            if(j >= (p.length - 1))
            {
                error("Vocab \\ Closure Error");
            }
            espace = true;
            j++;
            return true;
        }
        //check if current character belongs to functions
        else if (nonVocabList.contains(p[j]))
        {
            return false;
        }
        return true;
    }

    //Prints out error
    public static void error(String error)
    {
        System.out.println(error + ", error encountered at index: " + j);
        System.exit(0);
    }
}
