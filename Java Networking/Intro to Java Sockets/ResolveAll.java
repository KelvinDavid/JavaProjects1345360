//Kelvin David 1345360
//This class finds all the resolves of a given set of host addresses

import java.net.InetAddress;
import java.net.UnknownHostException;

class ResolveAll
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
		//gets all possible IPs for the given host and store it into a array
                InetAddress[] AllIPs = InetAddress.getAllByName(hostName);
		//print out all IP addresses
                for(InetAddress ina : AllIPs)
                {
                    System.out.println(hostName + " : " + ina.getHostAddress());
                }
            }
            catch(UnknownHostException e)
            {
                System.err.println(hostName + " : unknown host");
            }
        }
    }
}
