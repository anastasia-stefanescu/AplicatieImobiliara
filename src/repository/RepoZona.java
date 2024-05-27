package repository;

import model.Zona;
import serviciu.FileManagement;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepoZona {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/PAO";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD= "ihtis-aei";

    FileManagement audit = new FileManagement("/Users/diverse/audit.txt");
    static FileManagement file_zone = new FileManagement("/Users/diverse/zone.txt");


    private static final String ADD_ZONA_SQL  = "INSERT INTO ZONA(nume) values (?)";

    private static final String DEL_ZONA_SQL  = "DELETE FROM ZONA WHERE nume = ?";
    //listari
    private static final String GET_ZONE_SQL  = "SELECT * FROM ZONA";

    private static final String CAUTA_ZONE_SQL  = "SELECT * FROM ZONA WHERE nume = ?";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    public static boolean adauga_Zona(Zona zona) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(ADD_ZONA_SQL);
        preparedStatement.setString(1, zona.getNume());

        boolean ok = preparedStatement.execute();

            file_zone.writeFile(zona.toString());
            return true;

    }


    public static boolean sterge_Zona(String nume) throws SQLException, IOException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(DEL_ZONA_SQL);
        preparedStatement.setString(1, nume);

        int affectedRows = preparedStatement.executeUpdate();
            int index = file_zone.getLineNumber_byName(nume);
            file_zone.replaceLine("sters", index);
            return true;

    }

    public static List<Zona> getAllZone() throws SQLException {
        List<Zona> zone = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ZONE_SQL);

        while (resultSet.next()) {
            Zona zona = new Zona();
            //zona.setId(resultSet.getInt("id"));
            zona.setNume(resultSet.getString("nume"));
            zone.add(zona);
        }

        return zone;
    }
    public static Zona cautaZona(String nume) throws SQLException {
        Zona zona = null;
        PreparedStatement preparedStatement = getConnection().prepareStatement(CAUTA_ZONE_SQL);
        preparedStatement.setString(1, nume);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            zona = new Zona();
            //zona.setId(resultSet.getInt("id"));
            zona.setNume(resultSet.getString("nume"));
        }

        return zona;
    }
}
