package server;

import java.io.*;
import java.net.Socket;
/**
 * Die Klasse Session verarbeitet die Anfrage.
 *
 * @author      Roland Stiebel
 */
public class Session implements Runnable
{
    private final Socket socket;

    public Session(Socket socket)
    {
        this.socket = socket;
    }

    public void run()
    {
        try 
        (
        InputStream streamIn = socket.getInputStream();                     // vom Socket einen InputStream beziehen
        InputStreamReader streamReaderIn = new InputStreamReader(streamIn); // Br�ckenklasse, um vom Byte-Stream zum Character-Stream zu kommen
        BufferedReader reader = new BufferedReader(streamReaderIn);         // und mit einem BufferedReader verketten
        OutputStream streamOut = socket.getOutputStream();                  // vom Socket einen OutputStream beziehen
        PrintWriter writer = new PrintWriter(streamOut, true);)             // mit einem CharacterStream verketten
        { 
            String anfrage = reader.readLine();

            while (anfrage != null)
            {

                // Anfrage mit der String-Methode split() in Tokens - Array of String - zerlegen:
                String[] tokens = anfrage.split(" ");

                // Variable, die das Ergebnis aufnehmen wird:
                Double ergebnis = null;
                String antwort = "";

                String cmd = "";
                double[] numbers = new double[255];
                int counter = 0;
                for(int i = 0; i < tokens.length; i++)
                {
                    try
                    {
                        numbers[counter] = Double.parseDouble(tokens[i]);
                        counter += 1;
                    }
                    catch(NumberFormatException e)
                    {
                        cmd = tokens[i];
                    }
                }

                if(cmd.equals("add") || cmd.equals("sum"))
                {
                    ergebnis = 0.0;
                    for(int i = 0; i < 255; i++)
                    {
                        if(numbers[i] == 0)
                        {
                            break;
                        }
                        ergebnis+=numbers[i];
                    }
                }
                else if(cmd.equals("sub") || cmd.equals("dif"))
                {
                    ergebnis = 0.0;
                    for(int i = 0; i < 255; i++)
                    {
                        if(numbers[i] == 0)
                        {
                            break;
                        }
                        ergebnis-=numbers[i];
                    }
                }
                else if(cmd.equals("mul"))
                {
                    ergebnis = 0.0;
                    for(int i = 0; i < 255; i++)
                    {
                        if(numbers[i] == 0)
                        {
                            break;
                        }
                        ergebnis*=numbers[i];
                    }
                }
                else if(cmd.equals("div"))
                {
                    ergebnis = 0.0;
                    for(int i = 0; i < 255; i++)
                    {
                        if(numbers[i] == 0)
                        {
                            break;
                        }
                        ergebnis/=numbers[i];
                    }
                }
                else
                {
                    writer.print("Error: Keine passenden argumente gefunden: ");
                    for(int i = 0; i < tokens.length; i++)
                    {
                        writer.print(tokens[i] + " ");
                    }
                    writer.println();
                }
                
                // Antwort generieren: 
                if(ergebnis != null)
                {
                    antwort = String.valueOf(ergebnis);
                }
                
                // an Client uebergeben: 
                writer.println(antwort);

                //...und auf der Konsole ausgegeben
                System.out.println(anfrage +" liefert "+antwort);  

                anfrage = reader.readLine();
            }
            
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
