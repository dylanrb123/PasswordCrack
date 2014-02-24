/**
 * @author Dylan Bannon <drb2857@rit.edu>
 */
public class PasswordTuple {
    private String password;
    private String hash;

    public PasswordTuple(String password, String hash) {
        this.password = password;
        this.hash = hash;
    }

    public String getPassword() {
        return password;
    }

    public String getHash() {
        return hash;
    }
}
