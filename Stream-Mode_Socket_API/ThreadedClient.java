import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by ahsan on 12/5/16.
 */
public class ThreadedClient {
    // 2 commandline argument expected
    //ip of server, portNo

    public static void  main(String[] args) throws UnknownHostException,IOException{
        InetAddress serverip = InetAddress.getByName(args[0]);
        int port = Integer.parseInt(args[1]);
        StreamModeServer datasock = new StreamModeServer(serverip,port);
        Thread th = new Thread(new readMsgFromServer(datasock));
        th.start();

        while(true){
            if(datasock!=null)
            datasock.sendMsg();
        }

    }

    public static class readMsgFromServer implements Runnable{
        StreamModeServer client;
        public readMsgFromServer(StreamModeServer sm){
            this.client = sm;
        }
        public void run() {
            try {


                while (true) {

                   // client.sendMsg();
                    //System.out.println("Server Typing...  ");
                    String str = client.receiveMsg();
                    System.out.println(str);
                    if (str.contains("Bye...")) {
                        //System.out.println("Bye...");
                        break;
                    }

                }
                client.close();
                System.exit(0);

            }
            catch(IOException e){
                e.printStackTrace();
            }

        }

    }

}
