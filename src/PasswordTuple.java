/**
 * @author Dylan Bannon <drb2857@rit.edu>
 */
public class PasswordTuple {
    private String user;
    private String password;
    private String hash;

    public PasswordTuple(String user, String password, String hash) {
        this.user = user;
        this.password = password;
        this.hash = hash;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getHash() {
        return hash;
    }
}
