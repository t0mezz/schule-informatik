package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import basis.*;

/**
 * Die Klasse MyClient ist ein Client fuer den Server
 * 
 * @author      Roland Stiebel
 */
public class MyClient extends Fenster implements KnopfLauscher
{
    /**
     * Die Portnummer
     */
    private int port = 42781;
    private String adresse = "localhost";

    public Knopf ende;
    public Knopf los;
    public TextFeld ein;

    public MyClient()
    {
        ende = new Knopf("Ende", 100,200,100,30);
        ende.setzeKnopfLauscher(this);
        los = new Knopf("sende", 100,100,100,30);
        los.setzeKnopfLauscher(this);
        ein = new TextFeld(100,150,100,30);

    }

    public void sende(String aufgabe)
    {
        try (
        Socket socket = new Socket(adresse, port);
        // vom Socket einen OutputStream beziehen
        OutputStream streamOut = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(streamOut, true);
        // vom Socket einen InputStream beziehen
        InputStream streamIn = socket.getInputStream();
        // Br�ckenklasse, um einen Character-Stream zu bekommen
        InputStreamReader streamReaderIn = new InputStreamReader(streamIn);
        // Character-Stream mit einer Instanz des BufferedReader verketten
        BufferedReader reader = new BufferedReader(streamReaderIn);)
        {
            writer.println("GET /index.html HTTP/1.1\r");
            writer.println("Host: "+adresse+" \r");
            writer.println("\r");
            writer.flush();

            String ergebnis = reader.readLine();        // hier holt er die Antwort
            while(ergebnis!=null)
            {
                System.out.println(aufgabe + " = " + ergebnis);
                //System.out.println(ergebnis);
                ergebnis = reader.readLine();
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void bearbeiteKnopfDruck(Knopf k)
    {
        if (k==ende) this.gibFrei();
        if (k==los) sende(ein.text());
    }

}
