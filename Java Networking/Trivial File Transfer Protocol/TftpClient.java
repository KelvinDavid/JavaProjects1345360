import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class TftpClient
{
    private String filename;
    private InetAddress address;
    private int port;
    private byte curBlock = 1;
    private int ACKIn = 0;
    private SocketAddress SocketIP;
    private DatagramSocket clientDs;
    private FileOutputStream fos;

    public static void main(String[] args) throws IOException
    {
        TftpClient client = new TftpClient();
        if(client.argsValid(args))
        {
            client.parseArgs(args);
            client.runClient();
        }
    }

    private void runClient()
    {
        try
        {
            clientDs = new DatagramSocket();

            byte[] buf = filename.getBytes();
            int rc = buf.length;
            fos = new FileOutputStream(new File("TestRecieved.txt"));

            DatagramPacket dp = new DatagramPacket(buf, rc, address, port);
            SocketIP = dp.getSocketAddress();

            //Send RQQ
            DatagramPacket RQQ = TftpUtil.packRRQDatagramPacket(buf);
            RQQ.setSocketAddress(SocketIP);
            System.out.println("RQQ sent...");
            clientDs.send(RQQ);
            //

            //Get first data packet
            byte[] buf2 = new byte[1427];
            DatagramPacket recieveDp = new DatagramPacket(buf2, 1427);
            clientDs.receive(recieveDp);
            //

            //Do until EOF
            while(true)
            {
                System.out.println("Packet recieved [type " + TftpUtil.checkPacketType(recieveDp) + "]");

                //If received error
                if(TftpUtil.checkPacketType(recieveDp) == TftpUtil.ERROR)
                {
                    System.out.println("Not type " + TftpUtil.DATA);
                }
                //

                //If received Data
                if(TftpUtil.checkPacketType(recieveDp) == TftpUtil.DATA)
                {
                    byte blockNum = TftpUtil.extractBlockSeq(recieveDp);
                    System.out.println("----> Expected Block Number: " + curBlock);
                    System.out.println("----> Recieved Block Number: " + blockNum);


                    if(blockNum == curBlock && recieveDp.getLength() == TftpUtil.PACKET_BUFFER_SIZE)
                    {
                        fos.write(recieveDp.getData(), 2, recieveDp.getLength()-2);
                        //Create ACK
                        int block = TftpUtil.extractACKNumber(recieveDp);
                        //block++;
                        byte[] ack = new byte[2];
                        ack[0] = TftpUtil.ACK;
                        ack[1] = (byte)block;
                        DatagramPacket ACKPK = new DatagramPacket(ack, 2);
                        ACKPK.setSocketAddress(recieveDp.getSocketAddress());

                        //Send ACK
                        clientDs.setSoTimeout(1000);
                        clientDs.send(ACKPK);

                        try
                        {
                            clientDs.receive(recieveDp);
                            System.out.println("ACK #" + TftpUtil.extractACKNumber(ACKPK) + " sent...");
                            curBlock++;
                        }
                        catch(SocketTimeoutException ste)
                        {
                            clientDs.send(ACKPK);
                            System.out.println("timeout #" + TftpUtil.extractACKNumber(ACKPK));
                        }
                    }
                    else
                        {
                            //Create ACK
                            int block = TftpUtil.extractACKNumber(recieveDp);
                            //block++;
                            byte[] ack = new byte[2];
                            ack[0] = TftpUtil.ACK;
                            ack[1] = (byte)block;
                            DatagramPacket ACKPK = new DatagramPacket(ack, 2);
                            ACKPK.setSocketAddress(recieveDp.getSocketAddress());

                            //Send ACK
                            clientDs.setSoTimeout(1000);
                            clientDs.send(ACKPK);

                            try
                            {
                                clientDs.receive(recieveDp);
                                curBlock++;
                            }
                            catch(SocketTimeoutException ste)
                            {
                                clientDs.send(ACKPK);

                            }
                            break;
                        }
                }
                //
            }
            System.out.println("+++++++++++++++++++++++++++++++++++++++++");
            System.out.println(filename + " received uWu");

        }
        catch(FileNotFoundException nf)
        {
            System.err.println("FileNotFound: " + nf);
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe);
        }
        catch(Exception e)
        {
            System.err.println("Exception: " + e);
        }
    }


    private void parseArgs(String[] args) throws IOException
    {
        address = InetAddress.getByName(args[0]);
        port = Integer.parseInt(args[1]);
        filename = args[2];
        System.out.println("Address: " + address.getHostAddress());
        System.out.println("Port: " + port);
        System.out.println("Filename: " + filename);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    private boolean argsValid(String[] args)
    {
        if(args.length == 3)
        {
            return true;
        }
        return false;
    }
}
