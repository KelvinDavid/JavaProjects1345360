//Kelvin David 1345360
//This is a simple client that connects to my simple server

import java.net.*;
import java.io.*;

public class client
{
    public static void main(String[] args) throws IOException
    {
        if(args.length != 2) //checks if the correct amount of entries
        {
            System.out.println("Usage: java client <hostname> <port>");
            return;
        }
        String hostName = args[0]; //get hostname
        int port = Integer.parseInt(args[1]); //get port number
        InetAddress ip = InetAddress.getByName(hostName); //get ip of hostname

        Socket client = new Socket(ip, port); //connect to the port as it's own ip

        InputStreamReader in = new InputStreamReader(client.getInputStream()); //create reader
        BufferedReader br = new BufferedReader(in);

        String str = br.readLine(); //read line from server

	//display server
        System.out.println("Server: " + str);
        str = br.readLine();
        System.out.println("Server: " + str);

    }
}
