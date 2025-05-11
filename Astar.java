import java.util.*;

class AStar {
    Labirynt labirynt;
    PriorityQueue<Komorka> otwarte;
    boolean[][] zamkniete;

    public AStar(Labirynt labirynt) {
        this.labirynt = labirynt;
        otwarte = new PriorityQueue<>(Comparator.comparingDouble(Komorka::f));
        zamkniete = new boolean[labirynt.wiersze][labirynt.kolumny];
    }

    public void szukajSciezki() {
        Komorka start = labirynt.start;
        Komorka cel = labirynt.cel;

        start.g = 0;
        start.h = heurystyka(start, cel);
        otwarte.add(start);

        while (!otwarte.isEmpty()) {
            Komorka aktualna = otwarte.poll();
            if (aktualna == cel) {
                odtworzSciezke(cel);
                return;
            }

            zamkniete[aktualna.wiersz][aktualna.kolumna] = true;

            for (Komorka sasiad : sasiedzi(aktualna)) {
                if (zamkniete[sasiad.wiersz][sasiad.kolumna] || sasiad.blokowana)
                    continue;

                double nowyG = aktualna.g + 1;
                boolean lepszaSciezka = nowyG < sasiad.g || sasiad.g == 0;

                if (lepszaSciezka) {
                    sasiad.poprzednia = aktualna;
                    sasiad.g = nowyG;
                    sasiad.h = heurystyka(sasiad, cel);
                    otwarte.add(sasiad);
                }
            }
        }
    }

    private double heurystyka(Komorka a, Komorka b) {
        return Math.abs(a.wiersz - b.wiersz) + Math.abs(a.kolumna - b.kolumna);
    }

    private void odtworzSciezke(Komorka cel) {
        Komorka aktualna = cel;
        while (aktualna != null) {
            aktualna.wSciezce = true;
            aktualna = aktualna.poprzednia;
        }
    }

    private List<Komorka> sasiedzi(Komorka k) {
        List<Komorka> lista = new ArrayList<>();
        int[][] kierunki = {{-1,0},{1,0},{0,-1},{0,1}};
        for (int[] d : kierunki) {
            int ni = k.wiersz + d[0];
            int nj = k.kolumna + d[1];
            if (ni >= 0 && nj >= 0 && ni < labirynt.wiersze && nj < labirynt.kolumny)
                lista.add(labirynt.siatka[ni][nj]);
        }
        return lista;
    }
}
