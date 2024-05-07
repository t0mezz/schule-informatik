package server;

import java.io.*;
import java.net.Socket;

/**
 * Die Klasse Session verarbeitet die Anfrage.
 *
 * @author Roland Stiebel
 * @author acc4t
 */
public abstract class Session implements Runnable {
    private final Socket socket;

    public Session(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
                InputStream streamIn = socket.getInputStream();                     // vom Socket einen InputStream beziehen
                InputStreamReader streamReaderIn = new InputStreamReader(streamIn); // Br�ckenklasse, um vom Byte-Stream zum Character-Stream zu kommen
                BufferedReader reader = new BufferedReader(streamReaderIn);         // und mit einem BufferedReader verketten
                OutputStream streamOut = socket.getOutputStream();                  // vom Socket einen OutputStream beziehen
                PrintWriter writer = new PrintWriter(streamOut, true);
        )       // mit einem CharacterStream verketten
        {
            // do something with the request
            processRequest(reader, writer);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public abstract void processRequest(BufferedReader reader, PrintWriter writer);
}
