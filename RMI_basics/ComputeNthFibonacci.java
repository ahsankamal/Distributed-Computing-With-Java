import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by ahsan on 11/5/16.
 */
//https://docs.oracle.com/javase/tutorial/rmi/running.html
public class ComputeNthFibonacci {

    public static void main(String[] args) throws RemoteException, NotBoundException{
      /*  if (System.getSecurityManager()==null){
            System.setSecurityManager(new SecurityManager());
        }
        */


        String name = "Compute";
        Registry registry = LocateRegistry.getRegistry(args[0]);
        Compute comp = (Compute) registry.lookup(name);
        Fibonacci task = new Fibonacci(Integer.parseInt(args[1]));
        Integer nthFibo = comp.executeTask(task);
        System.out.println(nthFibo);


    }

}
