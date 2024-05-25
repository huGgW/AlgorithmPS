use std::collections::VecDeque;

impl Solution {
    pub fn update_matrix(mat: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let mut queue = VecDeque::new();
        let mut dist = mat.clone();
        (0..dist.len()).for_each(|i| {
            (0..dist[0].len()).for_each(|j| {
                if dist[i][j] == 0 {
                    queue.push_front((i, j))
                } else {
                    dist[i][j] = i32::MAX
                }
            })
        });

        let rows = dist.len();
        let cols = dist[0].len();

        while !queue.is_empty() {
            let n = queue.len();
            (0..n).for_each(|_| {
                let (i, j) = queue.pop_back().unwrap();
                let curr_dist = dist[i][j];
                [(i + 1, j), (i, j + 1), (i - 1, j), (i, j - 1)]
                    .iter()
                    .filter(|(ii, jj)| (0..rows).contains(ii) && (0..cols).contains(jj))
                    .for_each(|(ii, jj)| {
                        if dist[*ii][*jj] > curr_dist + 1 {
                            dist[*ii][*jj] = curr_dist + 1;
                            queue.push_front((*ii, *jj))
                        }
                    });
            });
        }

        dist
    }
}