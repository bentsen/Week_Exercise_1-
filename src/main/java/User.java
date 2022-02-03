import java.util.ArrayList;

public class User
{
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String adress;

    private UserLogic userMap = new UserLogic();

    public User(String firstName, String lastName, String password, String phoneNumber, String adress)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.adress = adress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAdress() {
        return adress;
    }
}
