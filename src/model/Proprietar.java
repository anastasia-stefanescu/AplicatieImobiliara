package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Proprietar {
    protected String nume;
    protected int suma_bani;
    private List<Locuinta> locuinte = new ArrayList<Locuinta>();
    Scanner scanner = new Scanner(System.in);

    public Proprietar() {
        String nume = scanner.nextLine();
        this.nume = nume;
        int suma = scanner.nextInt();
        this.suma_bani = suma;
    }

    public Proprietar(String nume, int suma_bani) {
        this.nume = nume;
        this.suma_bani = suma_bani;
    }
    public String getNume() {
        return nume;
    }

    public void setSuma_bani(int suma_bani) {
        this.suma_bani = suma_bani;
    }

    public int getSuma_bani() {
        return suma_bani;
    }

    public List<Locuinta> getLocuinte() {
        return locuinte;
    }

    public void addLocuinta(Locuinta locuinta) {
        locuinte.add(locuinta);
    }

    public void deleteLocuinta(int id) {
        locuinte.removeIf(l -> l.getId() == id);
    }

    @Override
    public String toString() {
        return "Proprietar [nume=" + nume + "]";
    }

}
