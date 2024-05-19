package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Agent {
    private String nume;
    private Agentie agentie;
    private List<Locuinta> locuinte = new ArrayList<>();

    public void Agent(Scanner scanner) {
        System.out.println("Nume: ");
        String s = scanner.nextLine();
        nume = s;
    }

    public void setAgentie(Agentie agentie) {
        this.agentie = agentie;
    }

    public String getNume() {
        return nume;
    }

    @Override
    public String toString() {
        return "Agent {nume=" + nume + ", agentie=" + agentie + "}";
    }

}
