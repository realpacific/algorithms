from codinginterview.shared.base_linked_list import BaseLinkedList
from codinginterview.shared.node import Node


class LinkedList(BaseLinkedList):
    def __init__(self):
        super(LinkedList, self).__init__()

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
