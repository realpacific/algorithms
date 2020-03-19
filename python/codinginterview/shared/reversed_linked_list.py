from shared.node import Node
from shared.base_linked_list import BaseLinkedList

class RLinkedList(BaseLinkedList):
    def __init__(self):
        self.node = None
        self.length = 0

    def add(self, data):
        if not isinstance(data, Node):
            raise TypeError()

        if self.node is None:
            self.node = data
        else:
            data.next = self.node
            self.node = data
        self.length += 1