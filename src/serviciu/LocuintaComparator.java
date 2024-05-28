package serviciu;

import model.Locuinta;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LocuintaComparator {
    Comparator<Locuinta> combinedComparator = Comparator
                .comparingInt(Locuinta::getCost)
                .thenComparingInt(Locuinta::getNr_camere);

    public List<Locuinta> sortComparator(List<Locuinta> locuinte) {
        Collections.sort(locuinte, combinedComparator);
        //locuinte.forEach(locuinta -> System.out.println(locuinta.getProprietar() + " - Cost: " + locuinta.getCost() + ", Nr. Camere: " + locuinta.getNr_camere()));
        return locuinte;
    }
}