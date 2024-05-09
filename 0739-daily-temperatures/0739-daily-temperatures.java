import java.util.ArrayDeque;

class Pair {
    public int first;
    public int second;

    public Pair(int f, int s) {
        this.first = f;
        this.second = s;
    }
}

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        var stack = new ArrayDeque<Pair>();
        var answer = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && stack.peek().second < temperatures[i]) {
                Pair p = stack.pop();
                answer[p.first] = i - p.first;
            }

            stack.push(new Pair(i, temperatures[i]));
        }

        return answer;
    }
}
