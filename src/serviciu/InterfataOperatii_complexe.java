package serviciu;

import java.util.Optional;
import java.util.Scanner;

public interface InterfataOperatii_complexe<T> {
    void adauga_la(T obj1, String tip, Scanner s);
    void sterge_de_la(T obj1, String tip, Scanner s);


}
