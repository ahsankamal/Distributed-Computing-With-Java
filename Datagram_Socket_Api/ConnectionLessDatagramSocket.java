
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by ahsan on 8/5/16.
 */
public class ConnectionLessDatagramSocket extends DatagramSocket{

    int bufferSize = 100;
    public ConnectionLessDatagramSocket(int portNo) throws SocketException{
    // constructs a datagram socket and binds it to specified port on local host machine
        super(portNo);
    }
    public ConnectionLessDatagramSocket() throws SocketException{
        // constructs a datagram socket and binds it to any available port on local host machine

    }

    public String receiveMsg() throws IOException{

        byte[] buffer = new byte[bufferSize];
        DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
        //construct a datagram for receiving messages in buffer
        this.receive(datagram);
        //receive operation is blocking(Synchronous)
        return new String(buffer);
    }

    public void sendMsg(InetAddress hostIP, int port, String msg) throws IOException{
        byte[] buffer = msg.getBytes();
        //getBytes() : Encodes msg into a sequence of bytes using the platform's default charset
        // and then stores the result into a new byte array.

        DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, hostIP, port);
        //constructs a packet for sending it to specified address
        this.send(datagram);
        //send operation is non-blocking(asynchronous)
    }



}
