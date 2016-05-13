import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by ahsan on 8/5/16.
 */
public class Client1 {
    static int myPort=8007;
    // 2 commandline arguments are expected
    // ip address and portno of client2
    //  on localhost type
    // java Client1 127.0.0.1 8057
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException{
        if(args.length!=2){
            System.out.println("two command line arguments expected");
            System.exit(0);
        }
        else{
            InetAddress client2Host = InetAddress.getByName(args[0]);
            int client2Port = Integer.parseInt(args[1]);
            ConnectionLessDatagramSocket ahs = new ConnectionLessDatagramSocket(myPort);

            int i = 7;
            while(i>0) {
                // read input
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Type your msg : ");
                String msg = input.readLine();

                //send msg
                ahs.sendMsg(client2Host, client2Port, msg);

                ahs.setSoTimeout(20000);//to avoid indefinate blocking
                //receive operation is blocking
                //wait to receive datagram from client2
                System.out.println("Received Msg : " + ahs.receiveMsg());

                i--;
            }
            ahs.close();

        }

    }

}
