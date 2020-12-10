import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Listener implements Runnable
{
    private MulticastSocket _socket;

    public Listener(MulticastSocket socket)
    {
        _socket = socket;
    }

    public void run()
    {
        try
        {
            //Reade received message packet
            byte[] buf = new byte[1024];
            DatagramPacket received = new DatagramPacket(buf, buf.length);

            while(true)
            {
                _socket.receive(received);
                buf = received.getData();
                //System.out.println("buf length" + received.getLength());
                System.out.println(received.getAddress() + ": " + new String(buf, 0,received.getLength()));
            }
        }
        catch(Exception e)
        {
            System.err.println("Exception: "  + e);
        }
    }
}
