import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(in.readLine());
            ArrayList<String> list = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                list.add(in.readLine());
            }
            in.close();

            list.sort(new CustomComparator());
            
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            String before = "";
            for (String s : list) {
                if (!s.equals(before)) {
                    out.write(s);
                    out.newLine();
                }
                before = s;
            }
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class CustomComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        if (s1.length() == s2.length()) {
            return s1.compareTo(s2);
        }
        else {
            return ((Integer)s1.length()).compareTo((Integer)s2.length());
        }
    }
}