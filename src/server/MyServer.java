package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Die Klasse MyServer ist einfacher Server
 * 
 * @author      Roland Stiebel
 *
 */
public class MyServer
{
    /**
     * Die Portnummer
     */
    private static int port = 42781;

    /**
     * startet den Server
     */
    public MyServer()
    {

        try (ServerSocket serverSocket = new ServerSocket(port);)
        {
            while (true)
            {
                System.out.println("Server wartet auf Anfragen ...");
                Socket socket = serverSocket.accept();
                System.out.println("Anfrage akzeptiert ...");

                SessionWebServer session = new SessionWebServer(socket);
                Thread sessionThread = new Thread(session);
                sessionThread.start();
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Diese Meldung wurde abgefangen und dann gedruckt");
        }
    }
}
