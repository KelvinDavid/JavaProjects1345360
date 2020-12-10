//Kelvin David 1345360
//This is a simple server that returns to the user a hello and its ip

import java.net.*;
import java.io.*;

public class server
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket server = new ServerSocket(0); //create server
        System.out.println("Waiting for connection on port " + server.getLocalPort());
        Socket client = server.accept(); //waits for a client to connect
        InetAddress cIP = client.getInetAddress(); //gets ip of connected client
        System.out.println(cIP.getHostName() + " connected!");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter
                (client.getOutputStream())); //create a writer
        writer.write("Hello " + cIP.getHostName() + " (uWu)"); //say hello
        writer.newLine();
        writer.write("Your IP is : " + cIP.getHostAddress() + " (oWo)"); //give ip
        writer.flush();
        client.close(); //disconnect client

    }
}
