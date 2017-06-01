package studia;

/**
 * Created by lukas on 24.01.2017.
 */
class Przepełnienie extends Exception{
   //niepotrzebne podwójne informowanie o wyjątku, zarówno w main jak i w klasie
   // public Przepełnienie() {
   //     System.out.println("Przepełnienie");
   // }
}

class Niedomiar extends Exception{

}


public class Kolejka {
    static final int N = 3;
    private Object[] tab;
    private int pocz, zaost, lbel;
    public Kolejka() {
        pocz=0;
        zaost=0;
        lbel=0;
        tab = new Object[N];
    }
    void doKolejki(Object el) throws Przepełnienie {
        if (tab.length == zaost)
            throw new Przepełnienie();
        tab[zaost] = el;
        zaost = (zaost+1);
        ++lbel;
    }

    Object zKolejki() throws Niedomiar {
        if (pocz >= lbel)
            throw new Niedomiar();
        int ind = pocz;
        pocz = (pocz+1);
        //--lbel;
        return tab[ind];
    }
}

