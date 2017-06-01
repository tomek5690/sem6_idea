package studia;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class Graf {
    private int n;
    // liczba wierzchołków, V = {0,1,...,n-1}
    private LinkedList<Integer>[] tab; // tablica wierzchołków połączonych z danym wierzcholkiem
    public Graf(String lan) {
        StringTokenizer st = new StringTokenizer(lan, "() ,");
        n = Integer.parseInt(st.nextToken());
        tab = new LinkedList[n];
        for (int i = 0; i<n; ++i)
            tab[i] = new LinkedList();
        while (st.hasMoreTokens()) {
            tab[Integer.parseInt(st.nextToken())].add( new Integer(st.nextToken()));
        }
    } public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (LinkedList<Integer> list : tab) {
            sb.append(i + ": ");
            for (Integer target : list)
                sb.append(target.toString() + " ");
            sb.append("\n");
            i++;
        }
        return sb.toString();
    }
}
