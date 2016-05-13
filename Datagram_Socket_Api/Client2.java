import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by ahsan on 8/5/16.
 */
public class Client2 {

    static int myPort=8057;
    // 2 commandline arguments are expected
    // ip address and portno of client1
    //  on localhost type
    // java Client2 127.0.0.1 8007
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
        if(args.length!=2){
            System.out.println("two command line arguments expected");
            System.exit(0);
        }
        else{
            InetAddress client1Host = InetAddress.getByName(args[0]);
            int client1Port = Integer.parseInt(args[1]);
            ConnectionLessDatagramSocket sar = new ConnectionLessDatagramSocket(myPort);

            System.out.println("Waiting...");

            int i=7;
            while(i>0) {

                sar.setSoTimeout(20000);
                //wait to receive datagram from client1
                System.out.println("Received Msg : " + sar.receiveMsg());

                // read input
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Type your msg : ");
                String msg = input.readLine();

                //send msg
                sar.sendMsg(client1Host, client1Port, msg);
                i--;
            }

            sar.close();
        }

    }

}
