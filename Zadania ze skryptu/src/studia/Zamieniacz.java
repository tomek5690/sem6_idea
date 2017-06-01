package studia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class Zamieniacz extends Thread {
    JTextArea okno;
    volatile boolean zakonczyc;
    public Zamieniacz(JTextArea comp) {
        okno = comp; zakonczyc = false;
    }
    public void run() {
        while (! zakonczyc) {
            try {
                String tekst = okno.getText();
                int indeks = tekst.indexOf("{");
                if (indeks >= 0) {
                    okno.replaceRange("begin", indeks, indeks+1);
                    okno.setCaretPosition(tekst.length()+4);
                } else {
                    indeks = tekst.indexOf("}");
                    if (indeks >=0) {
                        okno.replaceRange("end", indeks, indeks+1);
                        okno.setCaretPosition(tekst.length()+2);
                    }
                }
                sleep(2000);
            }
            catch (Exception e) {

            }
        }
    }
}

class NewJFrame extends JFrame {
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private Zamieniacz watek;
    private Cenzor cenzor;

    public NewJFrame() {
        initComponents();
        setSize(350, 250);
        watek = new Zamieniacz(jTextArea1);
        watek.start();
        cenzor = new Cenzor(jTextArea1);
        cenzor.start();
    }

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        jTextArea1.setPreferredSize(new Dimension(300, 200));
        jScrollPane1.setViewportView(jTextArea1);
        getContentPane().add(jScrollPane1, BorderLayout.CENTER);
        pack();
    }

    private void formWindowClosing(WindowEvent evt) {
        watek.zakonczyc = true;
        watek = null;
    }
}

class Cenzor extends Thread {
    JTextArea okno;
    volatile boolean zakonczyc;
    public Cenzor(JTextArea comp) {
        okno = comp;
        zakonczyc = false;
    }
    public void run() {
        while (! zakonczyc) {
            try {
                String tekst = okno.getText();
                int indeks = tekst.indexOf("cholera");
                if (indeks >= 0) {
                    JOptionPane.showMessageDialog(null, "Proszę nie używać takich brzydkich słów");
                }
                sleep(10000);
            }
            catch (Exception e) {

            }
        }
    }
}