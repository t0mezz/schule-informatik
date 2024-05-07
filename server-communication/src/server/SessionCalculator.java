package server;

import java.io.*;
import java.net.Socket;

/**
 * Die Klasse Session verarbeitet die Anfrage.
 *
 * @author Roland Stiebel
 */
public class SessionCalculator extends Session {

    public SessionCalculator(Socket socket) {
        super(socket);
    }

    public void processRequest(BufferedReader reader, PrintWriter writer) {
        try
        {
            String anfrage = reader.readLine();

            while (anfrage != null) {

                // Anfrage mit der String-Methode split() in Tokens - Array of String - zerlegen:
                String[] tokens = anfrage.split(" ");

                // Variable, die das Ergebnis aufnehmen wird:

                // split the command and the numbers from the input
                String[] cmd = new String[255];
                double[] numbers = new double[255];
                int c_numbers = 0;
                int c_cmd = 0;

                for (int i = 0; i < tokens.length; i++) {
                    try {
                        numbers[c_numbers] = Double.parseDouble(tokens[i]);
                        c_numbers += 1;
                    } catch (NumberFormatException e) {
                        cmd[c_cmd] = tokens[i];
                        c_cmd += 1;
                    }
                }

                String message = "";
                for (int i = 0; i < c_cmd; i++) {
                    message = parse_command(cmd[i], numbers, tokens);
                    if (!message.startsWith("E")) {
                        break;
                    }
                }

                // an Client uebergeben:
                writer.println(message);

                anfrage = reader.readLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String parse_command(String cmd, double[] numbers, String[] tokens) {
        double result = 0.0;
        String message = "";
        if (cmd.equals("add") || cmd.equals("sum")) {
            for (int i = 0; i < 255; i++) {
                if (numbers[i] == 0) {
                    break;
                }
                result += numbers[i];
            }
            message = Double.toString(result);
        } else if (cmd.equals("sub") || cmd.equals("dif")) {
            for (int i = 0; i < 255; i++) {
                if (numbers[i] == 0) {
                    break;
                }
                result -= numbers[i];
            }
            message = Double.toString(result);
        } else {
            message = "Error: Command not found type 'help' for help: ";
            for (int i = 0; i < tokens.length; i++) {
                message += tokens[i] + " ";
            }
        }
        return message;
    }
}
