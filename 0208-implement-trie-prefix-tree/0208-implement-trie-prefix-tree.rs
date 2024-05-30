use std::mem::swap;

const END_CHAR: &str = "$";

struct Trie {
    prefix: String,
    children: Vec<Trie>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl Trie {
    fn new() -> Self {
        Trie {
            prefix: String::new(),
            children: vec![],
        }
    }

    fn insert(&mut self, word: String) {
        let word_with_end = word + END_CHAR;
        self.insert_recursive(word_with_end)
    }

    fn insert_recursive(&mut self, word: String) {
        let mut common_until = 0;
        for (p, w) in self.prefix.chars().zip(word.chars()) {
            if p != w {
                break;
            } else {
                common_until += 1;
            }
        }

        if common_until == word.len() {
            return;
        }

        if common_until < self.prefix.len() {
            let bef_prefix = &self.prefix[..common_until];
            let after_prefix = &self.prefix[common_until..];

            let mut new_trie = Trie {
                prefix: after_prefix.to_string(),
                children: vec![],
            };

            self.prefix = bef_prefix.to_string();
            swap(&mut new_trie.children, &mut self.children);
            self.children.push(new_trie);

            self.children.push(Trie {
                prefix: word[common_until..].to_string(),
                children: vec![],
            });

            return;
        }

        let leftover = word[common_until..].to_string();
        let tr_opt = self
            .children
            .iter_mut()
            .find(|tr| tr.prefix.starts_with(&leftover[0..1]));
        match tr_opt {
            Some(tr) => tr.insert_recursive(String::from(leftover)),
            None => self.children.push(Trie {
                prefix: leftover,
                children: vec![],
            }),
        }
    }

    fn search(&self, word: String) -> bool {
        self.search_recursive(&(word + END_CHAR))
    }

    fn search_recursive(&self, word: &str) -> bool {
        if self.prefix.eq(word) {
            return true;
        } else if !word.starts_with(&self.prefix) {
            return false;
        }

        let left_word = &word[self.prefix.len()..];
        self.children
            .iter()
            .any(|tr| tr.search_recursive(left_word))
    }

    fn starts_with(&self, prefix: String) -> bool {
        self.starts_with_recursive(&prefix)
    }

    fn starts_with_recursive(&self, prefix: &str) -> bool {
        if self.prefix.starts_with(prefix) {
            true
        } else if prefix.starts_with(&self.prefix) {
            let left_prefix = &prefix[self.prefix.len()..];
            self.children
                .iter()
                .any(|tr| tr.starts_with_recursive(left_prefix))
        } else {
            false
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * let obj = Trie::new();
 * obj.insert(word);
 * let ret_2: bool = obj.search(word);
 * let ret_3: bool = obj.starts_with(prefix);
 */