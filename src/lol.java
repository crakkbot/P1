import java.math.BigInteger;

public class lol {
    public static void main(String[] args) {
        int[] arr = {14,5,16,19,20,1,9};
        double a = ((Math.sqrt(5.0)-1.0)/2.0);
        int m = 9;
        int k = arr[0];
        double h_strich = Math.floor(m*(k*a-Math.floor(k*a)));
        System.out.println("h_strich = " + h_strich);
        int i= 3;
        double h = (h_strich + 0.5*i+0.5*i*i) % m;
        System.out.println("h= " +h);

    }
}
