
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;



/**
 * Created by ahsan on 12/5/16.
 */
/*
javac *.java
java ConcurrentServer
(Server broadcast the message it received)

open 2-3 terminals for running clients
java ThreadedClient 127.0.0.1 8700

 */



// DataSocket
public class StreamModeServer extends Socket {

    private Socket datasocket;
    private BufferedReader input;
    private PrintWriter output;
    String client;
    public StreamModeServer(InetAddress ip, int port) throws IOException{
        datasocket = new Socket(ip,port);
        // super(ip,port);
        setIOStreams();
        //System.out.println("New Connection : "+client);
    }
    public StreamModeServer(Socket datasock) throws IOException{
        datasocket = datasock;
        setIOStreams();
        client = datasock.getInetAddress().toString() + ":" + datasock.getPort();

        System.out.println("New Connection at : "+client);

    }

    public void setIOStreams() throws IOException{
    InputStream in = datasocket.getInputStream();
    input = new BufferedReader(new InputStreamReader(in));
    OutputStream ou = datasocket.getOutputStream();
    output = new PrintWriter(new OutputStreamWriter(ou));
    }

    public void sendMsg(String str)throws IOException{
        output.println(str);
        output.flush();
    }

    public void sendMsg()throws IOException{
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String str = console.readLine();
        output.println(str);
        output.flush();
    }
    public String receiveMsg() throws IOException{

        return input.readLine();

    }

}
