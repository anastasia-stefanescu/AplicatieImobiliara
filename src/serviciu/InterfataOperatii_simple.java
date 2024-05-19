package serviciu;

import java.util.Optional;
import java.util.Scanner;

public interface InterfataOperatii_simple<T> {
    void adauga();
    void sterge(Scanner scanner);
    void listeaza();
    T citeste(Scanner s);
    Optional<T> cauta(String identificator);
}
