class Komorka {
    int wiersz, kolumna;
    boolean blokowana = false;
    boolean wSciezce = false;
    double g, h;
    Komorka poprzednia;

    public Komorka(int wiersz, int kolumna) {
        this.wiersz = wiersz;
        this.kolumna = kolumna;
    }

    public double f() {
        return g + h;
    }
}