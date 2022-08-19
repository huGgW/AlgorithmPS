use std::io::{stdin, stdout, BufReader, BufRead, BufWriter, Write};
use std::collections::HashSet;

fn main() {
    let mut reader = BufReader::new(stdin());
    let mut buf = String::new();
    reader.read_line(&mut buf).unwrap();
    // let n = buf.trim_end().parse::<usize>().unwrap();

    buf = String::new();
    reader.read_line(&mut buf).unwrap();
    let mut base: HashSet<i32> = HashSet::new();
    for x in buf.trim().split_whitespace().map(|s| s.parse::<i32>().unwrap()) {
        base.insert(x);
    }

    buf = String::new();
    reader.read_line(&mut buf).unwrap();

    buf = String::new();
    reader.read_line(&mut buf).unwrap();

    let mut writer = BufWriter::new(stdout());
    for x in buf.trim().split_whitespace().map(|s| s.parse::<i32>().unwrap()) {
        writer.write_fmt(format_args!("{}\n", {if base.contains(&x) {1} else {0}})).unwrap();
    }
    writer.flush().unwrap();
}