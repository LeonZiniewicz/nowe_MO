class Labirynt {
    int wiersze, kolumny;
    Komorka[][] siatka;
    Komorka start, cel;

    public Labirynt(int wiersze, int kolumny) {
        this.wiersze = wiersze;
        this.kolumny = kolumny;
        siatka = new Komorka[wiersze][kolumny];

        for (int i = 0; i < wiersze; i++) {
            for (int j = 0; j < kolumny; j++) {
                siatka[i][j] = new Komorka(i, j);
            }
        }

        start = siatka[0][0];
        cel = siatka[wiersze - 1][kolumny - 1];
    }

    public void generuj() {
        for (int i = 0; i < wiersze; i++) {
            for (int j = 0; j < kolumny; j++) {
                siatka[i][j].blokowana = false;
                siatka[i][j].wSciezce = false;
                siatka[i][j].g = 0;
                siatka[i][j].h = 0;
                siatka[i][j].poprzednia = null;

                if (Math.random() < 0.3 && (i != 0 || j != 0) && (i != wiersze - 1 || j != kolumny - 1)) {
                    siatka[i][j].blokowana = true;
                }
            }
        }
    }
}