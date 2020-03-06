package TahakkukAnalizPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    Connection connection;

    DbConnect(){
//        String db_adres="localhost";
        String db_adres="10.10.10.28";
        try {
            connection= DriverManager.getConnection("jdbc:mariadb://"+db_adres+":3306/bash?user=root&password=1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
