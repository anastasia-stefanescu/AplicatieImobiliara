package serviciu;

import model.Agent;
import model.Agentie;
import model.Locuinta;
import model.Cumparator;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ServiciuCumparator implements InterfataOperatii_simple<Cumparator>
{
    private List<Cumparator> cumparatori =  new ArrayList<>();


    public ServiciuCumparator() {
    }

    public List<Cumparator> getCumparatori() {
        return cumparatori;
    }

    @Override
    public void adauga()
    {
        int s = cumparatori.size()+1;
        Cumparator c = new Cumparator(s); //automat si citeste datele de intrare
        cumparatori.add(c);
        //de adaugat la string buffer (audit?)
    }

    @Override
    public void sterge(Scanner scanner)
    {
        System.out.print("Introduceti id-ul cumparatorului de sters: ");
        int ind = scanner.nextInt();

        cumparatori.removeIf(c -> c.getId() == ind);
    }

    @Override
    public void listeaza()
    {
        for (int i = 0; i < cumparatori.size(); i++) {
            System.out.println(cumparatori.get(i));
        }
    }

    public Cumparator citeste(Scanner scanner){
        while (true) {
            System.out.print("\"Introduceti id-ul cumparatorului de citit: ");
            String ind = scanner.nextLine();


            Optional<Cumparator> c = cauta(ind);
            if (c.isPresent())
            {
                return c.get();
            }
        }

    }

    public Optional<Cumparator> cauta(String ind)
    {
        int id  = Integer.parseInt(ind);
        Optional<Cumparator> found = cumparatori.stream()
                .filter(c -> c.getId() == id)
                .findFirst(); // Find the first matching element

        if (!found.isPresent())
            System.out.println("Nu exista cumparatorul " + ind);

        return found;
    }
}
