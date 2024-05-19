package serviciu;

import model.Agentie;
import model.Locuinta;
import model.Agent;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Optional;

public class ServiciuAgentie implements InterfataOperatii_simple<Agentie>, InterfataOperatii_complexe<Agentie> {
    private List<Agentie> agentii =  new ArrayList<>();

    public ServiciuAgentie() {
    }

    public List<Agentie> getAgentii() {
        return agentii;
    }

    @Override
    public void adauga()
    {
        Agentie ag = new Agentie(); //automat si citeste datele de intrare
        agentii.add(ag);
        //de adaugat la string buffer (audit?)
    }

    @Override
    public void sterge(Scanner scanner)
    {
        System.out.print("Introduceti numele agentiei de sters: ");
        String nume = scanner.nextLine();

        agentii.removeIf(ag -> ag.getNume().equals(nume));
    }

    @Override
    public void listeaza()
    {
        for (int i = 0; i < agentii.size(); i++) {
            System.out.println(agentii.get(i));
        }
    }
    @Override
    public void adauga_la(Agentie ag, String tip, Scanner scanner)
    {
        //agentul e dependent de agentie, cand este sters de la o agentie consideram ca este sters din baza de date
        if (tip.equals("agent")) {
            Agent agent = new Agent();
            agent.setAgentie(ag);
            ag.addAgent(agent);
        }
        if (tip.equals("locuinta")) { // in plus locuinta poate fi scoasa de la un proprietar si mutata la altul;
            Locuinta loc = new Locuinta(scanner);
            loc.setProprietar(ag);
            ag.addLocuinta(loc);
        }
    }

    @Override
    public void sterge_de_la(Agentie ag, String tip, Scanner scanner)
    {
        System.out.print("Introduceti numele agentului / id-ul locuintei de sters: ");
        String s = scanner.nextLine();

        if (tip.equals("agent")) {
            ag.deleteAgent(s);
        }
        if (tip.equals("locuinta") ){
            ag.deleteLocuinta(Integer.parseInt(s));
        }
    }

    public Agentie citeste(Scanner scanner){
        while (true) {
            System.out.println("Numele agentiei: ");
            String nume = scanner.nextLine();
            nume.toLowerCase();

            Optional<Agentie> ag = cauta(nume);
            if (ag.isPresent())
            {
                return ag.get();
            }
        }

    }

    public Optional<Agentie> cauta(String nume)
    {
        Optional<Agentie> foundAg = agentii.stream()
                                    .filter(ag -> ag.getNume().equals(nume))
                                    .findFirst(); // Find the first matching element

        if (!foundAg.isPresent())
            System.out.println("Nu exista agentia " + nume);

        return foundAg;
    }
}
