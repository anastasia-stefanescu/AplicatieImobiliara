package serviciu;

import model.Proprietar;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public interface InterfataOperatii_simple<T> {
    void adauga()throws SQLException;
    void sterge(Scanner scanner) throws SQLException, IOException;
    void listeaza() throws SQLException;
    T citeste(Scanner s) throws SQLException;
    T cauta(String identificator) throws SQLException;
}
