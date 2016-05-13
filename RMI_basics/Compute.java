import java.rmi.Remote;

/**
 * Created by ahsan on 11/5/16.
 */

//Generic Compute Engine (CE)
//CE is a remote object on the server that takes tasks from the client(dynamically loads the task code), runs the tasks and
//return the result. Task runs on server machine having powerful hardware.

// this interface defines the methods that can be invoked by client. (client's view of remote object)
public interface Compute extends Remote{
    // remote method
    <T> T executeTask(Task<T> t) throws java.rmi.RemoteException;
}
