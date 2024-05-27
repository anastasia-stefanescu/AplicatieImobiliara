package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Agent {
    private String nume;
    private String agentie;

    public Agent(String nume, String agentie) {
        this.nume = nume;

    }
    public Agent(Scanner scanner) {
        System.out.println("Nume: ");
        String s = scanner.nextLine();
        nume = s;
    }

    public String getAgentie() {
        return agentie;
    }

    public void setAgentie(String agentie) {
        this.agentie = agentie;
    }

    public String getNume() {
        return nume;
    }

    @Override
    public String toString() {
        return nume + " agentie=" + agentie + "}";
    }

}
