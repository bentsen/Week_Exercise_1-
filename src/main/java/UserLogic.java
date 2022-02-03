import DBConnector.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserLogic
{
    public  ArrayList<User> getAllUsersNames()
    {
        User tmpUser = null;
        ArrayList<User> userList = new ArrayList<>();
        try (Connection connection = Database.connection())
        {
            String sql  = " SELECT * FROM usertable";

            try(PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery(sql);
                if(rs.next())
                {
                    String firstName = rs.getString("fname");
                    String lastName = rs.getString("lname");
                    String password = rs.getString("pw");
                    String phoneNumber = rs.getString("phone");
                    String adress = rs.getString("address");

                    tmpUser = new User(firstName,lastName,password,phoneNumber,adress);
                    userList.add(tmpUser);
                }
                return userList;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String printAllUsersFistNames()
    {
        ArrayList<User> userList = getAllUsersNames();
        StringBuilder users = new StringBuilder();

        userList.stream().forEach((e) -> users.append(e.getFirstName()));

        return users.toString();
    }

    public String getUserDetails(String name)
    {
        ArrayList<User> userList = getAllUsersNames();
        StringBuilder details = new StringBuilder();

        userList.stream()
                .filter(e -> name.equals(e.getFirstName()))
                .findFirst()
                .stream()
                .forEach((e) -> details.append(e.getAdress() + " " + e.getPhoneNumber()));

        return details.toString();
    }
}
