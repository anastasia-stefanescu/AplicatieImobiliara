package serviciu;



import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import model.Locuinta;
import model.Proprietar;
import repository.RepoLocuinta;
import repository.RepoProprietar;

public class ServiciuProprietar implements InterfataOperatii_simple<Proprietar>, InterfataOperatii_complexe<Proprietar> {
    Scanner scanner = new Scanner(System.in);
    public ServiciuProprietar() {
    }

    @Override
    public void adauga() throws SQLException {
        Proprietar prop = new Proprietar(scanner); //automat si citeste datele de intrare
        RepoProprietar.adauga_Proprietar(prop);
        //de adaugat la string buffer (audit?)
    }

    @Override
    public void listeaza() throws SQLException {
        List<Proprietar> proprietari = RepoProprietar.getAllProprietari();
        for (int i = 0; i < proprietari.size(); i++) {
            System.out.println(proprietari.get(i).toString());
        }
    }

    @Override
    public void sterge(Scanner scanner) throws SQLException, IOException {
        System.out.print("Introduceti numele proprietarii de sters: ");
        String nume = scanner.nextLine();

        RepoProprietar.sterge_Proprietar(nume);
    }
    @Override
    public void adauga_la(Proprietar prop, String tip, Scanner scanner) throws SQLException, IOException {
        //poate fi doar locuinta
        if (tip .equals("locuinta")) {
            Locuinta loc = new Locuinta(scanner);
            RepoLocuinta.adauga_Locuinta(loc);
            RepoLocuinta.actualizeaza_Locuinta_Proprietar(loc.getId(), prop.getNume());
        }
        else
            System.out.println("comanda incorecta");
    }


    @Override
    public void sterge_de_la(Proprietar prop, String tip, Scanner scanner) throws SQLException, IOException {
        if (tip.equals("locuinta")) {
            System.out.print("Introduceti id-ul locuintei de sters: ");
           int s = scanner.nextInt();
           RepoLocuinta.sterge_Locuinta(s);
        }
        else
            System.out.println("comanda incorecta");

    }

    @Override
    public Proprietar citeste(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("Numele proprietarului : ");
            String nume = scanner.nextLine();
            nume.toLowerCase();

            Proprietar p = cauta(nume);
            if (p!= null)
                return p;
        }
    }

    @Override
    public Proprietar cauta(String nume) throws SQLException {
        Proprietar foundProp = RepoProprietar.cautaProprietar(nume);

        if (foundProp == null)
            System.out.println("Nu exista proprietarul " + nume);
        return foundProp;

    }
}
