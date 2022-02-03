import DBConnector.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test
{

    private UserLogic userMap;

    @org.junit.jupiter.api.BeforeEach
    void setUp()
    {
        System.out.println("TESTINNNNGGGG");
        Connection con = null;
        try {
            con = Database.connection();
            String createTable = "CREATE TABLE IF NOT EXISTS `startcode_test`.`usertable` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `fname` VARCHAR(45) NULL,\n" +
                    "  `lname` VARCHAR(45) NULL,\n" +
                    "  `pw` VARCHAR(45) NULL,\n" +
                    "  `phone` VARCHAR(45) NULL,\n" +
                    "  `address` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`id`));";
            con.prepareStatement(createTable).executeUpdate();
            String SQL = "INSERT INTO startcode_test.usertable (fname, lname, pw, phone, address) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Hans");
            ps.setString(2, "Hansen");
            ps.setString(3, "Hemmelig123");
            ps.setString(4, "40404040");
            ps.setString(5,"Rolighedsvej 3");
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("DONE");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() throws SQLException, ClassNotFoundException {
        Connection connection = Database.connection();
        try {
            String droptable = "DROP TABLE IF EXISTS `startcode_test`.`usertable`";
            connection.prepareStatement(droptable).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void Test()
    {
        UserLogic userLogic = new UserLogic();

        assertEquals("Hans",userLogic.printAllUsersFistNames());
    }

    @org.junit.jupiter.api.Test
    void Test2()
    {
        UserLogic userLogic = new UserLogic();

        assertEquals("Rolighedsvej 3 40404040",userLogic.getUserDetails("Hans"));
    }
}