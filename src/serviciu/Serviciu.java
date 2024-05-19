package serviciu;


import model.Agentie;
import model.Locuinta;
import model.Proprietar;
import model.Zona;
import model.Cumparator;

import java.util.ArrayList;
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

    public void processInput(String input) {
        //redirectioneaza la functiile necesare
        String[] words = input.split(" ");

        if (words[0].equals("adauga") || words[0].equals("sterge"))
        {
            if (words[1].equals("la"))
            { // adauga / sterge la
                switch (words[2]) {
                    case "agentie": //locuinta sau agent
                        Agentie ag = servAgentii.citeste(scanner);
                        if (words[0].equals("adauga"))
                            servAgentii.adauga_la(ag, words[3], scanner);
                        else
                            servAgentii.sterge_de_la(ag, words[3], scanner);
                        break;
                    case "proprietar": //locuinta sau agent
                        Proprietar prop = servProp.citeste(scanner);
                        if (words[0].equals("adauga"))
                            servProp.adauga_la(prop, words[3], scanner);
                        else
                            servProp.sterge_de_la(prop, words[3], scanner);
                        break;
                    case "zona": //locuinta
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
                        if (words[0].equals("adauga"))
                            servAgentii.adauga();
                        else
                            servAgentii.sterge(scanner);
                        break;
                    case "zona":
                        if (words[0].equals("adauga"))
                            servZona.adauga();
                        else
                            servZona.sterge(scanner);
                        break;
                    case "proprietar":
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
                        locuinte_gasite = setup_cautare(cump, words);
                        ok = 1;
                    }
                    if (words[1].equals("contacteaza")) { //CONTACTEAZA AGENTIE
                        Agentie agentie = servAgentii.citeste(scanner);
                        locuinte_gasite = setup_contactare(cump, agentie);
                        ok =1;
                    }
                    if (words[1].equals("modifica")) {
                        ok = 2;
                        modificare_preferinte(cump);
                    }


                    if (ok == 1) {
                        //alege locuinta de vizualizat din cele afisate
                        System.out.println("Actiuni posibile:  vizualizare locuinta  / exit ");
                        String inp = scanner.nextLine();
                        if (inp.equals("vizualizare locuinta"))
                        {
                            System.out.println("Id locuinta: ");
                            int id = scanner.nextInt();

                            for (Locuinta locuinta : locuinte_gasite)
                                if (locuinta.getId() == id) {
                                    locuinta.vizualizare();

                                    System.out.println("Actiuni posibile:  adauga favorite / x contacteaza agentie / adauga agentie/zona (la favorite) / cumpara ");
                                    inp = scanner.nextLine();
                                    switch(inp)
                                    {
                                        case "adauga favorite":
                                            cump.addLocuinta(locuinta);
                                            break;
                                        case "adauga agentie":
                                            cump.addProprietar(locuinta.getProprietar());
                                            break;
                                        case "adauga zona":
                                            cump.addZone(locuinta.getZona());
                                            break;
                                        case "cumpara":
                                            cumparaLocuinta(cump, locuinta);
                                            break;


                                    }
                                }


                        }

                        //posibil: adauga la favorite

                        //posibil: contacteaza agentie
                        //posibil: adauga agentie/agent la favorite
                        //posibil: cumpara locuinta
                        //apoi, exit automat inapoi la lista

                        //eventual, exit din lista de afisat
                    }

                }
            }
            else {
                //VIZUALIZARI
                if (words[0].equals("vizualizare")) {
                    switch (words[1]) {
                        case "zone":
                            servZona.listeaza();
                            break;
                        case "proprietari":
                            servProp.listeaza();
                            break;
                        case "agentii":
                            servAgentii.listeaza();
                            break;
                    }
                }
            }
        }

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
        System.out.println("        - adauga locuinta la favorite (dupa ce vizualizeaza locuinta)");
        System.out.println("        - contacteaza agent / proprietar (dupa ce vizualizeaza locuinta, daca nu a contactat agentie) ");
        System.out.println("            - cumpara locuinta (dupa ce contacteaza agentie/proprietar)");
        System.out.println("    - exit (se intoarce la lista afisata)");
        System.out.println(" - cumparator modifica filtre (+abonare la serviciu de notificari)");
        System.out.println(" - cumparator adauga / sterge");
        System.out.println("stop");
    }

    public List<Locuinta> setup_cautare(Cumparator cump, String[] words)
    {
        List<Locuinta> locuinte_posibile = new ArrayList<Locuinta>();
        List<Proprietar> proprietari_copy = servProp.getProprietari();//ne uitam la proprietarii privati in orice caz
        proprietari_copy.addAll(servAgentii.getAgentii());
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
            zone.addAll(cump.getZone_preferate());
        } else { //cautare dupa input
            System.out.println("Nr camere: ");
            nr_camere = scanner.nextInt();
            System.out.println("Anul minim de constructie: ");
            an1 = scanner.nextInt();
            System.out.println("Anul maxim de constructie: ");
            an2 = scanner.nextInt();
            System.out.println("Tip locuinta: ");
            tip = scanner.nextLine();

            System.out.println("Zone preferate (scrieti orice, sau stop pt oprire): ");
            String z = scanner.nextLine();
            while (z != "stop")
            {
                Zona zona = servZona.citeste(scanner);
                zone.add(zona);
            }
        }

        return cautareLocuinta(cump, proprietari_copy, an1, an2, tip, nr_camere, zone);
    }

    public List<Locuinta> setup_contactare(Cumparator cump, Agentie agentie)
    {
        List<Locuinta> locuinte_posibile = new ArrayList<Locuinta>();
        List<Proprietar> proprietari_copy = new ArrayList<Proprietar>();
        proprietari_copy.add(agentie);

        List<Zona> zone = new ArrayList<Zona>();

        System.out.println("Nr camere: ");
        int nr_camere = scanner.nextInt();
        System.out.println("Anul minim de constructie: ");
        int an1 = scanner.nextInt();
        System.out.println("Anul maxim de constructie: ");
        int an2 = scanner.nextInt();
        System.out.println("Tip locuinta: ");
        String tip = scanner.nextLine();

        System.out.println("Zone preferate (scrieti orice, sau stop pt oprire): ");
        String z = scanner.nextLine();
        while (z != "stop")
        {
            Zona zona = servZona.citeste(scanner);
            zone.add(zona);
        }

        return cautareLocuinta(cump, proprietari_copy, an1, an2, tip, nr_camere, zone);

    }

    public List<Locuinta> cautareLocuinta(Cumparator cump, List<Proprietar> proprietari_posibili, int an1, int an2, String tip, int nr_camere, List<Zona> zone)
    {
        List<Locuinta> locuinte_posibile = new ArrayList<Locuinta>();

        for (Proprietar proprietar : proprietari_posibili) {
            List<Locuinta> locuinte_copy = proprietar.getLocuinte();
            for (Locuinta locuinta : locuinte_copy) {
                if (abs(locuinta.getNr_camere() - nr_camere) <= 1 && an1 <= locuinta.getAn_constructie() && locuinta.getAn_constructie() <= an2 && locuinta.getTip() == tip) {
                    if (zone.contains(locuinta.getZona()))
                        if (cump.getSuma_bani() >= locuinta.getCost())
                            locuinte_posibile.add(locuinta);
                }
            }
        }

        System.out.println("S-au gasit " + locuinte_posibile.size() + " locuinte");
        for (Locuinta locuinta : locuinte_posibile) {
            System.out.println(locuinta);
        }

        return locuinte_posibile;
    }

    public void modificare_preferinte(Cumparator cump)
    {
        System.out.println("Preferintele dvs. curente:");
        System.out.println("Tip: " + cump.getTip());
        System.out.println("Nr camere: " + cump.getNr_camere());
        System.out.println("Ani constructie: " + cump.getAn1() + " - " + cump.getAn2());

        System.out.println("Ce doriti sa modificati (stop pt oprire): ");
        String z = scanner.nextLine();
        while (! z.equals("stop"))
        {
            switch(z)
            {
                case "tip":
                    System.out.println("Noul tip: ");
                    String tip = scanner.nextLine();
                    if (tip.equals("apartament") || tip.equals("casa"))
                        cump.setTip(tip);
                case "an1":
                    System.out.println("Noul an minim: ");
                    int an1 = scanner.nextInt();
                    cump.setAn1(an1);
                case "an2":
                    System.out.println("Noul an maxim: ");
                    int an2 = scanner.nextInt();
                    cump.setAn2(an2);
                case "nr_camere":
                    System.out.println("Noul numar de camere ");
                    int nr  = scanner.nextInt();
                    cump.setNr_camere(nr);
                default:
                    System.out.println("Input incorect ");
            }
            z = scanner.nextLine();
        }
    }

    public void cumparaLocuinta(Cumparator cump, Locuinta locuinta)
    {
        if (locuinta.getProprietar() instanceof Agentie) {
            Agentie ag = (Agentie) locuinta.getProprietar();
            servAgentii.sterge_de_la(ag, "locuinta", scanner);
            ag.setSuma_bani(ag.getSuma_bani() + locuinta.getCost());
        }

        else {
            Proprietar proprietar = locuinta.getProprietar();
            servProp.sterge_de_la(proprietar,"locuinta", scanner);
            proprietar.setSuma_bani(proprietar.getSuma_bani() + locuinta.getCost());
        }
        cump.setSuma_bani(cump.getSuma_bani() - locuinta.getCost());

    }



}
