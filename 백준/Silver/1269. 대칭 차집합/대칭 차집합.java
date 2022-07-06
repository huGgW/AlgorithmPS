import java.util.*;
import java.io.*;

public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = in.readLine().split(" ");
        int n = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);
        
        HashSet<Integer> a = new HashSet<>(n);
        HashSet<Integer> b = new HashSet<>(m);

        tmp = in.readLine().split(" ");
        for (String s : tmp) {
            a.add(Integer.parseInt(s));
        }

        tmp = in.readLine().split(" ");
        for (String s : tmp) {
            b.add(Integer.parseInt(s));
        }

        HashSet<Integer> aminusb = (HashSet<Integer>)a.clone();
        aminusb.removeAll(b);

        HashSet<Integer> bminusa = (HashSet<Integer>)b.clone();
        bminusa.removeAll(a);

        System.out.println(aminusb.size() + bminusa.size());
    }
}