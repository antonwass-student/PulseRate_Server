import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Anton on 2016-12-06.
 */
public class PRService {
    private ServerSocket serverSocket;
    private boolean listening = false;

    public PRService(int port) throws Exception {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException{
        listening = true;
        while(listening){
            System.out.println("Listening for a client...");
            Socket client = serverSocket.accept();
            System.out.println("Client accepted.");
            try{
                handleClient(client);
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    private void handleClient(Socket socket) throws  IOException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");

        PrintWriter pw = new PrintWriter(new FileWriter(sdf.format(Calendar.getInstance().getTime()) + ".dat"));

        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        try {
            System.out.println("Reading from client...");
            String line;
            while((line = bufferedReader.readLine()) != null){
                pw.println(line);
                pw.flush();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            //Close streams
            try{
                bufferedReader.close();
            }catch(Exception e){
                e.printStackTrace();
            }

            try{
                pw.close();
            }catch(Exception e){
                e.printStackTrace();
            }

            System.out.println("Client connection closed.");
        }


    }
}
