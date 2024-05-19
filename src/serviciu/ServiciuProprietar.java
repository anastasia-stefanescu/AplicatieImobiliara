package serviciu;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import model.Agent;
import model.Agentie;
import model.Locuinta;
import model.Proprietar;

public class ServiciuProprietar implements InterfataOperatii_simple<Proprietar>, InterfataOperatii_complexe<Proprietar> {
    private List<Proprietar> proprietari =  new ArrayList<>();

    public ServiciuProprietar() {
    }

    public List<Proprietar> getProprietari() {
        return proprietari;
    }

    @Override
    public void adauga()
    {
        Proprietar prop = new Proprietar(); //automat si citeste datele de intrare
        proprietari.add(prop);
        //de adaugat la string buffer (audit?)
    }

    @Override
    public void listeaza()
    {
        for (int i = 0; i < proprietari.size(); i++) {
            System.out.println(proprietari.get(i).toString());
        }
    }

    @Override
    public void sterge(Scanner scanner)
    {
        System.out.print("Introduceti numele proprietarii de sters: ");
        String nume = scanner.nextLine();

        proprietari.removeIf(prop -> prop.getNume().equals(nume));
    }
    @Override
    public void adauga_la(Proprietar prop, String tip, Scanner scanner)
    {
        //poate fi doar locuinta
        if (tip .equals("locuinta")) {
            Locuinta loc = new Locuinta(scanner);
            loc.setProprietar(prop);
            prop.addLocuinta(loc);
        }
        else
            System.out.println("comanda incorecta");
    }


    @Override
    public void sterge_de_la(Proprietar prop, String tip, Scanner scanner)
    {
        if (tip.equals("locuinta")) {
            System.out.print("Introduceti id-ul locuintei de sters: ");
            String s = scanner.nextLine();
            prop.deleteLocuinta(Integer.parseInt(s));
        }
        else
            System.out.println("comanda incorecta");

    }

    @Override
    public Proprietar citeste(Scanner scanner)
    {
        while (true) {
            System.out.println("Numele proprietarului : ");
            String nume = scanner.nextLine();
            nume.toLowerCase();

            Optional<Proprietar> p = cauta(nume);
            if (p.isPresent())
            {
                return p.get();
            }
        }
    }

    @Override
    public Optional<Proprietar> cauta(String nume)
    {
        Optional<Proprietar> foundProp = proprietari.stream()
                .filter(p -> p.getNume().equals(nume))
                .findFirst(); // Find the first matching element

        if (foundProp.isPresent())
            return foundProp;
        else {
            System.out.println("Nu exista proprietarul " + nume);
            return null;
        }
    }
}
