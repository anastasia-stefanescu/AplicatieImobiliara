package serviciu;

import model.Locuinta;
import model.Proprietar;
import model.Zona;
import repository.RepoLocuinta;
import repository.RepoProprietar;
import repository.RepoZona;


import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class ServiciuZona implements InterfataOperatii_simple<Zona>, InterfataOperatii_complexe<Zona> {

    Scanner sc = new Scanner(System.in);
    public ServiciuZona() {
    }

    @Override
    public void adauga() throws SQLException {
        Zona z = new Zona(sc); //automat si citeste datele de intrare
        RepoZona.adauga_Zona(z);

        //de adaugat la string buffer (audit?)
    }

    @Override
    public void sterge(Scanner scanner) throws SQLException, IOException {
        System.out.print("Introduceti numele zonei de sters: ");
        String nume = scanner.nextLine();

        RepoZona.sterge_Zona(nume);
    }

    @Override
    public void listeaza() throws SQLException {
        List<Zona> zone = RepoZona.getAllZone();
        for (int i = 0; i < zone.size(); i++) {
            System.out.println(zone.get(i).toString());
        }
    }

    @Override
    public void adauga_la(Zona z, String tip, Scanner scanner) throws SQLException, IOException {
        //poate fi doar locuinta
        if (tip.equals("locuinta") ){
            System.out.print("Introduceti id locuinta : ");
            int id = scanner.nextInt();
            Locuinta loc = RepoLocuinta.cautaLocuinta(id);
//            if (loc == null) RepoLocuinta.adauga_Locuinta(loc);
            RepoLocuinta.actualizeaza_Locuinta_Zona(loc.getId(), z.getNume());
        }
        else
            System.out.println("comanda incorecta");
    }


    @Override
    public void sterge_de_la(Zona z, String tip, Scanner scanner) throws SQLException, IOException {
        if (tip.equals("locuinta")) {
            System.out.print("Introduceti id-ul locuintei de sters: ");
            int s = scanner.nextInt();
            RepoLocuinta.actualizeaza_Locuinta_Zona(s, null);
        }
        else
            System.out.println("comanda incorecta");
    }

    @Override
    public Zona citeste(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("Numele zonei: ");
            String nume = scanner.nextLine();
            nume.toLowerCase();

            Zona ag = cauta(nume);
            if (ag!= null)
                return ag;
        }
    }

    @Override
    public Zona cauta(String nume) throws SQLException {
        Zona foundZone = RepoZona.cautaZona(nume);

        if (foundZone == null)
            System.out.println("Nu exista zona " + nume);
        return foundZone;
    }
}
