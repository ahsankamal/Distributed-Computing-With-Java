import java.io.Serializable;

/**
 * Created by ahsan on 11/5/16.
 */

public class Fibonacci implements Task<Integer>, Serializable {

    private final int nth;

    public Fibonacci(int n){
        this.nth=n;
    }

    public Integer execute(){

        return fibo(nth);
    }

    //computationally expensive implementation of finding nth fibo.( Exponential complexity)
    public Integer fibo(int n){
        if (n==0)
            return 0;
        else if (n==1)
            return 1;
        else
            return fibo(n-1) + fibo(n-2);
    }
}
