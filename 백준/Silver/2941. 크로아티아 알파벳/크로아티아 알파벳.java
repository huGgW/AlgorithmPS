import java.util.Scanner;
import java.util.HashMap;
import java.util.Objects;
public class Main {
    public static void main(String[] args) {
        HashMap<Tuple<Character, State>, State> stateMap = new HashMap<>();
        stateMap.put(new Tuple<>('c', State.None), State.C_);
        stateMap.put(new Tuple<>('c', State.C_), State.C_);
        stateMap.put(new Tuple<>('c', State.D_), State.C_);
        stateMap.put(new Tuple<>('c', State.DZ_), State.C_);
        stateMap.put(new Tuple<>('c', State.L_), State.C_);
        stateMap.put(new Tuple<>('c', State.N_), State.C_);
        stateMap.put(new Tuple<>('c', State.S_), State.C_);
        stateMap.put(new Tuple<>('c', State.Z_), State.C_);
        stateMap.put(new Tuple<>('d', State.None), State.D_);
        stateMap.put(new Tuple<>('d', State.C_), State.D_);
        stateMap.put(new Tuple<>('d', State.D_), State.D_);
        stateMap.put(new Tuple<>('d', State.DZ_), State.D_);
        stateMap.put(new Tuple<>('d', State.L_), State.D_);
        stateMap.put(new Tuple<>('d', State.N_), State.D_);
        stateMap.put(new Tuple<>('d', State.S_), State.D_);
        stateMap.put(new Tuple<>('d', State.Z_), State.D_);
        stateMap.put(new Tuple<>('l', State.None), State.L_);
        stateMap.put(new Tuple<>('l', State.C_), State.L_);
        stateMap.put(new Tuple<>('l', State.D_), State.L_);
        stateMap.put(new Tuple<>('l', State.DZ_), State.L_);
        stateMap.put(new Tuple<>('l', State.L_), State.L_);
        stateMap.put(new Tuple<>('l', State.N_), State.L_);
        stateMap.put(new Tuple<>('l', State.S_), State.L_);
        stateMap.put(new Tuple<>('l', State.Z_), State.L_);
        stateMap.put(new Tuple<>('n', State.None), State.N_);
        stateMap.put(new Tuple<>('n', State.C_), State.N_);
        stateMap.put(new Tuple<>('n', State.D_), State.N_);
        stateMap.put(new Tuple<>('n', State.DZ_), State.N_);
        stateMap.put(new Tuple<>('n', State.L_), State.N_);
        stateMap.put(new Tuple<>('n', State.N_), State.N_);
        stateMap.put(new Tuple<>('n', State.S_), State.N_);
        stateMap.put(new Tuple<>('n', State.Z_), State.N_);
        stateMap.put(new Tuple<>('s', State.None), State.S_);
        stateMap.put(new Tuple<>('s', State.C_), State.S_);
        stateMap.put(new Tuple<>('s', State.D_), State.S_);
        stateMap.put(new Tuple<>('s', State.DZ_), State.S_);
        stateMap.put(new Tuple<>('s', State.L_), State.S_);
        stateMap.put(new Tuple<>('s', State.N_), State.S_);
        stateMap.put(new Tuple<>('s', State.S_), State.S_);
        stateMap.put(new Tuple<>('s', State.Z_), State.S_);
        stateMap.put(new Tuple<>('z', State.None), State.Z_);
        stateMap.put(new Tuple<>('z', State.C_), State.Z_);
        stateMap.put(new Tuple<>('z', State.D_), State.Z_);
        stateMap.put(new Tuple<>('z', State.DZ_), State.Z_);
        stateMap.put(new Tuple<>('z', State.L_), State.Z_);
        stateMap.put(new Tuple<>('z', State.N_), State.Z_);
        stateMap.put(new Tuple<>('z', State.S_), State.Z_);
        stateMap.put(new Tuple<>('z', State.Z_), State.Z_);
        stateMap.put(new Tuple<>('=', State.C_), State.Complete);
        stateMap.put(new Tuple<>('-', State.C_), State.Complete);
        stateMap.put(new Tuple<>('z', State.D_), State.DZ_);
        stateMap.put(new Tuple<>('-', State.D_), State.Complete);
        stateMap.put(new Tuple<>('j', State.L_), State.Complete);
        stateMap.put(new Tuple<>('j', State.N_), State.Complete);
        stateMap.put(new Tuple<>('=', State.S_), State.Complete);
        stateMap.put(new Tuple<>('=', State.Z_), State.Complete);
        stateMap.put(new Tuple<>('=', State.DZ_), State.Comp_DZ);

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        int cnt = 0;
        State state = State.None;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cnt++;
            state = stateMap.get(new Tuple<>(c, state));
            if (state == null) {
                state = State.None;
            }
            else if (state == State.Complete) {
                state = State.None;
                cnt--;
            }
            else if (state == State.Comp_DZ) {
                state = State.None;
                cnt -= 2;
            }
        }
        System.out.println(cnt);
    }
}
enum State {
    None, C_, D_, DZ_, L_, N_, S_, Z_, Complete, Comp_DZ
}
class Tuple<T1, T2> {
    T1 left;
    T2 right;
    Tuple(T1 l, T2 r) { left = l; right = r; }
    @Override
    public boolean equals(Object o) {
        if (o.getClass().isInstance(this)) {
            Tuple<T1, T2> t = (Tuple<T1, T2>)o;
            return (this.left == t.left && this.right == t.right);
        }
        else { return false; }
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.left, this.right);
    }
}