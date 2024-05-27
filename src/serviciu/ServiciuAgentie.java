package serviciu;

import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import java.util.List;
import java.util.Optional;

import repository.RepoAgentie;
import repository.RepoLocuinta;

public class ServiciuAgentie implements InterfataOperatii_simple<Agentie>, InterfataOperatii_complexe<Agentie> {

    Scanner scanner = new Scanner(System.in);

    public ServiciuAgentie() {
    }

    @Override
    public void adauga() throws SQLException {
        Agentie ag = new Agentie(scanner); //automat si citeste datele de intrare
        RepoAgentie.adauga_Agentie(ag);
        //de adaugat la string buffer (audit?)
    }

    @Override
    public void sterge(Scanner scanner) throws SQLException, IOException {
        System.out.print("Introduceti numele agentiei de sters: ");
        String nume = scanner.nextLine();

        RepoAgentie.sterge_Agentie(nume);
    }

    @Override
    public void listeaza() throws SQLException {
        List<Agentie> agentii = RepoAgentie.getAllAgentii();
        for (int i = 0; i < agentii.size(); i++) {
            System.out.println(agentii.get(i));
        }
    }
    @Override
    public void adauga_la(Agentie ag, String tip, Scanner scanner) throws SQLException, IOException {
        //agentul e dependent de agentie, cand este sters de la o agentie consideram ca este sters din baza de date
        if (tip.equals("agent")) {
            Agent agent = new Agent(scanner);

            //RepoAgent.ada

           // agent.setAgentie(ag);

            //ag.addAgent(agent);
        }
        if (tip.equals("locuinta")) { // in plus locuinta poate fi scoasa de la un proprietar si mutata la altul;
            Locuinta loc = new Locuinta(scanner);
            boolean ok = RepoLocuinta.adauga_Locuinta(loc);
            ok = RepoLocuinta.actualizeaza_Locuinta_Agentie(loc.getId(), ag.getNume());
        }
    }

    @Override
    public void sterge_de_la(Agentie ag, String tip, Scanner scanner) throws SQLException, IOException {
        System.out.print("Introduceti id-ul agentului / id-ul locuintei de sters: ");
        int s = scanner.nextInt();

        if (tip.equals("agent")) {
            //ag.deleteAgent(s);
        }
        if (tip.equals("locuinta") ){
           RepoLocuinta.sterge_Locuinta(s);
        }
    }

    public Agentie citeste(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("Numele agentiei: ");
            String nume = scanner.nextLine();
            nume.toLowerCase();

            Agentie c = cauta(nume);
            if (c!= null)
            {
                return c;
            }
        }
    }

    public Agentie cauta(String nume) throws SQLException
    {
        Agentie ag = RepoAgentie.cautaAgentie(nume);
        if (ag == null) {
            System.out.println("Agentia nu exista");
        }
        return ag;
    }
}
