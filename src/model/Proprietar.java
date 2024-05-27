package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Proprietar {
    protected String nume;
    protected int suma_bani;

    public Proprietar() {

    }

    public Proprietar(Scanner scanner)
    {
        System.out.println("Nume:");
        String nume = scanner.nextLine();
        this.nume = nume;
        System.out.println("Suma bani:");
        int bani = scanner.nextInt();
        this.suma_bani = bani;
    }

    public Proprietar(String nume, int suma_bani) {
        this.nume = nume;
        this.suma_bani = suma_bani;
    }
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }


    public void setSuma_bani(int suma_bani) {
        this.suma_bani = suma_bani;
    }

    public int getSuma_bani() {
        return suma_bani;
    }


    @Override
    public String toString() {
        return  nume + " suma bani= " + suma_bani;
    }




}
