package serviciu;

import model.Agentie;
import model.Locuinta;
import model.Agent;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
public class ServiciuAgentie implements InterfataOperatii_simple, InterfataOperatii_complexe<Agentie> {
    private List<Agentie> agentii =  new ArrayList<>();

    private ServiciuAgentie() {
    }

    @Override
    public void adauga()
    {
        Agentie ag = new Agentie();
        agentii.add(ag);
        //de adaugat la string buffer (audit?)
    }

    @Override
    public void sterge(Scanner scanner)
    {
        System.out.print("Introduceti numele agentiei de sters: ");
        String nume = scanner.nextLine();

        agentii.removeIf(ag -> ag.nume == nume);
    }
    @Override
    public void adauga_la(Agentie ag)
    {

    }
    @Override
    public void adauga_la(Agentie ag, Agent agent)
    {

    }

    @Override
    public void sterge_de_la(Agentie)
    {

    }
}
