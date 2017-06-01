package studia;

/**
 * Created by lukas on 20.01.2017.
 */
public class FunkcjaLiniowa implements Wielomian {
    int a;
    int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public FunkcjaLiniowa(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String wypiszMiejscaZerowe(){
        double newA = this.a;
        double miejsce = -b/newA;
        return new String(""+miejsce);
    }
}
