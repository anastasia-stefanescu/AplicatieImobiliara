package serviciu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public interface InterfataOperatii_complexe<T> {
    void adauga_la(T obj1, String tip, Scanner s) throws SQLException, IOException;
    void sterge_de_la(T obj1, String tip, Scanner s) throws SQLException, IOException;


}
