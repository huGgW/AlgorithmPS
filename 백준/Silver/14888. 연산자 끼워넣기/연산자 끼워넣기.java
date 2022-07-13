import java.util.*;

enum Op {
  ADD, SUB, MUL, DIV
}

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = sc.nextInt();
    }

    Op[] oper = new Op[n-1];
    int pos = 0;
    // add
    int tmp = sc.nextInt();
    for (int i = pos; i < pos + tmp; i++) {
      oper[i] = Op.ADD;
    }
    pos += tmp;
    // sub
    tmp = sc.nextInt();
    for (int i = pos; i < pos + tmp; i++) {
      oper[i] = Op.SUB;
    }
    pos += tmp;
    // mul
    tmp = sc.nextInt();
    for (int i = pos; i < pos + tmp; i++) {
      oper[i] = Op.MUL;
    }
    pos += tmp;
    // div
    tmp = sc.nextInt();
    for (int i = pos; i < pos + tmp; i++) {
      oper[i] = Op.DIV;
    }
    sc.close();

    // TreeSet (red-black binary search tree) can be easily find min and MAX
    TreeSet<Integer> calcs = new TreeSet<>();
    // Calculate all cases
    calcAllCase(nums, oper, calcs);
    
    // Print minimum and Maximum in the TreeSet
    System.out.println(calcs.last());
    System.out.println(calcs.first());
  }

  public static void calcAllCase (int[] nums, Op[] oper, TreeSet<Integer> calcs) {
    int n = nums.length;
    // applyTo is the order of operators in oper array
    int[] applyTo = new int[n-1];
    HashSet<Integer> appeared = new HashSet<>(n-1);

    // make all possible cases of applyTo and calculate using it
    considerApplyTo(0, nums, oper, applyTo, appeared, calcs);
  }

  public static void considerApplyTo(int state, int[] nums, Op[] oper, int[] applyTo, HashSet<Integer> appeared, TreeSet<Integer> calcs) {
    int n = nums.length;
    for (int i = 0; i < n-1; i++) {
      if (!appeared.contains(i)) {
        applyTo[state] = i;
        appeared.add(i);
        state++;

        if (state == n-1) {
          calculate(nums, oper, applyTo, calcs);
        }
        else {
          considerApplyTo(state, nums, oper, applyTo, appeared, calcs);
        }

        state--;
        appeared.remove(i);
      }
    }
  }

  public static void calculate(int[] nums, Op[] oper, int[] applyTo, TreeSet<Integer> calcs) {
    int result = nums[0];

    for (int i = 0; i < oper.length; i++) {
      switch(oper[applyTo[i]]) {
      case ADD: {
        result += nums[i+1];
        break;
      }
      case SUB: {
        result -= nums[i+1];
        break;
      }
      case MUL: {
        result *= nums[i+1];
        break;
      }
      case DIV: {
        result /= nums[i+1];
        break;
      }
      default: { result = result; }
      }
    }

    calcs.add(result);
  }
}