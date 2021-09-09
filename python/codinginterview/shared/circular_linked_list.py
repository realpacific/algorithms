from .node import Node
from .base_linked_list import BaseLinkedList


class CircularLinkedList(BaseLinkedList):
    def __init__(self):
        super(CircularLinkedList, self).__init__()
        self.is_circular_yet = False
        self.first_element = None

    def add(self, data, join=False):
        if self.is_circular_yet:
            raise Exception("Cannot add any more")
        if not isinstance(data, Node):
            raise TypeError()

        if self.node is None:
            self.node = data
            self.first_element = data
        else:
            current = self.node
            while current.next is not None:
                current = current.next
            current.next = data
        if join:
            self.is_circular_yet = True
            current.next.next = self.first_element
        self.length += 1
