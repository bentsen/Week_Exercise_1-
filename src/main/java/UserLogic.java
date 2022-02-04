import DBConnector.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserLogic
{
    public  ArrayList<User> getAllUsers()
    {
        User tmpUser = null;
        ArrayList<User> userList = new ArrayList<>();
        try
        {
            Connection connection = Database.connection();
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
        ArrayList<User> userList = getAllUsers();
        StringBuilder users = new StringBuilder();

        userList.stream().forEach((e) -> users.append(e.getFirstName()));

        return users.toString();
    }

    public User getUserByName(String name)
    {
        ArrayList<User> userList = getAllUsers();
        StringBuilder details = new StringBuilder();

       User usertmp = userList.stream()
                .filter(e -> name.equals(e.getFirstName()))
                .findFirst()
                .get();

        return usertmp;
    }

    public void editUserDetails(User user)
    {
        try
        {
            Connection connection = Database.connection();
            String sql = "UPDATE user SET fname=?, lname=?, pw=?, phone=?, address=?";
            try(PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getPhoneNumber());
                ps.setString(5, user.getAdress());
                ps.executeUpdate();
            }
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
