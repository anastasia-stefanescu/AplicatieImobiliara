package model;

import model.Locuinta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zona {
    public String nume;

    public Zona() {}

    public Zona(Scanner scanner) {
        System.out.println("Nume: ");
        String s = scanner.nextLine();
        nume = s;
    }


    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }
    @Override
    public String toString() {
        return nume;

    }


}
