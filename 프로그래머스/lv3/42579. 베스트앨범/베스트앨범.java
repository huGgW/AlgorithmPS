import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, ArrayList<Integer>> table = new HashMap<>();
        HashMap<String, ArrayList<Integer>> indexs = new HashMap<>();
        HashMap<String, Integer> cnts = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            ArrayList<Integer> list = table.get(genres[i]);
            ArrayList<Integer> indexList = indexs.get(genres[i]);
            int cnt = cnts.getOrDefault(genres[i], 0);
            if (list == null) {
                list = new ArrayList<>();
                indexList = new ArrayList<>();
                table.put(genres[i], list);
                indexs.put(genres[i], indexList);
            }
            list.add(plays[i]);
            indexList.add(i);
            cnts.put(genres[i], cnt+plays[i]);
        }
        
        //
        System.out.println(table);
        System.out.println(indexs);
        System.out.println(cnts);
        //
        
        ArrayList<Integer> answer = new ArrayList<>();
        ArrayList<String> sortedKeys = new ArrayList(table.keySet());
        sortedKeys.sort((a, b) -> cnts.get(b) - cnts.get(a));
        
        for (String genre : sortedKeys) {
            ArrayList<Integer> sortedList = table.get(genre);
            ArrayList<Integer> indexList = indexs.get(genre);
            if (sortedList.size() == 1) {
                answer.add(indexList.get(0));
            } else {
                for (int j = 0; j < 2; j++) {
                    int i = getMaxIndex(sortedList, indexList);
                    answer.add(indexList.get(i));
                    indexList.remove(i);
                    sortedList.remove(i);
                }
            }
        }
        
        return answer.stream().mapToInt(a -> a).toArray();
    }
    
    int getMaxIndex(ArrayList<Integer> arr, ArrayList<Integer> idx) {
        int maxIdx = 0;
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) > arr.get(maxIdx) 
                || (
                    arr.get(i) == arr.get(maxIdx) && idx.get(i) < idx.get(maxIdx))
               ) {
                maxIdx = i;
            }
        }
        
        return maxIdx;
    }
    
}