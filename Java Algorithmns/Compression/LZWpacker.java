
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Group Members
//Kelvin David ID: 1345360
//Daniel Wilson ID: 1345359
//This LZWpacker.java program allow for the phrases output by the Encoder to the 
//<input file name.extention>.txt file to be compressed significantly in size to 
//the point were it usally ends up being smaller than the original file
public class LZWpacker
{
    public static void main(String[] args)
    {
        File file = new File(args[0]);
        Scanner scanner;
        try
        {
            scanner = new Scanner(file);
            List<Integer> encodedFile = new ArrayList<Integer>();
            while(scanner.hasNextInt())
            {
                encodedFile.add(scanner.nextInt());
            }

            for(int num : encodedFile)
            {
                System.out.println("BValue = " + String.format("%32s",
                        Integer.toBinaryString(num)).replaceAll(" ", "0"));
            }

            int b;
            ArrayList<Byte> output = new ArrayList<Byte>();
            b = encodedFile.get(0);
            System.out.println("BValue = " + String.format("%32s",
                    Integer.toBinaryString(b)).replaceAll(" ", "0"));
            for(int i=1; i < encodedFile.size(); i++)
            {
                //Get byte size needed to pack
                System.out.println("BValue = " + String.format("%32s",
                        Integer.toBinaryString(b)).replaceAll(" ", "0"));
                int a = encodedFile.get(i);
                boolean startCount = false;
                System.out.println("AValue = " + String.format("%32s",
                        Integer.toBinaryString(a)).replaceAll(" ", "0"));
                String aS = String.format("%32s",
                        Integer.toBinaryString(a)).replaceAll(" ", "0");
                int needed = 10;
                System.out.println("bytes needed: " + needed);
                //Bytes left?
                System.out.println("BValue = " + String.format("%32s",
                        Integer.toBinaryString(b)).replaceAll(" ", "0"));
                String bS = String.format("%32s",
                        Integer.toBinaryString(b)).replaceAll(" ", "0");
                System.out.println("BValue = " + String.format("%32s",
                        Integer.toBinaryString(b)).replaceAll(" ", "0"));
                int left = 0;
                for(int j=0; j < bS.length(); j++)
                {
                    if(bS.charAt(j) == '1')
                    {
                        break;
                    }
                    left++;
                }
                System.out.println("bytes left: " + left);
                //
                //PACKING OUTPUT
                System.out.println("Create Output: " + (needed > left));
                if(needed > left)
                {
                    int out1 =  (b & 0xFF);
                    b = (b >>> 8);
                    int out2 = (b & 0xFF);
                    b = (b >>> 8);
                    output.add((byte)out1);
                    output.add((byte)out2);
                    System.out.println(String.format("%32s",
                            Integer.toBinaryString(out1)).replaceAll(" ", "0") + " " + String.format("%32s",
                            Integer.toBinaryString(out2)).replaceAll(" ", "0") + " Outputed!!");
                    System.out.println("Cleared!");
                    System.out.println("BValue = " + String.format("%32s",
                            Integer.toBinaryString(b)).replaceAll(" ", "0"));
                }
                        //PACKING
                        startCount = false;
                bS = String.format("%32s",
                        Integer.toBinaryString(b)).replaceAll(" ", "0");
                        int shift = 0;
                        for(int j=0; j < bS.length(); j++)
                        {
                            if(bS.charAt(j) == '1')
                            {
                                startCount = true;
                            }
                            if(startCount == true)
                            {
                                shift++;
                            }
                        }
                        System.out.println("bytes shifting: " + shift);
                        a = (a << shift);
                        System.out.println("AValue = " + String.format("%32s",
                                Integer.toBinaryString(a)).replaceAll(" ", "0"));
                        b = a | b;
                        System.out.println("packed!");
                        System.out.println("BValue = " + String.format("%32s",
                                Integer.toBinaryString(b)).replaceAll(" ", "0"));
                        System.out.println("---");
                if(i == encodedFile.size()-1)
                {
                    output.add((byte)b);
                }
                //
            }
            System.out.println("");
            System.out.println("----ORIGINAL INPUT----------------------------");
            System.out.println("");
            for(int i : encodedFile)
            {
                System.out.print(i + ",");
            }
            System.out.println("");
            System.out.println("----PACKED OUTPUT-----------------------------");
            System.out.println("");
            for(byte bt : output)
            {
                System.out.print(bt + ",");
            }
            System.out.println("");
            System.out.println("");

            String newFileName = args[0] + "_Packed" + ".LZW";
            FileOutputStream fos = new FileOutputStream(newFileName);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (int i = 0; i < output.size(); i++) {
                bw.write(output.get(i));
                bw.newLine();
            }
            System.out.println("");
            System.out.println("");
            System.out.println("input: " + encodedFile.size());
            System.out.println("output: " + output.size());
            System.out.println("Packing finished!");
            bw.close();
            fos.close();
        }
        catch (Exception e)
        {
            System.out.print("Error encountered: ");
            e.printStackTrace();
        }
    }
}
