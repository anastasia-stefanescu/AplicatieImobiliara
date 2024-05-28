package serviciu;

import model.*;
import repository.RepoCumparator;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ServiciuCumparator implements InterfataOperatii_simple<Cumparator>
{
    Scanner scanner = new Scanner(System.in);
    public ServiciuCumparator() {
    }

    @Override
    public void adauga() throws SQLException, IOException {
        Cumparator c = new Cumparator(scanner); //automat si citeste datele de intrare
        RepoCumparator.adauga_Cumparator(c);
        //de adaugat la string buffer (audit?)
    }

    @Override
    public void sterge(Scanner scanner) throws SQLException, IOException {
        System.out.print("Introduceti id-ul cumparatorului de sters: ");
        int ind = scanner.nextInt();

        RepoCumparator.sterge_Cumparator(ind);
    }

    @Override
    public void listeaza() throws SQLException {
        List<Cumparator> cumparatori = RepoCumparator.getAllCumparatori();
        for (int i = 0; i < cumparatori.size(); i++) {
            System.out.println(cumparatori.get(i));
        }
    }

    public Cumparator citeste(Scanner scanner) throws SQLException {
        while (true) {
            System.out.print("\"Introduceti id-ul cumparatorului de citit: ");
            String ind = scanner.nextLine();


            Cumparator c = cauta(ind);
            if (c != null)
            {
                return c;
            }
        }

    }

    public Cumparator cauta(String ind) throws SQLException {
        int id  = Integer.parseInt(ind);
        Cumparator found = RepoCumparator.cautaCumparator(id);

        if ( found == null)
            System.out.println("Nu exista cumparatorul " + ind);

        return found;
    }
}
