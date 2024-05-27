package repository;

import model.Cumparator;
import model.Locuinta;
import model.Proprietar;
import model.Zona;
import serviciu.FileManagement;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;


public class RepoLocuinta {

    private static int nr_locuinte = 0;

    FileManagement audit = new FileManagement("/Users/diverse/audit.txt");
    static FileManagement file_locuinte = new FileManagement("/Users/diverse/locuinte.txt");

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/PAO";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "ihtis-aei";

    private static final String ADD_LOCUINTA_SQL = "INSERT INTO LOCUINTA(id, proprietar, zona, tip, an_constructie, nr_camere, suprafata, adresa, cost) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DEL_LOCUINTA_SQL = "DELETE FROM LOCUINTA WHERE id = ?";
    //trb sa returneze id

    private static final String GET_LOCUINTE_SQL = "SELECT * FROM LOCUINTA";

    private static final String GET_COUNT = "SELECT COUNT(*) FROM LOCUINTA";



    private static final String ADD_LOCUINTA_LA_PROP_SQL = "UPDATE LOCUINTA SET proprietar = ? WHERE id = ? ";
    private static final String ADD_LOCUINTA_LA_AG_SQL = "UPDATE LOCUINTA SET proprietar = ? WHERE id = ? ";
    private static final String ADD_LOCUINTA_ZONA_SQL = "UPDATE LOCUINTA SET zona = ? WHERE id = ? ";

    private static final String DEL_LOCUINTA_LA_PROP_SQL = "UPDATE LOCUINTA SET proprietar = NULL WHERE id = ? ";
    private static final String DEL_LOCUINTA_LA_AG_SQL = "UPDATE LOCUINTA SET proprietar = NULL WHERE id = ? ";
    private static final String DEL_LOCUINTA_ZONA_SQL = "UPDATE LOCUINTA SET zona = NULL WHERE id = ? ";

    private static final String CAUTA_LOCUINTA_SQL = "SELECT * FROM LOCUINTA WHERE id = ?";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    public static boolean adauga_Locuinta(Locuinta locuinta) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(ADD_LOCUINTA_SQL);
        nr_locuinte++;
        locuinta.setId(nr_locuinte);
        preparedStatement.setInt(1, nr_locuinte);
        preparedStatement.setString(2, locuinta.getProprietar());
        preparedStatement.setString(3, locuinta.getZona());
        preparedStatement.setString(4, locuinta.getTip());
        preparedStatement.setInt(5, locuinta.getAn_constructie());
        preparedStatement.setInt(6, locuinta.getNr_camere());
        preparedStatement.setInt(7, locuinta.getSuprafata());
        preparedStatement.setString(8, locuinta.getAdresa());
        preparedStatement.setInt(9, locuinta.getCost());

        try {
            boolean ok = preparedStatement.execute();

                file_locuinte.writeFile(locuinta.toString());
                return  ok;
        }catch (SQLException e) {
            return adauga_Locuinta(locuinta);
        }
    }

    public static boolean sterge_Locuinta(int id) throws SQLException, IOException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(DEL_LOCUINTA_SQL);
        preparedStatement.setInt(1, id);
        int affectedRows = preparedStatement.executeUpdate();

        file_locuinte.replaceLine("sters", id-1);
        return  affectedRows > 0;
    }

    public static boolean actualizeaza_Locuinta_Proprietar(int locuintaId, String nume_proprietar) throws SQLException, IOException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(ADD_LOCUINTA_LA_PROP_SQL);
        preparedStatement.setString(1, nume_proprietar);
        preparedStatement.setInt(2, locuintaId);

        int affectedRows = preparedStatement.executeUpdate();
        Locuinta loc = cautaLocuinta(locuintaId);
        file_locuinte.replaceLine(loc.toString(), locuintaId-1);
        return  affectedRows > 0;
    }

    public static boolean actualizeaza_Locuinta_Agentie(int locuintaId, String nume_agentie) throws SQLException, IOException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(ADD_LOCUINTA_LA_AG_SQL);
        preparedStatement.setString(1, nume_agentie);
        preparedStatement.setInt(2, locuintaId);

        int affectedRows = preparedStatement.executeUpdate();
        Locuinta loc = cautaLocuinta(locuintaId);
        file_locuinte.replaceLine(loc.toString(), locuintaId-1);
        return  affectedRows > 0;
    }

    public static boolean actualizeaza_Locuinta_Zona(int locuintaId, String nume_zona) throws SQLException, IOException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(ADD_LOCUINTA_ZONA_SQL);
        preparedStatement.setString(1, nume_zona);
        preparedStatement.setInt(2, locuintaId);

        int affectedRows = preparedStatement.executeUpdate();
            Locuinta loc = cautaLocuinta(locuintaId);
            file_locuinte.replaceLine(loc.toString(), locuintaId-1);
            return  affectedRows > 0;
    }

    public static boolean elimina_Proprietar_Locuinta(int locuintaId) throws SQLException, IOException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(DEL_LOCUINTA_LA_PROP_SQL);
        preparedStatement.setInt(1, locuintaId);

        int affectedRows = preparedStatement.executeUpdate();
            Locuinta loc = cautaLocuinta(locuintaId);
            file_locuinte.replaceLine(loc.toString(), locuintaId-1);
            return affectedRows > 0;

    }

    public static boolean elimina_Agentie_Locuinta(int locuintaId) throws SQLException, IOException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(DEL_LOCUINTA_LA_AG_SQL);
        preparedStatement.setInt(1, locuintaId);

        int affectedRows = preparedStatement.executeUpdate();
            Locuinta loc = cautaLocuinta(locuintaId);
            file_locuinte.replaceLine(loc.toString(), locuintaId-1);
            return affectedRows>0;

    }

    public boolean elimina_Zona_Locuinta(int locuintaId) throws SQLException, IOException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(DEL_LOCUINTA_ZONA_SQL);
        preparedStatement.setInt(1, locuintaId);

        int affectedRows = preparedStatement.executeUpdate();

            Locuinta loc = cautaLocuinta(locuintaId);
            file_locuinte.replaceLine(loc.toString(), locuintaId-1);
            return affectedRows > 0;

    }

    public static List<Locuinta> getAllLocuinte() throws SQLException {
        List<Locuinta> locuinte = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(GET_LOCUINTE_SQL);

        while (resultSet.next()) {
            Locuinta loc = new Locuinta();
            loc.setId(resultSet.getInt("id"));
            loc.setCost(resultSet.getInt("cost"));
            loc.setProprietar(resultSet.getString("proprietar"));
            loc.setZona(resultSet.getString("zona"));
            loc.setTip(resultSet.getString("tip"));
            loc.setAn_constructie(resultSet.getInt("an_constructie"));
            loc.setSuprafata(resultSet.getInt("suprafata"));
            loc.setAdresa(resultSet.getString("adresa"));
            loc.setNr_camere(resultSet.getInt("nr_camere"));

            locuinte.add(loc);
        }

        return locuinte;
    }


    public static Locuinta cautaLocuinta(int id) throws SQLException {
        Locuinta locuinta = null;
        PreparedStatement preparedStatement = getConnection().prepareStatement(CAUTA_LOCUINTA_SQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            locuinta = new Locuinta();
            locuinta.setId(resultSet.getInt("id"));
            locuinta.setCost(resultSet.getInt("cost"));
            locuinta.setProprietar(resultSet.getString("proprietar"));
            locuinta.setZona(resultSet.getString("zona"));
            locuinta.setTip(resultSet.getString("tip"));
            locuinta.setAn_constructie(resultSet.getInt("an_constructie"));
            locuinta.setSuprafata(resultSet.getInt("suprafata"));
            locuinta.setAdresa(resultSet.getString("adresa"));
            locuinta.setNr_camere(resultSet.getInt("nr_camere"));


        }

        return locuinta;
    }




}
