package serviciu;


import model.Agentie;
import model.Locuinta;
import model.Proprietar;
import model.Zona;
import model.Cumparator;
import repository.*;

import exceptions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;


import static java.lang.Math.abs;

public class Serviciu {
    private static final Serviciu instanta = new Serviciu();




    private Scanner scanner;

    private ServiciuAgentie servAgentii;
    private ServiciuZona servZona;
    private ServiciuProprietar servProp;
    private ServiciuCumparator servCump;

    public Serviciu(){

        scanner = new Scanner(System.in);
        servAgentii = new ServiciuAgentie();
        servZona = new ServiciuZona();
        servProp = new ServiciuProprietar();
        servCump = new ServiciuCumparator();
        listPossibleOperations();
    }

    public static Serviciu getInstance() {
        return instanta;
    }

    public String getUserInput() {
        System.out.print("Actiune : ");
        return scanner.nextLine();
    }

    public boolean processInput(String input) throws SQLException, IOException {
        //redirectioneaza la functiile necesare
        String[] words = input.split(" ");
        boolean input_bun = false;

        if (words[0].equals("adauga") || words[0].equals("sterge"))
        {
            if (words[1].equals("la"))
            { // adauga / sterge la
                switch (words[2]) {
                    case "agentie": //locuinta sau agent
                        input_bun = true;
                        Agentie ag = servAgentii.citeste(scanner);
                        if (words[0].equals("adauga"))
                            servAgentii.adauga_la(ag, words[3], scanner);
                        else
                            servAgentii.sterge_de_la(ag, words[3], scanner);
                        break;
                    case "proprietar": //locuinta sau agent
                        input_bun = true;
                        Proprietar prop = servProp.citeste(scanner);
                        if (words[0].equals("adauga"))
                            servProp.adauga_la(prop, words[3], scanner);
                        else
                            servProp.sterge_de_la(prop, words[3], scanner);
                        break;
                    case "zona": //locuinta
                        input_bun = true;
                        Zona zona = servZona.citeste(scanner);
                        if (words[0].equals("adauga"))
                            servZona.adauga_la(zona, words[3], scanner);
                        else
                            servZona.sterge_de_la(zona, words[3], scanner);
                        break;

                }

            }
            else
            { //adauga/sterge agentie/zona/proprietar
                switch (words[1]) {
                    case "agentie":
                        input_bun = true;
                        if (words[0].equals("adauga"))
                            servAgentii.adauga();
                        else
                            servAgentii.sterge(scanner);
                        break;
                    case "zona":
                        input_bun = true;
                        if (words[0].equals("adauga"))
                            servZona.adauga();
                        else
                            servZona.sterge(scanner);
                        break;
                    case "proprietar":
                        input_bun = true;
                        if (words[0].equals("adauga"))
                            servProp.adauga();
                        else
                            servProp.sterge(scanner);
                        break;

                }
            }
        }
        else { // altfel, actiuni cumparator sau listari
            if (words[0].equals("cumparator")) {//ACTIUNI CUMPARATOR

                if (words[1].equals("adauga") || words[1].equals("sterge"))
                {
                    input_bun = true;
                    if (words[1].equals("adauga"))
                        servCump.adauga();
                    else
                        servCump.sterge(scanner);
                }
                else
                {
                    Cumparator cump = servCump.citeste(scanner);

                    int ok = 0;

                    List<Locuinta> locuinte_gasite = new ArrayList<Locuinta>();
                    if (words[1].equals("cauta")) //CAUTA LOCUINTA
                    {
                        input_bun = true;
                        try {
                            locuinte_gasite = setup_cautare(cump, words);
                            if (locuinte_gasite != null && locuinte_gasite.size() > 0)
                                ok = 1;
                        }catch(DateIncomplete d){
                            System.out.println("Fiecare locuinta trebuie sa aiba setata zona, altfel nu va fi luata in calcul");
                        }
                    }
                    if (words[1].equals("contacteaza")) {
                        //CONTACTEAZA AGENTIE
                        input_bun = true;
                        Agentie agentie = servAgentii.citeste(scanner);
                        try {
                            locuinte_gasite = setup_contactare(cump, agentie);

                            if (locuinte_gasite != null && locuinte_gasite.size() > 0)
                                ok = 1;
                        }catch(DateIncomplete d){
                            System.out.println("Fiecare locuinta trebuie sa aiba setata zona, altfel nu va fi luata in calcul");
                        }
                    }
                    if (words[1].equals("modifica")) {
                        ok = 2;
                        modificare_preferinte(cump);
                    }


                    if (ok == 1) {
                        //alege locuinta de vizualizat din cele afisate
                        System.out.println("Actiuni posibile:  cumpara locuinta  / exit ");
                        Scanner scanner2 = new Scanner(System.in);
                        String inp = scanner2.nextLine();
                        if (inp.equals("cumpara locuinta"))
                        {
                            System.out.println("Id locuinta: ");
                            int id = scanner.nextInt();

                            for (Locuinta locuinta : locuinte_gasite)
                                if (locuinta.getId() == id) {
                                    locuinta.vizualizare();
                                    //System.out.println("Actiuni posibile: cumpara / exit(nu face nimic) ");
                                    //String in = scanner.nextLine();
                                    //if (in.equals("cumpara")){
                                        cumparaLocuinta(cump, locuinta);

                                    //}
                                }

                        }

                    }

                }
            }
            else {
                //VIZUALIZARI
                if (words[0].equals("vizualizare")) {
                    switch (words[1]) {
                        case "zone":
                            input_bun = true;
                            servZona.listeaza();
                            break;
                        case "proprietari":
                            input_bun = true;
                            servProp.listeaza();
                            break;
                        case "agentii":
                            input_bun = true;
                            servAgentii.listeaza();
                            break;
                        case "locuinte":
                            input_bun = true;
                            List<Locuinta> locuinte = RepoLocuinta.getAllLocuinte();
                            for (Locuinta locuinta : locuinte)
                                System.out.println(locuinta);
                            break;
                    }
                }
            }
        }

        return input_bun;

    }



    public void listPossibleOperations()
    {
        System.out.println("Optiuni actiuni (scrieti in cuvinte ce actiune vreti sa executati:");
        System.out.println(" - vizualizare zone / agentii / proprietari / locuinte");
        System.out.println(" - adauga agentie / proprietar / zona");
        System.out.println(" - sterge agentie / proprietar / zona");
        System.out.println(" - adauga la proprietar/zona locuinta  // la agentie agent/locuinta");
        System.out.println(" - sterge locuinta de la agentie / proprietar / zona // agent de la agentie");
        System.out.println(" - cumparator cauta [automat] (locuinta, dupa filtre)");
        System.out.println(" - cumparator contacteaza agentie (pentru a vizualiza locuintele disponibile)");
        System.out.println("    - vizualizeaza locuinta [id] (dupa ce a cautat/contactat agentie) ");
        System.out.println("            - cumpara locuinta (dupa ce contacteaza agentie/proprietar)");
        System.out.println(" - cumparator modifica filtre");
        System.out.println(" - cumparator adauga / sterge");
        System.out.println("stop");
    }

    public List<Locuinta> setup_cautare(Cumparator cump, String[] words) throws SQLException, DateIncomplete {
        List<Locuinta> locuinte_posibile = new ArrayList<Locuinta>();
        List<Proprietar> proprietari_copy = RepoProprietar.getAllProprietari();//ne uitam la proprietarii privati in orice caz
        proprietari_copy.addAll(RepoAgentie.getAllAgentii());
        int nr_camere;
        int an1;
        int an2;
        String tip;
        List<Zona> zone = new ArrayList<Zona>();

        if (words.length > 2)
        {                        //cautare automata dupa preferintele listate
            nr_camere = cump.getNr_camere();
            an1 = cump.getAn1();
            an2 = cump.getAn2();
            tip = cump.getTip();
            zone = RepoZona.getAllZone();
        } else { //cautare dupa input
            try {
                System.out.println("Tip locuinta: ");
                tip = scanner.nextLine();
                System.out.println("Nr camere: ");
                nr_camere = scanner.nextInt();
                System.out.println("Anul minim de constructie: ");
                an1 = scanner.nextInt();
                System.out.println("Anul maxim de constructie: ");
                an2 = scanner.nextInt();


                Scanner scanner2 = new Scanner(System.in);
                System.out.println("Zone preferate (scrieti orice, sau stop pt oprire): ");
                String z = scanner2.nextLine();
                while (!z.equals("stop")) {
                    Zona zona = servZona.cauta(z);
                    if (zona != null)
                        zone.add(zona);
                    System.out.println("Urmatoarea zona / stop");
                    z = scanner2.nextLine();
                }
            }catch (InputMismatchException mism) {
                System.out.println("Tip input gresit");
                return setup_cautare(cump, words);
            }
        }

        return cautareLocuinta(cump, proprietari_copy, an1, an2, tip, nr_camere, zone);
    }

    public List<Locuinta> setup_contactare(Cumparator cump, Agentie agentie) throws SQLException, DateIncomplete {
        List<Locuinta> locuinte_posibile = new ArrayList<Locuinta>();
        List<Proprietar> proprietari_copy = new ArrayList<Proprietar>();
        proprietari_copy.add(agentie);

        List<Zona> zone = new ArrayList<Zona>();
        System.out.println("Tip locuinta: ");
        String tip = scanner.nextLine();
        System.out.println("Nr camere: ");
        int nr_camere = scanner.nextInt();
        System.out.println("Anul minim de constructie: ");
        int an1 = scanner.nextInt();
        System.out.println("Anul maxim de constructie: ");
        int an2 = scanner.nextInt();

        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Zone preferate (scrieti orice, sau stop pt oprire): ");
        String z = scanner2.nextLine();
        while (!z.equals("stop"))
        {
            Zona zona = servZona.cauta(z);
            if(zona != null)
                zone.add(zona);
            System.out.println("Urmatoarea zona / stop");
            z = scanner2.nextLine();
        }

        return cautareLocuinta(cump, proprietari_copy, an1, an2, tip, nr_camere, zone);

    }



    public void modificare_preferinte(Cumparator cump) throws SQLException, IOException {
        System.out.println("Preferintele dvs. curente:");
        System.out.println(" - Tip: " + cump.getTip());
        System.out.println(" - Nr camere: " + cump.getNr_camere());
        System.out.println(" - Ani constructie: " + cump.getAn1() + " - " + cump.getAn2());

        System.out.println("Ce doriti sa modificati (minuscule, stop pt oprire): ");
        String z = scanner.nextLine();
        int an1 = cump.getAn1();
        int an2 = cump.getAn2();
        int nr_camere = cump.getNr_camere();
        String tip = cump.getTip();
        while (! z.equals("stop"))
        {
            if(z.equals("tip")) {
                System.out.println("Noul tip(apartament / casa): ");
                tip = scanner.nextLine();
            }
            if(z.equals("an1")) {
                System.out.println(" - Noul an minim: ");
                an1 = scanner.nextInt();
            }
            if(z.equals("an2")) {
                System.out.println(" - Noul an maxim: ");
                an2 = scanner.nextInt();
            }
            if(z.equals("nr_camere") || z.equals("nr camere")) {
                System.out.println(" - Noul numar de camere ");
                nr_camere = scanner.nextInt();
            }
            System.out.println(" - Urmatoarea modificare, introduceti tip:");
            Scanner scanner2 = new Scanner(System.in);
            z = scanner2.nextLine();
        }
        RepoCumparator.update_preferinte(cump.getId(), an1, an2, nr_camere, tip);
    }

    public static boolean searchZona(List<Zona> items, String nume) {
        for (Zona z : items) {
            if (z.getNume().equals(nume))
                return true;
        }
        return false;
    }

    public static boolean searchProp(List<Proprietar> items, String nume) {
        for (Proprietar z : items) {
            if (z.getNume().equals(nume))
                return true;
        }
        return false;
    }

    public static List<Locuinta> cautareLocuinta(Cumparator cump, List<Proprietar> proprietari_posibili, int an1, int an2, String tip, int nr_camere, List<Zona> zone) throws SQLException, DateIncomplete {
        List<Locuinta> toate_locuintele = RepoLocuinta.getAllLocuinte();
        List<Locuinta> locuinte_posibile = new ArrayList<Locuinta>();

        for (Locuinta loc : toate_locuintele) {
            if (searchZona(zone, loc.getZona()) && searchProp(proprietari_posibili, loc.getProprietar())) {
                if (abs(loc.getNr_camere() - nr_camere) <= 1 && an1 <= loc.getAn_constructie() && loc.getAn_constructie() <= an2 && loc.getTip().equals(tip)) {
                    locuinte_posibile.add(loc);
                }
            }
            else {
                throw new DateIncomplete("zona nesetata");
            }
        }
        System.out.println("S-au gasit " + locuinte_posibile.size() + " locuinte");
        for (Locuinta locuinta : locuinte_posibile) {
            System.out.println(locuinta);
        }

        return locuinte_posibile;


    }
    public void cumparaLocuinta(Cumparator cump, Locuinta locuinta) throws SQLException, IOException {
        if (cump.getSuma_bani() >= locuinta.getCost())
        {
            Proprietar prop = RepoProprietar.cautaProprietar(locuinta.getProprietar());
            int comision = 0;
            if (prop == null)
            {
                prop = RepoAgentie.cautaAgentie(locuinta.getProprietar());
                prop = ((Agentie) prop);
                comision = ((Agentie) prop).getComision();
                String nume = ((Agentie) prop).getNume();
                RepoAgentie.recalc_suma(nume, prop.getSuma_bani() + locuinta.getCost() * (100 - comision) / 100);
            }
            else
                RepoProprietar.recalc_suma(prop.getNume(), prop.getSuma_bani() + locuinta.getCost() * (100 - comision) / 100);

            RepoCumparator.recalc_suma(cump.getId(), cump.getSuma_bani() - locuinta.getCost());
            RepoLocuinta.sterge_Locuinta(locuinta.getId());
        }
        else
            System.out.println("Nu are destui bani");

    }



}
