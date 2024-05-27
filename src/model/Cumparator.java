package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cumparator {
    private int id;
    private String nume;
    private int suma_bani;

    private int an1, an2;
    private int nr_camere;
    private String tip;

    public Cumparator(){}

    public Cumparator(Scanner scanner) {
        System.out.println("Nume: ");
        String s = scanner.nextLine();
        this.nume = s;
        System.out.println("suma bani: ");
        s = scanner.nextLine();
        this.suma_bani = Integer.parseInt(s);
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
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


    @Override
    public String toString() {
        return
                id +
                " Nume =" + nume +
                ", Suma_bani =" + String.valueOf(suma_bani) +
                ", tip preferat: '" + tip + '\'' +
                ", Numar camere =" + String.valueOf(nr_camere) +
                ", An constructie 1=" + String.valueOf(an1) + " - " + String.valueOf(an2)
                        ;
    }

}

