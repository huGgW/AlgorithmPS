use std::io::stdin;
use std::vec::Vec;

fn main() {
    let (n, size_cnts, t, p) = read_inputs();

    let min_shirt: i32 = size_cnts.iter()
        .map(|cnt| (((*cnt) as f64) / (t as f64)).ceil() as i32)
        .sum();

    let (pen_bunch, pen_left) = (n / p, n % p);

    println!("{}", min_shirt);
    println!("{} {}", pen_bunch, pen_left);
}

fn read_inputs() -> (i32, Vec<i32>, i32, i32) {
    let stdin = stdin();
    let mut buf = String::new();

    stdin.read_line(&mut buf).unwrap();
    let n = buf.trim().parse::<i32>().unwrap();

    buf.clear();
    stdin.read_line(&mut buf).unwrap();
    let size_cnts = buf
        .trim()
        .split_whitespace()
        .map(|s| s.parse::<i32>().unwrap())
        .collect::<Vec<i32>>();

    buf.clear();
    stdin.read_line(&mut buf).unwrap();
    let num_vec = buf
        .trim()
        .split_whitespace()
        .map(|s| s.parse::<i32>().unwrap())
        .collect::<Vec<i32>>();
    let (t, p) = (num_vec[0], num_vec[1]);

    (n, size_cnts, t, p)
}
