package studia;

public class DrzewoBinarneDzialania {
}
    abstract class Wierzcholek {
        Wierzcholek lewy, prawy;
        public abstract int wartosc();
    }
    class Stala extends Wierzcholek {
        private int wart;
        public Stala(int x) {
            wart = x;
        }
        public int wartosc() {
            return wart;
        }
    }
    class Dzialanie extends Wierzcholek {
        private char op; // operator +, -, / lub *
        public Dzialanie(char znak) {
            op = znak;
        }
        public void dodajLewyArg(Wierzcholek arg) {
        lewy = arg;
        }
        public void dodajPrawyArg(Wierzcholek arg) {
            prawy = arg;
        }
        public int wartosc() {
            switch (op) {
                case '+':
                    return lewy.wartosc() + prawy.wartosc();
                case '-':
                    return lewy.wartosc() - prawy.wartosc();
                case '/':
                    //if (prawy.wartosc() == 0)
                    return lewy.wartosc() / prawy.wartosc();
                case '*':
                    return lewy.wartosc() * prawy.wartosc();
            } return 0;
        }
    }
    class Wyrazenie {
        private Wierzcholek korzen;
        private Wierzcholek utworzDrzewo(String w, int p, int q) {
            if (p == q)
                return new Stala(Character.digit(w.charAt(p), 10));
            else {
                int i = p+1, nawiasy = 0;
                while ( (nawiasy != 0) || (w.charAt(i) == '(' || (w.charAt(i) == ')') || (Character.isDigit(w.charAt(i))))) {
                    if (w.charAt(i) == '(')
                        ++nawiasy;
                    if (w.charAt(i) == ')')
                        --nawiasy;
                    ++i;
                }
                    Dzialanie nowy = new Dzialanie(w.charAt(i));
                    nowy.dodajLewyArg(utworzDrzewo(w, p+1, i-1));
                    nowy.dodajPrawyArg(utworzDrzewo(w, i+1, q-1));
                    return nowy;
            }
        }
        public Wyrazenie(String w) {
            korzen = utworzDrzewo(w, 0, w.length()-1);
        }
        public int oblicz() {
            return korzen.wartosc();
        }
    }