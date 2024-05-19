package model;

import java.util.Scanner;

public class Locuinta {
    private int id;
    private final String tip;
    private Proprietar proprietar;
    private int cost;
    private Zona zona;
    private final String adresa;
    private final int nr_camere;
    private final int an_constructie;
    private final int suprafata;


    public Locuinta(Scanner scanner) {
      System.out.println("tip : ");
      tip  = scanner.nextLine();
      System.out.println("adresa : ");
      adresa = scanner.nextLine();
      System.out.println("nr_camere : ");
      nr_camere  = scanner.nextInt();
      System.out.println("an_constructie : ");
      an_constructie = scanner.nextInt();
      System.out.println("suprafata : ");
      suprafata = scanner.nextInt();
        System.out.println("cost : ");
        cost = scanner.nextInt();

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

    public Zona getZona()
    {
        return zona;
    }

    public Proprietar getProprietar(){
        return proprietar;
    }

    public int getCost() {
        return cost;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public void setProprietar(Proprietar proprietar) {
        this.proprietar = proprietar;
    }

    public void vizualizare()
    {
        String viz = this.toString() + '\n' + adresa + '\n' + proprietar.toString() + '\n' + zona.toString() + '\n' + suprafata;
        System.out.println(viz);
    }

    @Override
    public String toString() {
        return "Locuinta {" +
                "ID : '" + id + '\'' +
                ", tip : '" + tip + '\'' +
                //", "  + proprietar.toString() +
               // ", "  + zona.toString() +
               // ", Adresa : '" + adresa + '\'' +
                ", Numar camere =" + String.valueOf(nr_camere) +
                ", An constructie =" + String.valueOf(an_constructie) +
                ", Suprafata =" + String.valueOf(suprafata) +
                 "}" ;

    }
}
