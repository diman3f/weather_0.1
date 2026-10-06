import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.util.List;

public class HelloService {

    private String name;

    public static void main(String[] args) {
        String password = "123";
        final String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashpw);
        System.out.println(BCrypt.checkpw(password,hashpw));

    }
}
