use std::io;

fn main() {
    let mut buf: String = String::new();
    io::stdin().read_line(&mut buf).unwrap();
    let buf = buf.trim();

    let mut expr: Vec<Vec<&str>> = buf
        .split('-')
        .map(|s| vec![s])
        .collect();
    
    expr.iter_mut().for_each(
        |v| {
            let v_mod: Vec<&str> = v.iter().flat_map(|s| s.split('+')).collect();
            *v = v_mod
        }
    );

    let nums: Vec<Vec<isize>> = expr.iter().map(
        |v| {
            v.iter().map(|s| (*(*(s))).parse::<isize>().unwrap()).collect()
        }
    ).collect();

    let mut sum: isize = 0;
    for i in 0..nums.len() {
        if i == 0 {
            sum += nums.get(i).unwrap().iter().sum::<isize>();
        }
        else {
            sum -= nums.get(i).unwrap().iter().sum::<isize>();
        }
    }

    println!("{}", sum);
}