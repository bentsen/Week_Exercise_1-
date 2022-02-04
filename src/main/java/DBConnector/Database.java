package DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
        private static String URL = "jdbc:mysql://64.225.108.4:3306/startcode_test?serverTimezone=Europe/Copenhagen";
        private static String USER = "root";
        private static String PW = "Mikkel405!";

        private static Connection singleton;

        public static void setConnection( Connection con ) {
            singleton = con;
        }

        public static Connection connection() throws ClassNotFoundException, SQLException {
            if ( singleton == null ) {
                Class.forName( "com.mysql.cj.jdbc.Driver" );
                singleton = DriverManager.getConnection( URL, USER, PW );
            }
            return singleton;
        }

    }

