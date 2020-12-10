//Kelvin David - 1345360
//Daniel Wilson ID: 1345359
//REcompile: Parses a passed in argument for a Regular Expression before calling 
// the runFSM method on a FSMT class instance

import java.util.ArrayList;
import java.util.Arrays;

public class REcompile
{
    public static String[] p;
    public static int j = 0;

    public static ArrayList<String> nonVocabList;
    public static ArrayList<String> operatorList;

    public static void main(String[] args)
    {
        nonVocabList = new ArrayList<String>(Arrays.asList("*","?","|",".","[","]"));
        operatorList = new ArrayList<String>( Arrays.asList("*","?"));

        String[] extract = args[0].split("");
        p = new String[extract.length+1];
        for(int i=0; i < extract.length; i++)
        {
            p[i] = extract[i];
        }
        p[p.length-1] = "/f";
//        System.out.println("");
//        for(String s : p)
//        {
//            System.out.print(s + ",");
//        }
        //System.out.println("");
        parseExpression();
        //System.out.println("Regex Passed!");

        String[] e = args[0].split("");
        FSMT FSMcompiler = new FSMT();
        FSMcompiler.runFSM(p);
    }

//    public static void printProgress(String s)
//    {
//        System.out.println(p[j] + " is a " + s);
//    }

    public static void parseExpression()
    {
        parseTerm();
        if(p[j].compareTo("/f") == 0)
        {
            return;
        }
        //System.out.println("Checking vocab of " + p[j]);
        if(isvocab(p[j]) || p[j].compareTo("[") == 0)
        {
            parseExpression();
        }
        return;
    }
    public static void parseTerm()
    {
        parseFactor();
        if(p[j].compareTo("/f") == 0)
        {
            return;
        }
        if(p[j].compareTo("*") == 0 || p[j].compareTo("?") == 0)
        {
            //printProgress("is repeating");
            j++;
            return;
        }
        if(p[j].compareTo("|") == 0)
        {
            //printProgress("is OR");
            j++;
            parseTerm();
        }
        return;
    }
    public static void parseFactor()
    {
        if(p[j].compareTo("/f") == 0)
        {
            return;
        }
        if(isvocab(p[j]))
        {
            //printProgress("is vocab");
            j++;
        }
        else
            {
                if(p[j].compareTo("[") == 0)
                {
                    //printProgress("is open bracket");
                    j++;
                    parseExpression();
                    if(p[j].compareTo("]") == 0)
                    {
                        //printProgress("is closed bracket");
                        j++;
                    }
                    else error("Bracket Error");
                }
                else
                {
                    error("Factor Error");
                }
            }
        return;
        }


    public static boolean isvocab(String curCh)
    {
        //If next character is literal
        if(curCh.compareTo("\\") == 0)
        {
            if(j >= (p.length - 1))
            {
                error("Vocab \\ Closure Error");
            }
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

    public static void error(String error)
    {
        System.out.println(error + ", error encountered at index: " + j + " for " + p[j]);
        System.exit(0);
    }
}
