import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ahsan on 12/5/16.
 */

//Server read message from connected clients and broadcast it to all other connections
public class ConcurrentServer {

    private static int maxClients = 7;
    private static ArrayList<StreamModeServer> sms;
    public static void main(String[] args) throws IOException{

    ServerSocket connectionSocket = new ServerSocket(8700);
        //th = new Thread[maxClients];
        sms = new ArrayList<StreamModeServer>();
    while(true){
        //wait for accepting connections
       // Socket s = connectionSocket.accept();
       // StreamModeServer ss = connectionSocket.accept();
       StreamModeServer datasock = new StreamModeServer(connectionSocket.accept());// .accept() is blocking
        sms.add(datasock);
        Thread th = new Thread(new ServerThread(datasock));
        th.start();
        // now wait for next client
        if(sms.size()>=maxClients)
        break;
    }
    connectionSocket.close();

}

    public static class ServerThread implements Runnable{
        StreamModeServer newClient;

        public ServerThread(StreamModeServer ds){
        this.newClient=ds;
        }
        public void run(){
            try {


                while(true){
                    //System.out.print(newClient.client+" Typing...   ");
                    String msg = newClient.receiveMsg();//blocking method
                    if(msg.contains("disconnect"))
                    {   System.out.println(newClient.client+" Offline " );
                        newClient.sendMsg("Bye...");
                        break;
                    }
                    System.out.println(newClient.client+" : "+msg);
                    Iterator it = sms.iterator();
                    while(it.hasNext()){
                        StreamModeServer ss = (StreamModeServer)it.next();
                        ss.sendMsg(newClient.client +" said : "+msg);
                    }

                }
                newClient.close();
                }
            catch(IOException e){
                e.printStackTrace();
            }

        }

    }

}
