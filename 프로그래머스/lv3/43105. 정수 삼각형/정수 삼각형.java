class Solution {
    public int solution(int[][] triangle) {
        for (int i = 1; i < triangle.length; i++) {
            int n = triangle[i].length;
            
            // left
            triangle[i][0] += triangle[i-1][0];
            // right
            triangle[i][n-1] += triangle[i-1][n-2];
            // middles
            for (int j = 1; j < n-1; j++) {
                int max = Math.max(triangle[i-1][j-1], triangle[i-1][j]);
                triangle[i][j] += max;
            }
        }
        
        int answer = 0;
        for (int x : triangle[triangle.length-1]) {
            answer = Math.max(answer, x);
        }
        return answer;
    }
}