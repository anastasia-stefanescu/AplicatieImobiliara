import serviciu.FileManagement;
import serviciu.Serviciu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Serviciu serviciu = Serviciu.getInstance();

        FileManagement audit = new FileManagement("/Users/diverse/audit.txt");



        String str = serviciu.getUserInput();
        while (!str.equals("stop")) {

            boolean ok = serviciu.processInput(str);
            if (ok) {audit.writeFile("\n" + str);}
            str = serviciu.getUserInput();
        }
    }
}