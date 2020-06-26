package properties;

public class Lenda extends baza{
    private String Lenda;
    private String Profesoret;
    private int Viti;
    public Lenda(String lenda, String profesori, int viti){
        this.Lenda=lenda;
        this.Profesoret=profesori;
        this.Viti=viti;
    }
    public Lenda(){
    }

    public String getLenda() {
        return Lenda;
    }

    public void setLenda(String lenda) {
        Lenda = lenda;
    }

    public String getProfesoret() {
        return Profesoret;
    }

    public void setProfesoret(String profesoret) {
        Profesoret = profesoret;
    }

    public int getViti() {
        return Viti;
    }

    public void setViti(int viti) {
        Viti = viti;
    }




}

