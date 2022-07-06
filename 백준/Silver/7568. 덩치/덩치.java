import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Bro[] broArr = new Bro[n];
        for (int i = 0; i < n; i++) {
            broArr[i] = new Bro(sc.nextInt(), sc.nextInt());
        }
        sc.close();
        for (Bro b: broArr) {
            int cnt = 1;
            for (Bro bs: broArr) {
                if (b.isSmaller(bs)) {
                    cnt++;
                }
            }
            System.out.print(cnt + " ");
        }
    }
}


class Bro {
    int weight;
    int height;

    public Bro(int w, int h) {
        weight = w;
        height = h;
    }

    public boolean isSmaller(Bro b) {
        if (weight < b.weight && height < b.height) {
            return true;
        }
        else return false;
    }
}