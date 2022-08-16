/* *************************************************************

페르마의 소정리
    소수 p와 정수 a에 대하여
        a^p == a (mod p)
        * 이 때 a와 p가 서로소라면
          a^(p-1) == 1 (mod p)

키 아이디어:
    큰 수에 대해서는 파스칼의 삼각형을 적용하기 힘들다
        (메모리 부족)
    따라서, 수식을 계산해야 되는데,
    소수에 대한 나머지를 구하는 경우에는 
    (n-k)!(k)!을 나누는 것이 아닌
    이의 역원의 mod 값과 동일한 ((n-k)!(k)!)^(p-2)를 곱하자.

************************************************************* */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); int k = sc.nextInt();
        final int z = 1000000007;

        int sonResid = factResid(n, z);
        int motherResid = multResid(factResid(k, z), factResid(n-k, z), z);
        int reverseMotherResid = powerResid(motherResid, z-2, z);
        int combResid = multResid(sonResid, reverseMotherResid, z);

        System.out.println(combResid);
    }

    static int factResid(int n, int z) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = multResid(result, i, z);
        }
        return result;
    }

    static int powerResid(int a, int b, int z) { // a^b % z
        a %= z;
        int result = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                result = multResid(result, a, z);
            }
            result %= z;
            a = multResid(a, a, z);
            b /= 2;
        }
        return result;
    }
    
    static int multResid(int x, int y, int z) { // x * y % z
        x %= z;
        int result = 0;
        while (y > 0) {
            result += (y % 2) * x;
            result %= z;
            x = (x << 1) % z;
            y /= 2;
        }
        return result;
    }
}
