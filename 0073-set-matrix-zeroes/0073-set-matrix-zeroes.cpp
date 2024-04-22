class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        set<int> *rows = new set<int>();
        set<int> *cols = new set<int>();

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix[0].size(); j++) {
                if (matrix[i][j] == 0) {
                    rows->insert(i);
                    cols->insert(j);
                }
            }
        }

        for (int r: *rows) {
            for (int j = 0; j < matrix[0].size(); j++) {
                matrix[r][j] = 0;
            }
        }

        for (int c: *cols) {
            for (int i = 0; i < matrix.size(); i++) {
                matrix[i][c] = 0;
            }
        }
    }
};