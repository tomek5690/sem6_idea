package studia;

public class Rational {
    private int licznik;
    private int mianownik;
    Rational(int licznik, int mianownik){
        if (mianownik== 0) {
            throw new RuntimeException("Mianownik jest zerem");
        }
        int g = gcd(licznik, mianownik);
        this.licznik = licznik   / g;
        this.mianownik= mianownik / g;
    }
    public int getLicznik(){
        return this.licznik;
    }
    public int getMianownik(){
        return this.mianownik;
    }
    public Rational add(Rational arg){
        int sumL = (this.getLicznik() * arg.getMianownik()) + (this.getMianownik() * arg.getLicznik());;
        int sumM= this.getMianownik() * arg.getMianownik();
        return new Rational(sumL, sumM);
    }
    public Rational mul(Rational arg){
        return new Rational(this.getLicznik() * arg.getLicznik(), this.getMianownik() * arg.getMianownik());
    }
    public Rational sub(Rational arg){
        int sumL = (this.getLicznik() * arg.getMianownik()) - (this.getMianownik() * arg.getLicznik());;
        int sumM= this.getMianownik() * arg.getMianownik();
        return new Rational(sumL, sumM);
    }
    public Rational div(Rational arg){
        return this.mul(arg.odwroc());
    }
    public boolean equals(Rational arg){
        return (this.getMianownik() == arg.getMianownik() && this.getLicznik() == arg.getLicznik());
    }
    public int compareTo(Rational arg){
        if (this.getMianownik() == arg.getMianownik())
            if (this.getLicznik() > arg.getLicznik()) return 1;
            else if (this.getLicznik() < arg.getLicznik()) return -1;

        return 0;
    }
    public String toString(){

        if (this.getMianownik() == 1) return this.getLicznik() + "";
        else    return this.getLicznik() + "/" + this.getMianownik();
    }
    private static int gcd(int m, int n) {
        if (0 == n) return m;
        else return gcd(n, m % n);
    }
    private  Rational odwroc(){
        return new Rational(this.getMianownik(), this.getLicznik());
    }
}
