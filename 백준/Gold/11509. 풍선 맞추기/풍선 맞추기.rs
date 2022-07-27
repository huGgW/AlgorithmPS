use std::io;

fn main() {
    let mut buf: String = String::new();
    io::stdin().read_line(&mut buf).unwrap();
    let n: usize = buf.trim().parse().unwrap();

    let mut buf: String = String::new();
    io::stdin().read_line(&mut buf).unwrap();
    let nums: Vec<isize> = buf.trim().split(' ')
        .map(|c| c.parse::<isize>().unwrap()).collect();
    let mut arrows: Vec<isize> = Vec::with_capacity(n);

    for x in nums {
        let mut hit: bool = false;
        for i in 0..arrows.len() {
            if *arrows.get(i).unwrap() == x {
                *arrows.get_mut(i).unwrap() -= 1;
                hit = true;
                break;
            }
        }
        if arrows.is_empty() || !hit {
            arrows.push(x-1);
        }
    }

    println!("{}", arrows.len());
}