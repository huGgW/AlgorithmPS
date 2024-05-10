const ENDCHAR = '$'

type Trie struct {
	nodes map[rune]*Trie
}

func Constructor() Trie {
	return Trie{map[rune]*Trie{}}
}

func (this *Trie) Insert(word string) {
    rn := []rune(word)
    if rn[len(rn)-1] != ENDCHAR {
        rn = append(rn, ENDCHAR)
    }
	r := rn[0]

    tPtr, found := this.nodes[r]
	if !found {
        newTr := Constructor()
        this.nodes[r] = &newTr
        tPtr = &newTr
	}

    if r != ENDCHAR {
        tPtr.Insert(string(rn[1:]))
    }
}

func (this *Trie) Search(word string) bool {
    rn := []rune(word)
    if rn[len(rn)-1] != ENDCHAR {
        rn = append(rn, ENDCHAR)
    }
    r := rn[0]

    if tPtr, found := this.nodes[r]; found {
        if r == ENDCHAR {
            return true
        } else {
            return tPtr.Search(string(rn[1:]))
        }
    } else {
        return false
    }
}

func (this *Trie) StartsWith(prefix string) bool {
    if len(prefix) == 0 {
        return true
    }

    rn := []rune(prefix)
    r := rn[0]

    if tPtr, found := this.nodes[r]; found {
        return tPtr.StartsWith(string(rn[1:]))
    } else {
        return false
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Insert(word);
 * param_2 := obj.Search(word);
 * param_3 := obj.StartsWith(prefix);
 */