package properties;

public class Konsultim extends baza{

    private int mesimdhenesi;
    private  int salla;
    private String ora;
    private String ora_m;
    private String lenda;

    public int getMesimdhenesi() {
        return mesimdhenesi;
    }

    public int getSalla() {
        return salla;
    }

    public String getOra() {
        return ora;
    }

    public String getOra_m() {
        return ora_m;
    }

    public String getLenda() {
        return lenda;
    }

    public Konsultim(int mesimdhenesi, int salla, String ora, String ora_m, String lenda) {
        this.mesimdhenesi = mesimdhenesi;
        this.salla = salla;
        this.ora = ora;
        this.ora_m = ora_m;
        this.lenda = lenda;
    }
}
