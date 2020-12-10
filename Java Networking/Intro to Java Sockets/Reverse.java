//Kelvin David 1345360
//This class does a reverse resolve and by getting the
//host name from a given address

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.lang.Byte;

class Reverse
{
    public static void main(String [] args)
    {
        if(args.length == 0)
        {
            System.err.println("Usage: java Reverse <ipAddress1> <ipAddress2>...");
            return;
        }
	//Repeat this for all the given addresses
        for(String s : args)
        {
            String hostAddress = s; //get current address
            String[] ip = hostAddress.split("\\."); //Divide string ip

            byte[] address = new byte[4]; //create byte array size 4
            for(int i = 0; i < 4; i++)
            {
                //convert string ip into byte array
                address[i] = (byte)Integer.parseInt(ip[i]);
            }
            try
            {
		//get IP address by given address
                InetAddress IP = InetAddress.getByAddress(address);
		//if the current address has no host name
                if(hostAddress.compareTo(IP.getHostName()) == 0)
                {
                    System.err.println(hostAddress + " : no name");
                }
		//if it does have a host name
                else
                {
                    System.out.println(hostAddress + " : " + IP.getHostName());
                }
            }
            catch(UnknownHostException e)
            {
                System.err.println(hostAddress + " : Unknown Address");
            }
        }

    }
}
