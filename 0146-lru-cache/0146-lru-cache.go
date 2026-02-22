type LRUCache struct {
	linkedList *LinkedList
	nodeMap    map[int]*Node
	capacity   int
}

func Constructor(capacity int) LRUCache {
	return LRUCache{
		linkedList: new(LinkedList),
		nodeMap:    make(map[int]*Node, capacity),
		capacity:   capacity,
	}
}

func (this *LRUCache) Get(key int) int {
	n, ok := this.nodeMap[key]
	if !ok {
		return -1
	}

	this.linkedList.Remove(n)
	this.linkedList.Insert(n)

	return n.Value
}

func (this *LRUCache) Put(key int, value int) {
	if n, exists := this.nodeMap[key]; exists {
		n.Value = value

		this.linkedList.Remove(n)
		this.linkedList.Insert(n)

		return
	}

	n := &Node{Key: key, Value: value}
	this.nodeMap[key] = n
    this.linkedList.Insert(n)

	if len(this.nodeMap) > this.capacity {
		removeNode := this.linkedList.Head

		this.linkedList.Remove(removeNode)
		delete(this.nodeMap, removeNode.Key)
	}
}

type LinkedList struct {
	Head *Node
	Tail *Node
}

func (l *LinkedList) Insert(n *Node) {
	if n == nil || n.prev != nil || n.next != nil || l.Head == n || l.Tail == n {
		panic("should new node")
	}

	if l.IsEmpty() {
		l.Head = n
		l.Tail = n
	} else {
		pp := l.Tail
		pp.next = n
		n.prev = pp
		l.Tail = n
	}
}

func (l *LinkedList) Remove(n *Node) {
	if l.IsEmpty() {
        panic("empty remove")
	}

	if n == nil {
        panic("nil remove")
	}

	np := n.prev
	nn := n.next

	switch {
	case np == nil && nn == nil:
		if n != l.Head || n != l.Tail {
			panic("wrong node") // wrong node input
		}
		l.Head = nil
		l.Tail = nil
	case np == nil:
		if n != l.Head {
			panic("wrong node") // wrong node input
		}
		nn.prev = nil
		n.next = nil
		l.Head = nn
	case nn == nil:
		if n != l.Tail {
			panic("wrong node") // wrong node input
		}
		np.next = nil
		n.prev = nil
		l.Tail = np
	default: // since time complexity, do not check node validity
		np.next = nn
		nn.prev = np
		n.prev = nil
		n.next = nil
	}
}

func (l *LinkedList) IsEmpty() bool {
	return l.Head == nil && l.Tail == nil
}

type Node struct {
	prev  *Node
	next  *Node
	Key   int
	Value int
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */