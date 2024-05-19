package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cumparator {
    private int id;
    private String nume;
    private int suma_bani;
    private List<Locuinta> locuinte_preferate = new ArrayList<>();

    private List<Zona> zone_preferate = new ArrayList<>();

    private List<Proprietar> proprietari_preferati = new ArrayList<>();

    private int an1, an2;
    private int nr_camere;
    private String tip;

    public Cumparator(int id) {
        Scanner scanner = new Scanner(System.in);
        this.id = id;
        System.out.println("Nume: ");
        String s = scanner.nextLine();
        this.nume = s;
        System.out.println("suma bani: ");
        s = scanner.nextLine();
        this.suma_bani = Integer.parseInt(s);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getNr_camere()
    {
        return nr_camere;
    }

    public String getTip() {
        return tip;
    }

    public int getAn1(){
        return an1;
    }

    public int getAn2(){
        return an2;
    }

    public List<Zona> getZone_preferate()
    {
        return zone_preferate;
    }

    public List<Proprietar> getProprietari_preferati()
    {
        return proprietari_preferati;
    }

    public int getSuma_bani()
    {
        return this.suma_bani;
    }

    public void setSuma_bani(int s)
    {
        this.suma_bani = s;
    }



    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setAn1(int an1)
    {
        this.an1 = an1;
    }

    public void setAn2(int an2) {
        this.an2 = an2;
    }

    public void setNr_camere(int nr_camere) {
        this.nr_camere = nr_camere;
    }

    public void addZone(Zona zone) {
        zone_preferate.add(zone);
    }

    public void addProprietar(Proprietar prop) {
       proprietari_preferati.add(prop);
    }

    public void addLocuinta(Locuinta locuinta) {
        locuinte_preferate.add(locuinta);
    }
}

