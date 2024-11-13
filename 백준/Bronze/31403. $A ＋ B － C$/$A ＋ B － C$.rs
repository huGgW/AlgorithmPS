use std::io::stdin;

fn main() {
    let a_str = read_num_str();
    let b_str = read_num_str();
    let c_str = read_num_str();

    let str_calc = calc_str_sum(&a_str, &b_str, &c_str);

    let num_calc = calc_num_sum(&a_str, &b_str, &c_str);

    println!("{num_calc}");
    println!("{str_calc}");
}

fn read_num_str() -> String {
    let mut buf = String::new();
    stdin().read_line(&mut buf).unwrap();

    buf.trim().to_string()
}

fn calc_str_sum(a_str: &String, b_str: &String, c_str: &String) -> i32 {
    let ab = format!("{}{}", a_str, b_str)
        .parse::<i32>()
        .unwrap();

    let ab_c = ab - c_str.parse::<i32>().unwrap();

    ab_c
}

fn calc_num_sum(a_str: &String, b_str: &String, c_str: &String) -> i32 {
    a_str.parse::<i32>().unwrap()
    + b_str.parse::<i32>().unwrap()
    - c_str.parse::<i32>().unwrap()
}
