from shared.node import Node

class BaseLinkedList:
    def __init__(self):
        self.node = None
        self.length = 0

    def __iter__(self):
        node = self.__getattribute__('node')
        while node is not None:
            yield node
            node = node.next

    def __str__(self):
        return f"{self.node}"

    def __len__(self):
        return self.length

    def print(self):
        current = self.node
        while current is not None:
            print(current.data)
            current = current.next
