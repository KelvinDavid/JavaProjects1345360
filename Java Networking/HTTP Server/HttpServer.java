import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer
{
    private static final ExecutorService exec = Executors.newFixedThreadPool(10);

    public static void main(String main[]) throws IOException
    {
        ServerSocket server = new ServerSocket(3025);
        System.out.println("Server Open [port 3025]");
        System.out.println("---------------------------");
        while(true)
        {
            Socket client = server.accept();

            HttpServerSession task = new HttpServerSession(client);
            exec.execute(task);
        }
    }
}
