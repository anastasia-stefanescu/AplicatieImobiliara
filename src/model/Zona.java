package model;

import model.Locuinta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zona {
    public String nume;
    private List<Locuinta> locuinte = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void Zona() {
        System.out.println("Nume: ");
        String s = scanner.nextLine();
        nume = s;
    }

    public void addLocuinta(Locuinta locuinta) {
        locuinte.add(locuinta);
    }

    public void deleteLocuinta(int id) {
        locuinte.removeIf(l -> l.getId() == id);
    }

    public String getNume() {
        return nume;
    }
    @Override
    public String toString() {
        return "Zona {" +
                "Nume : '" + nume + '\'' +
                 "}" ;

    }


}
