import javax.net.ssl.*;
import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.naming.ldap.*;
import javax.net.*;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class MyTLSFileServer
{

    public static void main(String[] args)
    {
        try
        {
            int port = 40202;

            KeyStore ks = KeyStore.getInstance("JKS");
            char[] passphrase = "guest1".toCharArray();
            ks.load(new FileInputStream("server.jks"), passphrase);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, passphrase);

            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(kmf.getKeyManagers(), null, null);

            ServerSocketFactory ssf = ctx.getServerSocketFactory();
            SSLServerSocket sserverSocket = (SSLServerSocket) ssf.createServerSocket(port);
            String EnabledProtocols[] = {"TLSv1.2", "TLSv1.1"};
            sserverSocket.setEnabledProtocols(EnabledProtocols);

            System.out.println("File Server created on port - " + port);
            System.out.println("Waiting for connection...");

            SSLSocket sclient = (SSLSocket)sserverSocket.accept(); //blocking

            System.out.println(sclient.getLocalAddress() + " connected!");

            BufferedReader br = new BufferedReader(new InputStreamReader(sclient.getInputStream()));
            String fileR = br.readLine();

            System.out.println(fileR + " request received!");

            FileInputStream fis = new FileInputStream(fileR);
            byte[] buf = new byte[8192];
            BufferedOutputStream bos = new BufferedOutputStream(sclient.getOutputStream());
            int rc;
            System.out.println("Sending " + fileR);
            while((rc = fis.read(buf)) > 0)
            {
                bos.write(buf, 0, rc);
            }

            System.out.println(fileR + " sent back!");
            bos.close();
            fis.close();

        }
        catch(Exception e)
        {
            System.err.println("Exception: " + e);
        }
    }
}
