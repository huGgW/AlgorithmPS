import java.util.*; 
class Solution {
    public int[] solution(String[] operations) {
        DoubleQueue queue = new DoubleQueue();
        
        for (String cmd : operations) {
            switch (cmd.charAt(0)) {
                case 'I':
                    int num = Integer.parseInt(cmd.substring(2));
                    queue.offer(num);
                    break;
                case 'D':
                    if (queue.isEmpty()) {
                        break;
                    }
                    
                    int minOrMax = Integer.parseInt(cmd.substring(2));
                    if (minOrMax == -1) {
                        queue.minPoll();
                    } else if (minOrMax == 1) {
                        queue.maxPoll();
                    }
                    break;
            }
        }
        
        int[] answer = new int[2];
        if (queue.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = queue.maxPeek();
            answer[1] = queue.minPeek();
        }
        
        return answer;
    }
}

// class DoubleQueue {
//     private PriorityQueue<Integer> minQ;
//     private PriorityQueue<Integer> maxQ;
//     int cnt;
//     
//     public DoubleQueue() {
//         minQ = new PriorityQueue<>();
//         maxQ = new PriorityQueue<>((a, b) -> (b - a));
//         cnt = 0;
//     }
//     
//     private void initialize() {
//         minQ.clear();
//         maxQ.clear();
//         cnt = 0;
//     }
//     
//     public void offer(int x) {
//         cnt++;
//         minQ.offer(x);
//         maxQ.offer(x);
//     }
//     
//     public int maxPeek() {
//         return maxQ.peek();
//     }
//     
//     public int maxPoll() {
//         cnt--;
//         return maxQ.poll();
//     }
//     
//     public int minPeek() {
//         return minQ.peek();
//     }
//     
//     public int minPoll() {
//         cnt--;
//         return minQ.poll();
//     }
//     
//     public boolean isEmpty() {
//         if (cnt > 0) { 
//             return false;
//         }
//         
//         initialize();
//         return true;
//     }
// }

class DoubleQueue {
    LinkedList<Integer> list = new LinkedList<>();
    
    public int maxPoll() {
        return list.removeLast();
    }
    
    public int minPoll() {
        return list.removeFirst();
    }
    
    public int maxPeek() {
        return list.getLast();
    }
    
    public int minPeek() {
        return list.getFirst();
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public void offer(int x) {
        int idx = Collections.binarySearch(list, x);
        idx = (idx < 0) ? -1 * (idx + 1) : idx;
        list.add(idx, x);
    }
}