package server;

import java.io.*;
import java.net.Socket;

/**
 * Die Klasse Session verarbeitet die Anfrage.
 *
 * @author Roland Stiebel
 */
public class SessionWebServer extends Session {
    private String html = "HTTP/1.1 200 OK\n" +
            "Accept-Ranges: bytes\n" +
            "Vary: Accept-Encoding\n" +
            "Content-Type: text/html\n" +
            "\n" +
            "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Finner und seine Chatbots</title>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: Arial, sans-serif;\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "            background-color: #f5f5f5;\n" +
            "            color: #333;\n" +
            "        }\n" +
            "\n" +
            "        .container {\n" +
            "            max-width: 800px;\n" +
            "            margin: 0 auto;\n" +
            "            padding: 40px;\n" +
            "            text-align: center;\n" +
            "        }\n" +
            "\n" +
            "        h1 {\n" +
            "            font-size: 36px;\n" +
            "            font-weight: bold;\n" +
            "            margin-bottom: 20px;\n" +
            "        }\n" +
            "\n" +
            "        .poem {\n" +
            "            font-style: italic;\n" +
            "            line-height: 1.6;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"container\">\n" +
            "        <h1>Finner und seine Chatbots</h1>\n" +
            "        <div class=\"poem\">\n" +
            "            <p>Finner, der kleine Mann, so fein,</p>\n" +
            "            <p>Liebt es, mit Chatbots zu arbeiten.</p>\n" +
            "            <p>Auf ihre Hilfe kann er bauen,</p>\n" +
            "            <p>Wenn neue Programme entstehen.</p>\n" +
            "            <br>\n" +
            "            <p>Mit Eiergerichten gut versorgt,</p>\n" +
            "            <p>Sitzt er am Rechner, stets geborgen.</p>\n" +
            "            <p>Die Bots erleichtern ihm die Müh',</p>\n" +
            "            <p>Und sein Codingtraum geht in Erfüllung.</p>\n" +
            "        </div>\n" +
            "    <img src='https://cdn.discordapp.com/attachments/864937515876876309/1154125451313299466/425ad83f4d89731c569bdbaf87a0344a.png?ex=663b1ed6&is=6639cd56&hm=649ed3851b81bdd6f23bc1ca10ecd541c4294294d435627c841c3f1caf514534&'>" +
            "    </div>\n" +
            "</body>\n" +
            "</html>";

    public SessionWebServer(Socket socket) {
        super(socket);
    }

    public void processRequest(BufferedReader reader, PrintWriter writer) {
        writer.println(html);
    }
}