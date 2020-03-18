from shared.node import Node
from shared.base_linked_list import BaseLinkedList

class LinkedList(BaseLinkedList):
    def __init__(self):
        self.node = None
        self.length = 0

    def add(self, data):
        if not isinstance(data, Node):
            raise TypeError()

        if self.node is None:
            self.node = data
        else:
            current = self.node
            while current.next is not None:
                current = current.next
            current.next = data
        self.length += 1
