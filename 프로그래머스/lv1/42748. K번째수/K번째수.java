import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        int[] answer = new int[commands.length];
        int i = 0;
        for (int[] cmd: commands) {
            int[] sub = subArr(array, cmd[0]-1, cmd[1]-1);
            int num = kth(sub, cmd[2]-1, 0, sub.length-1);
            answer[i++] = num;
        }
        
        return answer;
    }
    
    public int[] subArr(int[] arr, int b, int e) {
        int[] sub = new int[e-b+1];
        
        for (int i = 0; i < sub.length; i++) {
            sub[i] = arr[b + i];
        }
        
        return sub;
    }
    
    public int kth(int[] arr, int k, int b, int e) {
        if (b == e) {
            return arr[b];
        }
        int m = (b + e) / 2;
        swap(arr, m, e);
        
        int i = b-1;
        for (int j = b; j < e; j++) {
            if (arr[j] < arr[e]) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, ++i, e);
        // Arrays.stream(arr).forEach((x) -> System.out.print(String.format("%d ", x)));
        // System.out.println("i:" + String.valueOf(i) + ", k:" + String.valueOf(k));
        
        if (i > k) {
            return kth(arr, k, b, i-1);
        } else if (i < k) {
            return kth(arr, k, i+1, e);
        } else {
            return arr[i];
        }
    }
    
    public void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}