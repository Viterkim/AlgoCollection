import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {


    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String content = "bingomanden";
        int c1 = 85;
        Integer c2 = 85;
        System.out.println(content.hashCode());
    }
}
