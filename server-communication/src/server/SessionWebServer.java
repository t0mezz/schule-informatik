package server;

import java.io.*;
import java.net.Socket;

/**
 * Die Klasse Session verarbeitet die Anfrage.
 *
 * @author Roland Stiebel
 */
public class SessionWebServer extends Session {
    private String http_ok = "HTTP/1.1 200 OK\n" +
            "Accept-Ranges: bytes\n" +
            "Vary: Accept-Encoding\n" +
            "Content-Type: text/html\n" +
            "\n";

    public SessionWebServer(Socket socket) {
        super(socket);
    }

    private static String readHtmlFile(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public void processRequest(BufferedReader reader, PrintWriter writer) {
        writer.println(http_ok + readHtmlFile("ressources/index.html"));
    }
}