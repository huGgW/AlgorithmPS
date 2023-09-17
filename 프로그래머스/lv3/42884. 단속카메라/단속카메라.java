import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[][] routes) {
        var cars = Arrays.stream(routes)
                .map(r -> new Pair(r))
                .sorted((a, b) -> a.end - b.end)
                .collect(Collectors.toList());

        int cnt = 0;
        int camera = -30001;

        for (var car: cars) {
            if (!car.isIn(camera)) {
                camera = car.end;
                cnt++;
            }
        }

        return cnt;
    }
}

class Pair {
    int start;
    int end;

    public Pair(int s, int e) {
        start = s;
        end = e;
    }

    public Pair(int[] arr) {
        start = arr[0];
        end = arr[1];
    }

    public boolean isIn(int point) {
        return start <= point && point <= end;
    }
}
