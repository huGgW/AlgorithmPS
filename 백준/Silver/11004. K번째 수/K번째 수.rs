use std::{io::{BufReader, BufRead, stdin}, cmp::Ordering::{Less, Greater, Equal}};

fn main() {
    // Read N, K
    let mut reader = BufReader::new(stdin());
    let mut buf = String::new();
    reader.read_line(&mut buf).unwrap();

    let mut numsIter = buf.trim().split(' ')
        .map(|s| s.parse::<i32>().unwrap());
    let N = numsIter.next().unwrap();
    let K = numsIter.next().unwrap() as usize;
    deallocate(numsIter);

    // Read Numbers
    buf.clear();
    reader.read_line(&mut buf).unwrap();
    let mut A: Vec<i32> = buf.trim().split(' ')
        .map(|s| s.parse::<i32>().unwrap())
        .collect();
    deallocate(buf);

    let is_min = K <= (N/2) as usize;

    to_heap(&mut A, is_min);

    let kth_num = get_kth_num(&mut A, if is_min {K} else {(N as usize) + 1 - K}, is_min);
    println!("{}", kth_num);
}

fn get_kth_num(A: &mut Vec<i32>, k: usize, is_min: bool) -> i32 {
    for _ in 1..=k-1 {
        pop(A, is_min);
    }
    pop(A, is_min)
}

fn to_heap(A: &mut Vec<i32>, is_min: bool) {
    let n = A.len();
    for i in (0..(n/2)).rev() {
        heapify(A, i, is_min);
    }
}

fn heapify(A: &mut Vec<i32>, i: usize, is_min: bool) {
    let get_left = |i: usize| ((i+1) * 2) - 1;
    let get_right = |i: usize| ((i+1) * 2);
    let get_parent = |i: usize| ((i+1) / 2) - 1;

    let mut current_idx = i;
    loop {
        let left_idx = get_left(current_idx);
        let right_idx = get_right(current_idx);

        let target_idx = match (A.get(left_idx), A.get(right_idx)) {
            (Some(l), Some(r)) => match is_min {
                true => if *l < *r {left_idx} else {right_idx}
                false => if *l > *r {left_idx} else {right_idx}
            },
            (Some(_), None) => left_idx,
            (None, Some(_)) => right_idx,
            (None, None) => break
        };

        current_idx = match is_min {
            true => if *A.get(current_idx).unwrap() > *A.get(target_idx).unwrap() {
                swap(target_idx, current_idx, A);
                target_idx
            } else {
                break
            },
            false => if *A.get(current_idx).unwrap() < *A.get(target_idx).unwrap() {
                swap(target_idx, current_idx, A);
                target_idx
            } else {
                break
            },
        }
    }
}

fn pop(heap: &mut Vec<i32>, is_min: bool) -> i32 {
    swap(0, heap.len() - 1, heap);
    let popped = heap.pop().unwrap();
    heapify(heap, 0, is_min);
    popped
}

fn swap(i: usize, j: usize, v: &mut Vec<i32>) {
    let tmp = *v.get(i).unwrap();
    *v.get_mut(i).unwrap() = *v.get(j).unwrap();
    *v.get_mut(j).unwrap() = tmp;
}

fn deallocate<T>(variable: T) {}