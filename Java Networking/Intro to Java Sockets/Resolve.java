//Kelvin David 1345360
//This class resolves a given set of host addresses

import java.net.InetAddress;
import java.net.UnknownHostException;

class Resolve
{
    public static void main(String [] args)
    {
        if(args.length == 0) //if no hostNames entered
        {
            System.err.println("Usage: java Resolve <hostname1> <hostname2>... ");
            return;
        }

        for(String s : args) //for all listed host names
        {
            String hostName = s; //assign first host name
            try
            {
                InetAddress IP = InetAddress.getByName(hostName); //get IP from hostname
                System.out.println(hostName + " : " + IP.getHostAddress()); //display ip & name
            }
            catch(UnknownHostException e)
            {
                System.err.println(hostName + " : unknown host");
            }
        }
    }
}
