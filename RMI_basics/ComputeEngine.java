import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by ahsan on 11/5/16.
 */
/*
How to Run the App:

javac *.java
rmic ComputeEngine
rmiregistry
Srart server: java ComputeEngine
Start Client: java ComputeNthFibonacci 127.0.0.1 10
to get 10th fibonacci no

 */


public class ComputeEngine implements Compute{

    public <T> T executeTask(Task<T> t){
        return t.execute();
    }

    public static void main(String[] args) throws RemoteException{
        /*if(System.getSecurityManager()==null){
            System.setSecurityManager(new SecurityManager());
        }*/

        String name = "Compute";
        Compute ce = new ComputeEngine();
        Compute stub = (Compute) UnicastRemoteObject.exportObject(ce,0);
        Registry reg = LocateRegistry.getRegistry();
        reg.rebind(name,stub);

    }



}





/*
Create the remote interface
Provide the implementation of the remote interface
Compile the implementation class and create the stub and skeleton objects using the rmic tool
Start the registry service by rmiregistry tool
Create and start the remote application
Create and start the client application
*/



//The RMI provides remote communication between the applications using two objects stub and skeleton.
//The stub acts as a gateway for the client side.
// All the client's requests goes through it.
// It is present at the client side and represents the remote object.
// When the client invokes method on the stub object,
//It initiates a connection with remote JVM,
//then writes and marshals the parameters to the remote JVM,
//then unmarshals the return value or exception, and
//and finally returns the value to the client.

// skeleton  acts as a gateway for the remote object at server side.
//It reads the parameter for the remote method
//then invokes the method on the actual remote object, and
//finally, writes and marshals the result to the client.