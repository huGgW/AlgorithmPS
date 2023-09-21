import java.util.*;

class Solution {
    private HashMap<Pair, Integer> table;

    public int solution(int[] money) {
        table = new HashMap<>();

        var initExcludePair = new Pair(1, money.length - 1);
        var initIncludePair = new Pair(2, money.length - 2);

        for (int diff = 0; diff <= money.length - 2; diff++) {
            for (int i = 1; (diff + i) < money.length; i++) {
                int j = i + diff;
                if (i == j) {
                    table.put(new Pair(i, j), money[i]);
                } else if (i + 1 == j) {
                    table.put(new Pair(i, j), Math.max(money[i], money[j]));
                } else {
                    table.put(
                        new Pair(i, j),
                        Math.max(
                            table.get(new Pair(i+1, j)),
                            money[i] + table.get(new Pair(i+2, j))
                        )
                    );
                }
            }
        }

        int answer = Math.max(
            table.get(initExcludePair),
            money[0] + table.get(initIncludePair)
        );

        return answer;
    }
}

class Pair {
    int b;
    int e;

    public Pair(int i, int j) {
        this.b = i;
        this.e = j;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Pair
            && ((Pair)o).b == this.b
            && ((Pair)o).e == this.e;
    }

    @Override
    public int hashCode() {
        return Objects.hash(b, e);
    }
}
