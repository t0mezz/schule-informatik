import client.MyClient;
import server.MyServer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // start client if --client argument is given
        if (args.length > 0 && args[0].equals("--client"))
            new MyClient();
        new MyServer();
    }
}