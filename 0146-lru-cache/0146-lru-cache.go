
type LRUCache struct {
    capacity int
    valueMap map[int]int
    nodeMap map[int]*node
    list doubleLinkedList
}

func Constructor(capacity int) LRUCache {
    return LRUCache {
        capacity: capacity,
        valueMap: map[int]int{},
        nodeMap: map[int]*node{},
        list: doubleLinkedList{},
    }
}

func (this *LRUCache) Get(key int) int {
    if nptr, ok := this.nodeMap[key]; ok {
        value := this.valueMap[key]

        this.list.removeNode(nptr)
        this.list.pushHead(nptr)

        return value
    } else {
        return -1
    }
}

func (this *LRUCache) Put(key int, value int)  {
    // edit value
    this.valueMap[key] = value

    // edit node
    if nptr, ok := this.nodeMap[key]; ok {
        this.list.removeNode(nptr)
        this.list.pushHead(nptr)
    } else {
        nd := newNode(key)
        this.nodeMap[key] = &nd
        this.list.pushHead(&nd)
    }

    // remove overflowed node
    if len(this.valueMap) > this.capacity {
        nptr := this.list.popTail()
        delete(this.valueMap, nptr.key)
        delete(this.nodeMap, nptr.key)
    }
}


type doubleLinkedList struct {
    head *node
    tail *node
}
func (dl *doubleLinkedList) removeNode(nptr *node) {
    if dl.head == nil && dl.tail == nil {
        panic("Pop from empty list")
    }

    if nptr.before != nil {
        nptr.before.after = nptr.after
    }
    if nptr.after != nil {
        nptr.after.before = nptr.before
    }

    if nptr == dl.head {
        dl.head = nptr.after
    }
    if nptr == dl.tail {
        dl.tail = nptr.before
    }

    nptr.before = nil
    nptr.after = nil
}
func (dl *doubleLinkedList) popTail() *node {
    tailPtr := dl.tail
    dl.removeNode(tailPtr)
    return tailPtr
}
func (dl *doubleLinkedList) pushHead(nptr *node) {
    if dl.head == nil && dl.tail == nil {
        dl.head = nptr
        dl.tail = nptr
    } else {
        dl.head.before = nptr
        nptr.after = dl.head
        dl.head = nptr
    }
}


type node struct {
    key int
    before *node
    after *node
}
func newNode(k int) node {
    return node{ key: k }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */