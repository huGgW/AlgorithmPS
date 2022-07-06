import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            
            if(n < 0 || n > 99) {
                throw new RangeException();
            }

            int n0 = n;
            int iter = 0;
            while(true) {
                int tempNum = (n/10) + (n%10);
                n = ((n%10)*10) + (tempNum%10);

                iter++;
                if (n0 == n) {break;}
            }

            System.out.println(iter);
        }
        catch (RangeException re) {System.out.println("Range is not correct");}
        catch (Exception e) {e.printStackTrace();}
    }
}

class RangeException extends Exception{}
