import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.nio.Buffer;
import java.rmi.server.ExportException;
import java.security.cert.X509Certificate;
import javax.naming.ldap.*;

public class MyTLSFileClient
{
    public static void main(String args[])
    {
        try
        {
            if(args.length != 3)
            {
                System.out.println("Usage: java MyTLSFileClient <host> <port> <filename>");
            }

            int port = Integer.parseInt(args[1]);
            InetAddress host = InetAddress.getByName(args[0]);
            String filename = args[2];

            SSLSocketFactory factory =
                    (SSLSocketFactory) SSLSocketFactory.getDefault();

            SSLSocket ssocket = (SSLSocket)factory.createSocket(host, port);

            SSLParameters params = new SSLParameters();
            params.setEndpointIdentificationAlgorithm("HTTPS");
            ssocket.setSSLParameters(params);

            ssocket.startHandshake();

            SSLSession sesh = ssocket.getSession();
            X509Certificate cert = (X509Certificate)
                    sesh.getPeerCertificates()[0];

            displayCert(cert);

            System.out.println("----------------------------------------------------");
            System.out.println("Connected to " + args[0] + " on port " + port);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ssocket.getOutputStream()));
            System.out.println("Sending file request for " + filename + "...");
            bw.write(filename + "\n");
            bw.flush();

            BufferedInputStream bis = new BufferedInputStream(ssocket.getInputStream());
            byte[] buf = new byte[1024];
            FileOutputStream fos = new FileOutputStream( "Copy.jpg");
            int rc;
            while ((rc = bis.read(buf)) > 0)
            {
                fos.write(buf, 0, rc);
            }
            System.out.println("File Received!");

            bw.close();
            fos.close();
            bis.close();

        }
        catch(Exception e)
        {
            System.out.println("Exception: " + e);
        }
    }

    private static void displayCert(X509Certificate cert) throws Exception
    {
        String name = cert.getSubjectX500Principal().getName();
        LdapName ln = new LdapName(name);
        for(Rdn rdn : ln.getRdns())
            System.out.println(rdn.getValue().toString());

    }
}
