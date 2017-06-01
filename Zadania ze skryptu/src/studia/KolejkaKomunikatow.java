package studia;
import java.util.*;
class KolejkaKomunikatow {
    HashMap<Integer, String> kolejka = new HashMap<>();
    public synchronized void wyslij(String msg, Integer key) {
        kolejka.put(key, msg);
    }
    public synchronized String odbierz() {
        if (kolejka.size() == 0)
            return null;
        for (int i=0; i<100; i++)
            if (kolejka.containsKey(new Integer(i))){
                String msg = kolejka.get(new Integer(i));
                kolejka.remove(new Integer(i));
                return msg;
            }
        return null;
    }
    public synchronized boolean isEmpty(){
        return (kolejka.size() == 0);
    }
}
class Watek extends Thread {
    private KolejkaKomunikatow koko;
    private int istart;
    public Watek(KolejkaKomunikatow kk, int pocz) {
        koko = kk;
        istart = pocz;
    }
    public void run() {
        try {
            for (int i=istart; i<=16; i+=2) {
                koko.wyslij("Komunikat"+i, new Integer(i));
                Thread.sleep(50);
            }
        } catch (InterruptedException e)
        {

        }
    }
}