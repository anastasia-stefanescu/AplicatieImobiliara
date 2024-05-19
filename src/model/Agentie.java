package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Agentie extends Proprietar {
    private List<Agent> agenti = new ArrayList<>();
    private int comision;
    private Scanner scanner = new Scanner(System.in);

    public Agentie(){
        super();
        System.out.println("Comision: ");
        String s = scanner.nextLine();
        comision = Integer.parseInt(s);
    }
    public void addAgent(Agent ag){
        agenti.add(ag);
    }

    public void deleteAgent(String nume){
        agenti.removeIf(ag -> ag.getNume().equals(nume));
    }

    @Override
    public String toString() {
        return "Agentie {" +
                      "Nume : '" + super.nume + '\'' +
                      ", Comision ='" + String.valueOf(comision) + '\'' +
                      ", Suma disponibila =" + String.valueOf(super.suma_bani) + "}" ;

    }
}
