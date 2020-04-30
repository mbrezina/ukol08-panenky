package cz.czechitas.webapp;

import java.time.*;

public class Panenka {

    private Long id;
    private String jmeno;
    private String vrsek;
    private String spodek;
    private Instant casVzniku;

    public Panenka() {
    }

    public Panenka(Long id) {
        this.id = id;
        casVzniku = Instant.now();
    }

    public Panenka(String jmeno, String vrsek, String spodek) {
        this.jmeno = jmeno;
        this.vrsek = vrsek;
        this.spodek = spodek;
        casVzniku = Instant.now();
    }

    public Panenka(Long id, String jmeno, String vrsek, String spodek, Instant casVzniku) {
        this.id = id;
        this.jmeno = jmeno;
        this.vrsek = vrsek;
        this.spodek = spodek;
        this.casVzniku = casVzniku;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getVrsek() {
        return vrsek;
    }

    public void setVrsek(String vrsek) {
        this.vrsek = vrsek;
    }

    public String getSpodek() {
        return spodek;
    }

    public void setSpodek(String spodek) {
        this.spodek = spodek;
    }

    public Instant getCasVzniku() {
        return casVzniku;
    }

    public void setCasVzniku(Instant casVzniku) {
        this.casVzniku = casVzniku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Panenka)) return false;

        Panenka panenka = (Panenka) o;

        if (getId() == null) return false;
        return getId().equals(panenka.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Panenka{" +
                "id=" + id +
                ", jmeno='" + jmeno + '\'' +
                ", vrsek='" + vrsek + '\'' +
                ", spodek='" + spodek + '\'' +
                ", casVzniku=" + casVzniku +
                '}';
    }
}
