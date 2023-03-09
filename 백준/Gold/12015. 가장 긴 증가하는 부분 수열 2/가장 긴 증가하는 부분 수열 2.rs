use std::io::stdin;

fn main() {
    let mut tmp = String::new();
    stdin().read_line(&mut tmp).unwrap();
    let n: isize = tmp.trim().parse().unwrap();
    tmp.clear();

    stdin().read_line(&mut tmp).unwrap();
    let nums = tmp
        .split_whitespace()
        .into_iter()
        .map(|s| s.parse::<isize>().unwrap())
        .collect::<Vec<isize>>();

    let mut list = Vec::<isize>::new();
    
    nums.into_iter().for_each(
        |v| insert_val(&mut list, v)
    );

    println!("{:?}", list.len());
}

fn insert_val(list: &mut Vec<isize>, val: isize) {
    let b = 0;
    let e = list.len() as isize - 1;

    let p = binary_search(list, val, b, e);

    match list.get(p) {
        Some(_) => list[p] = val,
        None => list.push(val)
    }
}

fn binary_search(list: &Vec<isize>, val: isize, b: isize, e: isize) -> usize {
    if b > e { return b as usize; }

    let m = (b + e) / 2;
    let pivot = * list.get(m as usize).unwrap();

    if val < pivot {
        binary_search(list, val, b, m-1)
    } else if val > pivot {
        binary_search(list, val, m+1, e)
    } else {
        m as usize
    }
}