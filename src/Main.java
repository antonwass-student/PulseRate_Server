import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) {
        try {
            PRService prs = new PRService(12345);
            prs.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
