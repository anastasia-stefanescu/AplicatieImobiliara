package model;

import model.Agent;

public class Locuinta {
    public static int id;
    public final String tip;
    private Proprietar proprietar;
    public Agent agent;
    public Zona zona;
    public final String adresa;
    public final int nr_camere;
    public final int an_constructie;
    public final int suprafata;

    public Locuinta(int nrcamere, int anconstructie, int supraf, String adr, String t) {
        nr_camere = nrcamere;
        an_constructie = anconstructie;
        suprafata = supraf;
        adresa = adr;
        tip = t;
    }
}
