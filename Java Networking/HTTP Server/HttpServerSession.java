import java.net.*;
import java.io.*;
import java.lang.Thread.*;

public class HttpServerSession implements Runnable
{
    private Socket client;
    private String request;
    private String filename;
    private String version;

    public HttpServerSession(Socket cc)
    {
        client = cc;
    }

    private void writeln(BufferedOutputStream bos, String s) throws IOException
    {
        String news = s + "\r\n";
        byte[] array = news.getBytes();
        for(int i=0; i<array.length; i++)
        {
            bos.write(array[i]);
        }
        bos.flush();
        return;
    }

    public void run()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String line = reader.readLine();

            request = line;
            String parts[] = request.split(" ");
            if(parts.length != 3 || parts[0].compareTo("GET") != 0)
            {
                System.out.println("ERROR : Invalid Request");
            }
            else
                {
                    filename = parts[1].substring(1);
                    version = parts[2];
                    if (filename.compareTo("") == 0)
                    {
                        filename = "HttpServer.java";
                    }
                    try
                    {
                        FileInputStream fis = new FileInputStream(filename);
                        writeln(new BufferedOutputStream(client.getOutputStream()), version + "200" + " OK");
                        writeln(new BufferedOutputStream(client.getOutputStream()), "");

                        byte[] buf = new byte[1024];
                        BufferedOutputStream bos = new BufferedOutputStream(client.getOutputStream());

                        int rc = fis.read(buf);
                        while(rc >= 0)
                        {
                            Thread.sleep(1000); //Simulate slow connection
                            bos.write(buf, 0, rc);
                            bos.flush();
                            rc = fis.read(buf);
                        }
                        bos.close();
                        fis.close();
                    }
                    catch(FileNotFoundException fe)
                    {
                        writeln(new BufferedOutputStream(client.getOutputStream()), version + "404" + " File Not Found");
                    }
                }
        }
        catch(Exception e)
        {
            System.err.println("Exception: " + e);
        }
    }
}
