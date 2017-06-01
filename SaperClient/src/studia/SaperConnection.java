package studia;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class SaperConnection {
    private Socket socket;
    private InputStream stream;
    private PrintWriter out;
    private BufferedReader buffer;
    private String line;
    private boolean gameInProgress;
    private int openedBoxes = 0;

    SaperConnection(){
        try {
            socket = new Socket("127.0.0.1", 9696);
            stream = socket.getInputStream();
            buffer = new BufferedReader(new InputStreamReader(stream));
            out = new PrintWriter(socket.getOutputStream(), true);
            line = buffer.readLine();
            if (!(line.equals("OK.")))
                throw new ConnectException("Błąd połączenia z serwerem gry");
            gameInProgress = true;
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
    void sendRequest (String id) throws IOException{
        if (isGameOn());{
            out.println(id);
            line = buffer.readLine();
        }
    }
    private boolean isGameOn() {
            return (gameInProgress && openedBoxes<71 && !(line.equals("bum")));
    }

    public int getOpenedBoxes(){
        return openedBoxes;
    }
    public String getLine(){
        return line;
    }
}
