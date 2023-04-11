import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        LinkedList<Task> tasks = new LinkedList<>();
        for (int[] job: jobs) {
            tasks.add(new Task(job[0], job[1]));
        }
        
        int totalTime = 0;
        boolean isDelayed = false;
        
        while (!tasks.isEmpty()) {
            Task nextTask = removeMinTask(tasks, isDelayed);
            totalTime += nextTask.executedTime();
            isDelayed = updateTasks(nextTask.end, tasks);
        }
        
        return totalTime / jobs.length;
    }
    
    public Task removeMinTask(List<Task> tasks, boolean isDelayed) {
        Comparator<Task> cmp = isDelayed ? new EndComp() : new StartComp();
        
        Task minTask = null;
        int minIdx = -1;
        
        int idx = 0;
        for (Task task: tasks) {
            if (
                ((isDelayed && task.isUpdated) || !isDelayed)
                && (minTask == null || cmp.compare(minTask, task) > 0)
            ) {
                minTask = task;
                minIdx = idx;
            }
            idx++;
        }
        
        return tasks.remove(minIdx);
    }
    
    public boolean updateTasks(int available, List<Task> tasks) {
        boolean isUpdated = false;
        for (Task t : tasks) {
            t.update(available);
            isUpdated |= t.isUpdated;
        }
        
        return isUpdated;
    }
}

class Task {
    final int origStart;
    int start;
    final int spend;
    int end;
    boolean isUpdated;
    
    public Task(int start, int spend) {
        this.origStart = start;
        this.start = start;
        this.spend = spend;
        this.end = start + spend;
        isUpdated = false;
    }
    
    public void update(int available) {
        if (available > start) {
            start = available;
            end = start + spend;
            isUpdated = true;
        }
    }
    
    public int executedTime() {
        return end - origStart;
    }
}

class StartComp implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.origStart != t2.origStart) {
            return t1.origStart - t2.origStart;
        }
        return t1.end - t2.end;
    }
}

class EndComp implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.end != t2.end) {
            return t1.end - t2.end;
        }
        
        return t1.start - t2.start;
    }
}