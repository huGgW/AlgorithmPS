package main;

import(
    "fmt"
    "bufio"
    "os"
    "sort"
)

func main() {
    reader := bufio.NewReader(os.Stdin)

    var n int
    var m int
    var r int

    fmt.Fscanln(reader, &n, &m, &r)

    visited := make([]int, n);
    graph := make([][]int, n);

    for i := 0; i < m; i++ {
        var x int
        var y int
        fmt.Fscanln(reader, &x, &y)

        if (graph[x-1] == nil) {
            graph[x-1] = []int{y-1}
        } else {
            graph[x-1] = append(graph[x-1], y-1)
        }

        if (graph[y-1] == nil) {
            graph[y-1] = []int{x-1}
        } else {
            graph[y-1] = append(graph[y-1], x-1)
        }
    }

    for i := 0; i < n; i++ {
        sort.Slice(graph[i], func(x, y int) bool { return graph[i][x] > graph[i][y] })
    }

    cnt := 0;
    dfs(&graph, &visited, &cnt, r-1)

    writer := bufio.NewWriter(os.Stdout)
    for _, c := range visited {
        fmt.Fprintln(writer, c)
    }
    writer.Flush()
}

func dfs(graph *[][]int, visited *[]int, cnt *int, x int) {
    if ((*visited)[x] > 0) {
        return
    }

    *cnt += 1
    (*visited)[x] = *cnt;

    for _, y := range (*graph)[x] {
        if (*visited)[y] == 0 {
            dfs(graph, visited, cnt, y)
        }
    }
}