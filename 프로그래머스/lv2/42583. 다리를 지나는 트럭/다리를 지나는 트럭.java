import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int pos = 0;
        Deque<Truck> queue = new LinkedList<Truck>();
        int queueWeight = 0;
        int time = 0;
        
        for (; (pos < truck_weights.length) || (!queue.isEmpty()); time++) {
//            System.out.print(String.format("%d : ", time));
//            System.out.println(queue);
            if (
                (!queue.isEmpty())
                && (time >= queue.peek().outTime)
            ) {
                Truck outTruck = queue.poll();
                queueWeight -= outTruck.weight;
            }
            if (
                (pos < truck_weights.length)
                && (queue.size() < bridge_length)
                && (queueWeight + truck_weights[pos] <= weight)
            ) {
                Truck inTruck = new Truck(truck_weights[pos++], time + bridge_length);
                queue.offer(inTruck);
                queueWeight += inTruck.weight;
            }
        }
        
        return time;
    }
}

class Truck {
    public int weight;
    public int outTime;
    
    public Truck(int w, int t) {
        weight = w;
        outTime = t;
    }
//    
//    @Override
//    public String toString() {
//        return String.valueOf(weight);
//    }
}