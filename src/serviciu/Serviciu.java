package serviciu;

import model.Agentie;
import model.Proprietar;
import model.Zona;

import java.util.Scanner;
public class Serviciu {
    private static Serviciu instanta;
    private Scanner scanner;

    private Agentie[] agentii;
    private Proprietar[] proprietari;
    private Zona[] zone;

    private Serviciu(){
        scanner = new Scanner(System.in);

        listPossibleOperations();
    }

    public static Serviciu getInstance() {
        if (instanta == null) {
            instanta = new Serviciu();
        }
        return instanta;
    }

    public String getUserInput() {
        System.out.print("Actiune : ");
        return scanner.nextLine();
    }

    public void processInput(String input) {
        //redirectioneaza la functiile necesare
        String[] words = input.split(" ");

        if (words[0] == "adauga" || words[0] == "sterge") {
            if (words[1] == "la") {
                if (words[2] == "agentie") {
                    if (words[3] == "agent") {

                    }
                    if (words[3] == "locuinta") {

                    }
                }
                if (words[2] == "proprietar")
                { //adaugam locuinta
                }
                if (words[2] == "zona")
                {//adaugam locuinta

                }
            }
            else {
                if (words[1] == "agentie") {
                    Agentie ag = new Agentie();
                    //agentii
                }
                if (words[1] == "zona"){

                }
                if (words[1] == "proprietar")
                {

                }
            }
        }
        if (words[0] == "cumparator"){
            int ok = 0;
            if (words[1] == "cauta"){
                if (words[2] != null)
                {

                }
                else {

                }
                ok = 1;
            }
            if(words[1] == "contacteaza"){
                ok = 1;
            }
            if (words[1] == "modifica")
            {
                ok = 2;
                //afiseaza filtre curente, alege ce sa schimbi
            }

            if (ok == 1)
            {
                //alege locuinta de vizualizat din cele afisate

                //posibil: adauga la favorite

                //posibil: contacteaza agentie
                    //posibil: adauga agentie/agent la favorite
                    //posibil: cumpara locuinta
                //apoi, exit automat inapoi la lista

                //eventual, exit din lista de afisat
            }
        }

    }

    public void listPossibleOperations()
    {
        System.out.println("Optiuni actiuni (scrieti in cuvinte ce actiune vreti sa executati:");
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
        System.out.println("stop");
    }


    //adauga/scoate agent, proprietar, agentie, locuinta, zona

    //adauga la:

    //scoate de la:

    //cumparator cauta dupa filtre

    //cumparator modifica favorite

    //cumparator contacteaza agent

    //cumparator cumpara

    //cumparator se aboneaza la serviciul de notificari

    //

    //constructor privat (pivat se mai foloseste si la singleton

}
