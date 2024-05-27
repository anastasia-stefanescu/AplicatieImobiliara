package repository;

import model.Cumparator;
import serviciu.FileManagement;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepoCumparator {

    private static int nr_cumparatori = 0;

    FileManagement audit = new FileManagement("/Users/diverse/audit.txt");
    static FileManagement file_cumparatori = new FileManagement("/Users/diverse/cumparatori.txt");

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/PAO";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD= "ihtis-aei";

    private static final String ADD_CUMP_SQL  = "INSERT INTO CUMPARATOR(id, nume, suma_bani, an1, an2, tip, nr_camere) values (?, ?, ?, ?, ?, ?, ?)";

    private static final String DEL_CUMP_SQL  = "DELETE FROM AGENTIE WHERE id = ?";

    //listari
    private static final String GET_CUMPARATORI_SQL  = "SELECT * FROM CUMPARATOR";

    private static final String CAUTA_CUMPARATORI_SQL  = "SELECT * FROM CUMPARATOR WHERE id = ?";

    private static final String UPDATE_SUMA = "UPDATE CUMPARATOR SET suma_bani = ? WHERE id = ?";

    private static final String UPDATE_PREF = "UPDATE CUMPARATOR SET an1 = ?, an2 = ?, nr_camere = ?, tip = ? WHERE id = ?";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    public static boolean adauga_Cumparator(Cumparator cumparator) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(ADD_CUMP_SQL);
        nr_cumparatori++;
        cumparator.setId(nr_cumparatori);
        preparedStatement.setInt(1, nr_cumparatori);
        preparedStatement.setString(2, cumparator.getNume());
        preparedStatement.setInt(3, cumparator.getSuma_bani());
        preparedStatement.setInt(4, cumparator.getAn1());
        preparedStatement.setInt(5, cumparator.getAn2());
        preparedStatement.setString(6, cumparator.getTip());
        preparedStatement.setInt(7, cumparator.getNr_camere());

        try {

            boolean ok = preparedStatement.execute();
            file_cumparatori.writeFile(cumparator.toString());
            return true;
        }catch (SQLException e){
            var b = adauga_Cumparator(cumparator);
            return b;
        }
    }

    public static boolean sterge_Cumparator(int id) throws SQLException, IOException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(DEL_CUMP_SQL);
        preparedStatement.setInt(1, id);

        int affectedRows = preparedStatement.executeUpdate();
        file_cumparatori.replaceLine("sters", id-1);
        return true;

    }

    public static boolean recalc_suma(int id, int suma_noua) throws SQLException, IOException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_SUMA);
        preparedStatement.setInt(1, suma_noua);
        preparedStatement.setInt(2, id);

        boolean ok = preparedStatement.execute();
        Cumparator cump = cautaCumparator(id);
            file_cumparatori.replaceLine(cump.toString(), id-1);
            return true;

    }




    public static List<Cumparator> getAllCumparatori() throws SQLException {
        List<Cumparator> cumparatori = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(GET_CUMPARATORI_SQL);

        while (resultSet.next()) {
            Cumparator cumparator = new Cumparator();
            cumparator.setId(resultSet.getInt("id"));
            cumparator.setNume(resultSet.getString("nume"));
            cumparator.setSuma_bani(resultSet.getInt("suma_bani"));
            cumparator.setAn1(resultSet.getInt("an1"));
            cumparator.setAn2(resultSet.getInt("an2"));
            cumparator.setTip(resultSet.getString("tip"));
            cumparator.setNr_camere(resultSet.getInt("nr_camere"));
            cumparatori.add(cumparator);
        }

        return cumparatori;
    }

    public static Cumparator cautaCumparator(int id) throws SQLException {
        Cumparator cumparator = null;
        PreparedStatement preparedStatement = getConnection().prepareStatement(CAUTA_CUMPARATORI_SQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            cumparator = new Cumparator();
            cumparator.setId(resultSet.getInt("id"));
            cumparator.setNume(resultSet.getString("nume"));
            cumparator.setSuma_bani(resultSet.getInt("suma_bani"));
            cumparator.setAn1(resultSet.getInt("an1"));
            cumparator.setAn2(resultSet.getInt("an2"));
            cumparator.setTip(resultSet.getString("tip"));
            cumparator.setNr_camere(resultSet.getInt("nr_camere"));

        }

        return cumparator;


    }

    public static boolean update_preferinte(int id, int an1, int an2, int nr_camere, String tip) throws SQLException, IOException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_PREF);
        preparedStatement.setInt(1, an1);
        preparedStatement.setInt(2, an2);
        preparedStatement.setInt(3, nr_camere);
        preparedStatement.setString(4, tip);
        preparedStatement.setInt(5, id);

        boolean ok = preparedStatement.execute();

            Cumparator cump = cautaCumparator(id);
            int index = file_cumparatori.getLineNumber_byName(String.valueOf(id));
            file_cumparatori.replaceLine(cump.toString(), index);
        return ok;
    }


}
