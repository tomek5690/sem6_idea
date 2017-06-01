package studia;

class Wspolrzedna implements Comparable{
    private int x, y;
    public Wspolrzedna(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){
        return "(" + x + ", " + y + ")";
    }
    public int compareTo(Object cmpObj) {
        return this.toString().compareTo(cmpObj.toString());
    }
    public boolean equals(Object obj){
        if (obj instanceof  Wspolrzedna) {
            Wspolrzedna wspl = (Wspolrzedna) obj;
            return (this.getX() == wspl.getX() && this.getY() == wspl.getY());
        }
        return false;
    }
    public int hashCode(){
        return this.getX() * this.getY();
    }
}