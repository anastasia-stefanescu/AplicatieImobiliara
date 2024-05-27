package repository;

import model.Agentie;
import serviciu.FileManagement;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class RepoAgentie {

    String filePath = "path/to/your/file.txt";

    // Create a File object
    FileManagement audit = new FileManagement("/Users/diverse/audit.txt");
    static FileManagement fisier_agentii = new FileManagement("/Users/diverse/agentii.txt");



    private static int nr_agentii = 0;

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/PAO";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD= "ihtis-aei";


    private static final String ADD_AGENTIE_SQL  = "INSERT INTO AGENTIE(nume, suma_bani, comision) values (?, ?, ?)";

    private static final String DEL_AGENTIE_SQL  = "DELETE FROM AGENTIE WHERE nume = ?";

    private static final String GET_AGENTII_SQL  = "SELECT * FROM AGENTIE";
    private static final String CAUTA_AGENTII_SQL  = "SELECT * FROM AGENTIE WHERE nume = ?";
    private static final String UPDATE_SUMA = "UPDATE AGENTIE SET suma_bani = ? WHERE nume = ?";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }


    public static boolean adauga_Agentie(Agentie agentie) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(ADD_AGENTIE_SQL);
        preparedStatement.setString(1, agentie.getNume());
        preparedStatement.setInt(2, agentie.getSuma_bani());
        preparedStatement.setInt(3, agentie.getComision());

        fisier_agentii.writeFile(agentie.toString());

        return preparedStatement.execute();
    }
    public static boolean sterge_Agentie(String nume) throws SQLException, IOException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(DEL_AGENTIE_SQL);
        preparedStatement.setString(1, nume);

        fisier_agentii.replaceLine("sters", fisier_agentii.getLineNumber_byName(nume));

        boolean ok = preparedStatement.execute();

        return ok;
    }

    public static List<Agentie> getAllAgentii() throws SQLException {
        List<Agentie> agentii = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(GET_AGENTII_SQL);

        while (resultSet.next()) {
            Agentie agentie = new Agentie();
            //agentie.setId(resultSet.getInt("id"));
            agentie.setNume(resultSet.getString("nume"));
            agentie.setSuma_bani(resultSet.getInt("suma_bani"));
            agentie.setComision(resultSet.getInt("comision"));
            agentii.add(agentie);
        }

        return agentii;
    }

    public static Agentie cautaAgentie(String nume) throws SQLException {
        Agentie agentie = null;
        PreparedStatement preparedStatement = getConnection().prepareStatement(CAUTA_AGENTII_SQL);
        preparedStatement.setString(1, nume);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            agentie = new Agentie();
            //agentie.setId(resultSet.getInt("id"));
            agentie.setNume(resultSet.getString("nume"));
            agentie.setSuma_bani(resultSet.getInt("suma_bani"));
            agentie.setComision(resultSet.getInt("comision"));

        }

        return agentie;


    }

    public static boolean recalc_suma(String nume, int suma_noua) throws SQLException, IOException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_SUMA);
        preparedStatement.setInt(1, suma_noua);
        preparedStatement.setString(2, nume);

        int ok = preparedStatement.executeUpdate();

            Agentie ag = cautaAgentie(nume);
            fisier_agentii.replaceLine(ag.toString(), fisier_agentii.getLineNumber_byName(nume));
        return ok > 0;
    }
}
