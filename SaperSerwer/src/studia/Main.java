package studia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.*;
class Gracz implements Runnable {
    private int plansza[][]; // liczba >= 100 to mina, 0, 1, ..., 8 -- ile wokól min
    private boolean klikniete[][];
    private PrintWriter out;
    private BufferedReader in;
    private Socket polaczenie;
    private void InicjujPlansze() {
        plansza = new int[11][11];
        klikniete = new boolean[11][11];
        int w, k, licznik;
        licznik = 0;
        while (licznik < 10) {
            w = (int)(Math.random()*9) + 1;
            k = (int)(Math.random()*9) + 1;
            if (plansza[w][k] < 100) {
                ++licznik; plansza[w][k] = 100;
                ++plansza[w-1][k-1];
                ++plansza[w-1][k];
                ++plansza[w-1][k+1];
                ++plansza[w][k-1];
                ++plansza[w][k+1];
                ++plansza[w+1][k-1];
                ++plansza[w+1][k];
                ++plansza[w+1][k+1];
            }
        }
    }
    public Gracz(Socket polaczenie) {
        InicjujPlansze();
        this.polaczenie = polaczenie;
        try {
            out = new PrintWriter(polaczenie.getOutputStream(), true);
            in = new BufferedReader( new InputStreamReader(polaczenie.getInputStream()));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    public void run() {
        int w, k, odkryte, liczba;
        String wsp, odp;
        boolean koniec = false;
        odkryte=0;
        try {
            out.println("OK.");
            while ((!koniec) && (odkryte<71)) {
                wsp = in.readLine();
                if (wsp == null)
                    koniec = true;
                else {
                    try {
                        liczba = Integer.parseInt(wsp);
                        w = (int)((liczba-1)/9) + 1;
                        k = (liczba-1) % 9 + 1;
                    } catch (NumberFormatException e) {
                        w = 200; k = 200;
                    }
                    if ((w>=1) && (w<=9) && (k>=1) && (k<=9)) {
                        if (plansza[w][k] >= 100) {
                            out.println("bum");
                            koniec = true;
                        } else {
                            out.println(Integer.toString(plansza[w][k]));
                            if (!klikniete[w][k])
                                ++odkryte;
                        }
                        klikniete[w][k] = true;
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
        finally {
            try {
                polaczenie.close();
            }
            catch (IOException e) {
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        ServerSocket server;
        try {
            server = new ServerSocket(9696);
            while(true) {
                Socket polaczenie = server.accept();
                Thread t = new Thread( new Gracz(polaczenie));
                t.start();
            }
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}