import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 * Created by Anton on 2016-12-06.
 */
public class Client {
    public static void main(String[] args) {
        Random rand = new Random();
        long timeStart = System.currentTimeMillis();
        Socket socket = null;
        PrintWriter pw = null;

        try {
            socket = new Socket("127.0.0.1", 12345);

            pw = new PrintWriter(socket.getOutputStream());

            for(int i = 0; i < 100; i++){
                pw.println(Math.abs(rand.nextInt()%512));
            }
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally
        {
            try {
                if(socket!=null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            pw.close();
        }
    }
}
