package repository;

import model.Agentie;
import model.Proprietar;
import model.Cumparator;
import model.Zona;
import serviciu.FileManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RepoProprietar {

    private static int nr_proprietari = 0;

    FileManagement audit = new FileManagement("/Users/diverse/audit.txt");
    static FileManagement file_proprietari = new FileManagement("/Users/diverse/proprietari.txt");

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/PAO";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD= "ihtis-aei";

    private static final String ADD_PROPRIETAR_SQL  = "INSERT INTO PROPRIETAR(nume, suma_bani) values (?, ?)";
    private static final String DEL_PROPRIETAR_SQL  = "DELETE FROM PROPRIETAR WHERE nume = ?";
   private static final String GET_PROPRIETARI_SQL  = "SELECT * FROM PROPRIETAR";
   private static final String CAUTA_PROPRIETARI_SQL  = "SELECT * FROM PROPRIETAR WHERE nume = ?";

    private static final String UPDATE_SUMA = "UPDATE PROPRIETAR SET suma_bani = ? WHERE nume = ?";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    public static boolean adauga_Proprietar(Proprietar proprietar) throws SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement(ADD_PROPRIETAR_SQL);
        nr_proprietari = nr_proprietari+1;
        preparedStatement.setString(1, proprietar.getNume());
        preparedStatement.setInt(2, proprietar.getSuma_bani());

        boolean ok = preparedStatement.execute();

            file_proprietari.writeFile(proprietar.toString());
       return ok;
    }


    public static boolean sterge_Proprietar(String nume) throws SQLException, IOException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(DEL_PROPRIETAR_SQL);
        preparedStatement.setString(1, nume);

        int affectedRows = preparedStatement.executeUpdate();
        int index = file_proprietari.getLineNumber_byName(nume);
            file_proprietari.replaceLine("sters", index);
            return affectedRows > 0;

    }

    public static List<Proprietar> getAllProprietari() throws SQLException {
        List<Proprietar> proprietari = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(GET_PROPRIETARI_SQL);

        while (resultSet.next()) {
            Proprietar proprietar = new Proprietar();
            //proprietar.setId(resultSet.getInt("id"));
            proprietar.setNume(resultSet.getString("nume"));
            proprietar.setSuma_bani(resultSet.getInt("suma_bani"));
            proprietari.add(proprietar);
        }

        return proprietari;
    }


    public static Proprietar cautaProprietar(String nume) throws SQLException {
        Proprietar proprietar = null;
        PreparedStatement preparedStatement = getConnection().prepareStatement(CAUTA_PROPRIETARI_SQL);
        preparedStatement.setString(1, nume);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            proprietar = new Proprietar();
            //proprietar.setId(resultSet.getInt("id"));
            proprietar.setNume(resultSet.getString("nume"));
            proprietar.setSuma_bani(resultSet.getInt("suma_bani"));

        }

        return proprietar;
    }


    public static boolean recalc_suma(String nume, int suma_noua) throws SQLException, IOException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_SUMA);
        preparedStatement.setInt(1, suma_noua);
        preparedStatement.setString(2, nume);

        int affectedRows = preparedStatement.executeUpdate();
            int index = file_proprietari.getLineNumber_byName(nume);
            Proprietar proprietar = cautaProprietar(nume);
            file_proprietari.replaceLine(proprietar.toString(), index);

        return affectedRows > 0;
    }


}
