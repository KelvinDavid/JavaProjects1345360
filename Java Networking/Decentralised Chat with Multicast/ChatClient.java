import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.List;

public class ChatClient
{
    public static void main(String[] args)
    {
        try
        {
            //Create Chat room on address 239.0.202.1
            InetAddress address = InetAddress.getByName("239.0.202.1");
            MulticastSocket client = new MulticastSocket(40202);
            client.joinGroup(address);


            //Creates thread that listens to the Chat_room
            Thread listener = new Thread(new Listener(client));
            //Run listener
            listener.start();


            while (true) {
                //Read message input by the user
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String message = br.readLine();
                byte[] msg = message.getBytes();

                //Send written message to the Chat_room
                DatagramPacket packet = new DatagramPacket(msg, msg.length, address, 40202);
                client.send(packet);

            }
        }
        catch(Exception e)
        {
            System.err.println("Exception: " + e);
        }
    }
}
