package studia;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

import static javax.swing.JOptionPane.showInputDialog;

public class Main {
    public static void main(String[] args) {
        //Wspólna klasa main dla wszystkich, aby sprawdzić zadanie odkomentować i odpalić TGA

        //zadanie1_1(); //komunikat, podaj stringa małe do upperCase
        //zadanie1_2(); //silnia na bigint multiply  substract
        //zadanie1_3(); //Sumowanie nieparzystych 1 do n, n parzyste kończymy na n-1, n nieparzyste koniec na n
        //System.out.println(zadanie1_4(123, 0)); // Konwerter Tryby 0- szesnastkowy, 1-binarny, 2-ósemkowy, zrealizowane metodami dla integer
        //zadanie2(); //klasa rational z działaniami, funkcja kwadratowa, wielomian, funkcja liniowa
        //zadanie3_1(); //niedomiar i przepełnienie
        //zadanie3_2(); //obsługa dzielenia przez zero
        zadanie4_1(); //edytor tekstu z różnym kodowaniem
        //zadanie4_2(); //kompresor i dekompresor
        //zadanie5_1(); //współrzędna i wypisanie kolejności od -1 do -nieskończoności i od 0 do nieskończoności
        //zadanie5_2(); //hashmap i współrzędne punktów
        //zadanie5_3();// graf przypisanie poziomu do tablicy
        //zadanie6_1(); //dorobienie wykrywanie cholera w sleep 10000 oknie
        //zadanie6_2(); // wątki komunikaty
        //zadanie7_1(args); //liczba sekund z serwera
        //zadanie7_2 - klient serwer - saper - osobno, włączyć serwer, włączyć klienta.
    }

    static void zadanie1_1(){
        String input = showInputDialog("Podaj łańcuch");
        String output = input.toUpperCase();
        JOptionPane.showMessageDialog(null, output);
        System.exit(0);
    }
    static void zadanie1_2(){
        BigInteger silnia = BigInteger.ONE;
        BigInteger n = new BigInteger(JOptionPane.showInputDialog("Podaj silnie")); //Konwersja podanego argumentu do liczby całkowitej

        while (n.compareTo(BigInteger.ONE)>0) {
            silnia = silnia.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }
        JOptionPane.showMessageDialog(null, silnia.toString());
        System.exit(0);
    }
    static void zadanie1_3(){
        int n = Integer.parseInt(JOptionPane.showInputDialog("Podaj liczbę"))+1;
        int sum = 0;
        for (int i=1; i<n; i++){
            if(i%2==0)
                continue;
            else
                sum+=i;
        }
        JOptionPane.showMessageDialog(null, ""+sum);

    }
    static String zadanie1_4(int n, int mode){
        switch (mode){
            case 0:
                return Integer.toHexString(n);
            case 1:
                return Integer.toBinaryString(n);
            case 2:
                return Integer.toOctalString(n);
        }
        return null;
    }
    static void zadanie2(){
        Wielomian w[] = new Wielomian[3];
        w[0] = new FunkcjaLiniowa(2, 1);
        w[1] = new FunkcjaKwadratowa(1, -2, 2);
        w[2] = new FunkcjaKwadratowa(1, 0, -1);
        for (int i=0; i<3; i++){
            System.out.println(w[i].wypiszMiejscaZerowe());
        }
    }
    static void zadanie3_1(){
        Kolejka k = new Kolejka();
        try {
        k.doKolejki(new Integer(8));
        //k.doKolejki(new String("Ala ma kota"));
        //k.doKolejki(new Double(3.14654));
            for (int i=1; i<4; ++i){
                System.out.println((k.zKolejki()).toString());
            }}
        catch (Przepełnienie ex){
            System.out.println("Przepełnienie!");
        }
        catch (Niedomiar ex){
            System.out.println("Niedomiar!");
        }

    }
    static void zadanie3_2(){
        Wyrazenie wyr = new Wyrazenie("(3*((2/0)-1))");
        int wynik = wyr.oblicz();
        System.out.println(wynik);
    }
    static void zadanie4_1(){
        SimpleTextEditorBase edytor = new SimpleTextEditorBase();
        edytor.putSomeTextAndSave();
    }
    static void zadanie4_2(){

        GZIPCompesser compesser = new GZIPCompesser();
        compesser.zipFiles("D:\\Tomek\\Studia\\Semestr 6\\Java zaawansowana\\text.txt" , "D:\\Tomek\\Studia\\Semestr 6\\Java zaawansowana\\text.gz");
        compesser.unzipFiles("D:\\Tomek\\Studia\\Semestr 6\\Java zaawansowana\\text2.txt" , "D:\\Tomek\\Studia\\Semestr 6\\Java zaawansowana\\text.gz");

    }
    static void zadanie5_1(){
        //inaczej niż w skrypcie - najpierw od -1 do nieskończoności, później od 0 do nieskończoności
        // leksykograficznie
        TreeSet zbior = new TreeSet();
        zbior.add( new Wspolrzedna(2, 3) );
        zbior.add( new Wspolrzedna(-3, 0) );
        zbior.add( new Wspolrzedna(-1, 2) );
        zbior.add( new Wspolrzedna(-1, 2) );
        zbior.add( new Wspolrzedna(-3, -2) );
        zbior.add( new Wspolrzedna(5, 2) );
        Iterator it = zbior.iterator();
        while (it.hasNext()){
            System.out.println((it.next()).toString());
        }
    }
    static void zadanie5_2(){
        HashMap mapa = new HashMap();
        mapa.put(new Wspolrzedna(2, 3), new String("czerwony"));
        mapa.put(new Wspolrzedna(-3, 0), new String("czarny"));
        mapa.put(new Wspolrzedna(-1, 2), new String("czerwony"));
        mapa.put(new Wspolrzedna(2, -1), new String("czarny"));
        Wspolrzedna w = new Wspolrzedna(-3, 0);
        System.out.println("Punkt " + w.toString() + " ma kolor " + mapa.get(w));
    }
    static void zadanie5_3(){
        Graf g = new Graf("5, (0,1), (1,2), (3,0), (1,3), (0,5), (4,9)");
        System.out.println(g.toString());
    }
    static void zadanie6_1(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
    static void zadanie6_2(){
        KolejkaKomunikatow k = new KolejkaKomunikatow();
        Watek w1 = new Watek(k, 1);
        Watek w2 = new Watek(k, 2);
        w1.start(); w2.start();
        try {
            w1.join();
            w2.join();
        } catch (InterruptedException e) {

        }
        String msg = k.odbierz();
        while (!k.isEmpty()) {
            if (msg != null)
                System.out.println(msg);
            msg = k.odbierz();
        }
    }
    public static void zadanie7_1(String []args){
        String nazwahosta;
        if (args.length > 0) {
            nazwahosta = args[0];
        } else {
            nazwahosta = "time-c.nist.gov";
        }
        try {
            Socket gniazdo = new Socket(nazwahosta, 37);
            InputStream strumien = gniazdo.getInputStream();
            BufferedReader bufor = new BufferedReader( new InputStreamReader(strumien));
            long number=0;
            for(int i = 0; i<4; ++i) {
                int temp = strumien.read();
                number = number | (long) temp;
                number= number << 8;

            }
            number = number >> 8;
            System.out.println("Liczba sekund to: " + number);
        }
        catch (UnknownHostException e) {
            System.err.println(e);
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}
