package model;

import java.util.Scanner;

public class Locuinta {
    private int id;
    private String tip;
    private String proprietar; //numele proprietarului
    private int cost;
    private String zona;
    private String adresa;
    private int nr_camere;
    private int an_constructie;
    private int suprafata;

    public Locuinta() {}

    public Locuinta(Scanner scanner) {
      System.out.println("nr_camere : ");
      int nr = scanner.nextInt();
      this.nr_camere  = nr;
      System.out.println("an_constructie : ");
      nr =  scanner.nextInt();
      this.an_constructie = nr;
      System.out.println("suprafata : ");nr =  scanner.nextInt();
      this.suprafata = nr;
      System.out.println("cost : ");
      nr =  scanner.nextInt();
      this.cost = nr;
      String ceva  = scanner.nextLine();
      System.out.println("tip : ");
      this.tip  = scanner.nextLine();
      System.out.println("adresa : ");
      this.adresa = scanner.nextLine();

    }

    public int getId() {
        return id;
    }

    public int getAn_constructie() {
        return an_constructie;
    }

    public int getNr_camere() {
        return nr_camere;
    }

    public String getTip() {
        return tip;

    }

    public String getZona()
    {
        return zona;
    }

    public String getProprietar(){
        return proprietar;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setNr_camere(int nr_camere) {
        this.nr_camere = nr_camere;
    }

    public void setAn_constructie(int an_constructie) {
        this.an_constructie = an_constructie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }


    public void setZona(String zona) {
        this.zona = zona;
    }

    public void setProprietar(String proprietar) {
        this.proprietar = proprietar;
    }

    public int getSuprafata() {
        return suprafata;
    }

    public void setSuprafata(int suprafata) {
        this.suprafata = suprafata;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void vizualizare()
    {
        String viz;
        if (zona == null)
            viz = this.toString() + '\n' + adresa + '\n' + proprietar.toString() + '\n' + suprafata;
        else
             viz = this.toString() + '\n' + adresa + '\n' + proprietar.toString() + '\n' + zona.toString() + '\n' + suprafata;
        System.out.println(viz);
    }

    @Override
    public String toString() {
        return
                id +
                " tip : " + tip +
                ", "  + proprietar +
               ", "  + zona +
                ", Adresa : " + adresa +
                ", Numar camere =" + String.valueOf(nr_camere) +
                ", An constructie =" + String.valueOf(an_constructie) +
                ", Suprafata =" + String.valueOf(suprafata) ;

    }
}
