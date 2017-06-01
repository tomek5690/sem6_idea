package studia;

public class FunkcjaKwadratowa implements Wielomian {
    int a;
    int b;
    int c;

    FunkcjaKwadratowa(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public String wypiszMiejscaZerowe(){
        double delta = this.getDelta();
        if (delta < 0)
            return "brak";
        else if (delta == 0)
            return "" + -b/(2*a);
        else {
            double pierwiastekZDelty = Math.sqrt(delta);
            return "" + (-b-pierwiastekZDelty)/(2*a) + " ," +(-b+pierwiastekZDelty)/(2*a);
        }
    }
    public int getValue(int x){
        return ((a*x*x) + (b*x) + c);
    }
    private double getDelta(){
        return (b*b) - (4*a*c);
    }
    public int getLiczbaPierwiastkow(){
        double delta = this.getDelta();
        if (delta < 0 ) return 0;
        else if (delta == 0) return 1;
        else return 2;
    }
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

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

}
