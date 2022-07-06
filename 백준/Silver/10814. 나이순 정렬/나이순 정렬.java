import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(in.readLine());
            Person[] arr = new Person[n];

            for (int i = 0; i < n; i++) {
                String[] sl = in.readLine().split(" ");
                int age = Integer.parseInt(sl[0]);
                String name = sl[1];
                arr[i] = new Person(age, name);
            }
            in.close();
            
            mergeSortAge(arr, 0, n - 1);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            for (Person p : arr) {
                out.write(p.toString());
                out.newLine();
            }
            out.flush();
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void mergeSortAge(Person[] arr, int b, int e) {
        if (b >= e) return;
        int mid = (b+e) / 2;

        mergeSortAge(arr, b, mid);
        mergeSortAge(arr, mid+1, e);

        merge(arr, b, mid, e);
    }
    static void merge(Person[] arr, int b, int mid, int e) {
        int i = b;
        int j = mid + 1;
        int k = 0;
        Person[] temp = new Person[e - b + 1];

        while (i <= mid && j <= e) {
            if (arr[i].age <= arr[j].age) {
                temp[k++] = arr[i++];
            }
            else {
                temp[k++] = arr[j++];
            }
        }
        for (;i <= mid; i++) {
            temp[k++] = arr[i];
        }
        for (;j <= e; j++) {
            temp[k++] = arr[j];
        }

        for (int x = 0; x < e-b+1; x++) {
            arr[x + b] = temp[x];
        }
    }
}
class Person {
    public int age;
    public String name;

    public Person(int a, String n) {
        age = a;
        name = n;
    }

    @Override
    public String toString() {
        return (Integer.toString(this.age) + " " + this.name);
    }
}