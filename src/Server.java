import java.io.BufferedReader;
import java.io.InputStreamReader;
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

//            Input stream Reader converts the byte code into ASCII character and load as a buffer. Buffer reader reads the data
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            startReading();
            startWriting();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void startReading(){
//        Let's make one thread for continously reading
        Runnable r1 = () ->{
            System.out.println("Reader Started...");
            while(true){
                try{
                    String message = br.readLine();
                    if(message.equals("quit")){
                        System.out.println("Client terminated the chat");
                        break;
                    }
                    System.out.println("Client:" + message);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        new Thread(r1).start();
    }
    public void startWriting(){
//        Let's make another thread for continuously writing
        Runnable r2 = () ->{
            System.out.println("Writer Started");
            while (true){
                try{
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        new Thread(r2).start();
    }


    public static void main(String[] args) {

        System.out.println("Server is staring inside your computer");
        new Server();

        }
}