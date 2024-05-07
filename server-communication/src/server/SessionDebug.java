package server;

import java.io.*;
import java.net.Socket;

import basis.*;

/**
 * Die Klasse Session verarbeitet die Anfrage.
 *
 * @author Roland Stiebel
 */
public class SessionDebug extends Session {
    public SessionDebug(Socket socket) {
        super(socket);
    }

    public void processRequest(BufferedReader reader, PrintWriter writer) {
        try {
            // print all lines from input
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
