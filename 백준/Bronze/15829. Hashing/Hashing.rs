use std::io::stdin; 

fn main() {
    let s = read_input();
    let answer = hash_value(s);
    println!("{}", answer);
}

fn read_input() -> String {
    let mut buf = String::new();
    stdin().read_line(&mut buf).unwrap();
    buf.clear();

    stdin().read_line(&mut buf).unwrap();
    buf.trim().to_string()
}

fn hash_value(s: String) -> i64 {
    const MOD_VAL: i64 = 1234567891;
    const MULT_VAL: i64 = 31;
    const BASE_VAL: i64 = 'a' as i64 - 1;

    s.chars()
        .enumerate()
        .map(|(i, c)| {
            let mut front = (c as i64) - BASE_VAL;
            (0..i).for_each(|_| {
                front = (front * MULT_VAL) % (MOD_VAL);
            });
            front
        })
        .fold(0, |acc, v| (acc + v) % MOD_VAL)
}
