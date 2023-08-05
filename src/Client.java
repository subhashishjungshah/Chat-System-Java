import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;

public class Client {

    Socket socket;
    BufferedReader br;
    PrintWriter out;
    public Client(){
        try{
            socket = new Socket("127.0.0.1",5000);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            startReading();
            startWriting();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void startWriting(){
//        We need to use a thread for continously reading
        System.out.println("Client has started Writing");
        Runnable r1 = ()->{
            while(true) {
                try{
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        };
        new Thread(r1).start();
    }
    public void startReading(){
//        We need a thread for continuously writing
        System.out.println("Client has started Reading");
        Runnable r2 = ()->{
            while(true){
                try{
                    String message = br.readLine();
                    if(message.equals("exit")){
                        System.out.println("Server terminated the chat");
                        break;
                    }
                    System.out.println("Server:" + message);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
       new Thread(r2).start();
    }
    public static void main(String[] args) {
        System.out.println("This is client side for our chat Application");
        new Client();
    }
}
