class MyHashMap:

    def __init__(self):
        self.container = [None] * 2**8

    def put(self, key: int, value: int) -> None:
        idx = self.__hashFun(key)
        if self.container[idx] is None:
            self.container[idx] = Node((key, value))

        ll = self.container[idx]
        while ll is not None and ll.val[0] != key:
            ll = ll.aft

        if ll is None:
            self.container[idx].bef = Node((key, value))
            self.container[idx].bef.aft = self.container[idx]
            self.container[idx] = self.container[idx].bef
        else:
            ll.val = (key, value)

    def get(self, key: int) -> int:
        idx = self.__hashFun(key)
        ll = self.container[idx]

        while ll is not None and ll.val[0] != key:
            ll = ll.aft

        if ll is None:
            return -1
        else:
            return ll.val[1]

    def remove(self, key: int) -> None:
        idx = self.__hashFun(key)
        if self.container[idx] is None:
            return

        ll = self.container[idx]
        while ll is not None and ll.val[0] != key:
            ll = ll.aft

        if ll is None:
            return

        if ll.aft is not None:
            ll.aft.bef = ll.bef
        if ll.bef is not None:
            ll.bef.aft = ll.aft
        
        if ll.bef is None:
            self.container[idx] = ll.aft

        del ll


    def __hashFun(self, key: int) -> int:
        return key % 2**8
        
class Node:
    def __init__(self, v: Tuple[int, int]):
        self.val = v
        self.bef = None
        self.aft = None


# Your MyHashMap object will be instantiated and called as such:
# obj = MyHashMap()
# obj.put(key,value)
# param_2 = obj.get(key)
# obj.remove(key)