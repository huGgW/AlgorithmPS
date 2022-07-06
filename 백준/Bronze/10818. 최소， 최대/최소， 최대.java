import java.util.Scanner;

public class Main {
    public static int minNum(Integer[] array, int length) {
        int min = array[0];
        for (int i = 0; i < length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
    
    public static int maxNum(Integer[] array, int length) {
        int max = array[0];
        for (int i = 0; i < length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int len = sc.nextInt();

        Integer[] arr = new Integer[len];
        for (int i = 0; i < len; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();

        int min = minNum(arr, len);
        int max = maxNum(arr, len);

        System.out.println(min + " " + max);
    }

}