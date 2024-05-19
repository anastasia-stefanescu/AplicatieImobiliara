package serviciu;

import model.Locuinta;
import model.Zona;


import java.util.*;

public class ServiciuZona implements InterfataOperatii_simple<Zona>, InterfataOperatii_complexe<Zona> {
    private List<Zona> zone =  new ArrayList<>();

    public ServiciuZona() {
    }

    public List<Zona> getZona() {
        return zone;
    }

    @Override
    public void adauga()
    {
        Zona z = new Zona(); //automat si citeste datele de intrare
        zone.add(z);
        //de adaugat la string buffer (audit?)
    }

    @Override
    public void sterge(Scanner scanner)
    {
        System.out.print("Introduceti numele zonei de sters: ");
        String nume = scanner.nextLine();

        zone.removeIf(z -> z.nume.equals(nume));
    }

    @Override
    public void listeaza()
    {
        for (int i = 0; i < zone.size(); i++) {
            System.out.println(zone.get(i).toString());
        }
    }

    @Override
    public void adauga_la(Zona z, String tip, Scanner scanner)
    {
        //poate fi doar locuinta
        if (tip.equals("locuinta") ){
            Locuinta loc = new Locuinta(scanner);
            loc.setZona(z);
            z.addLocuinta(loc);
        }
        else
            System.out.println("comanda incorecta");
    }


    @Override
    public void sterge_de_la(Zona z, String tip, Scanner scanner)
    {
        if (tip.equals("locuinta")) {
            System.out.print("Introduceti id-ul locuintei de sters: ");
            String s = scanner.nextLine();
            //trb scos si proprietarul locuintei !!!!!
            z.deleteLocuinta(Integer.parseInt(s));
        }
        else
            System.out.println("comanda incorecta");
    }

    @Override
    public Zona citeste(Scanner scanner)
    {
        while (true) {
            System.out.println("Numele zonei: ");
            String nume = scanner.nextLine();
            nume.toLowerCase();

            Optional<Zona> ag = cauta(nume);
            if (ag.isPresent())
            {
                return ag.get();
            }
        }
    }

    @Override
    public Optional<Zona> cauta(String nume)
    {
        Optional<Zona> foundZone = zone.stream()
                .filter(z -> z.nume.equals(nume))
                .findFirst(); // Find the first matching element

        if (foundZone.isPresent())
            return foundZone;
        else {
            System.out.println("Nu exista zona " + nume);
            return null;
        }
    }
}
