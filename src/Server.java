import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket server;
    Socket socket;

    BufferedReader br;
    PrintWriter out;
    public Server(){
        try{
            server = new ServerSocket(5000);
            System.out.println("Server is Ready to accept Connection");
            System.out.println("Waiting...");
            socket = server.accept();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.println("Server is staring inside your computer");

        }
}