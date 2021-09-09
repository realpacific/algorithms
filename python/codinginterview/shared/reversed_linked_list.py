from .base_linked_list import BaseLinkedList
from .node import Node


class RLinkedList(BaseLinkedList):
    def __init__(self):
        super().__init__()

    def add(self, data):
        if not isinstance(data, Node):
            raise TypeError()

        if self.node is None:
            self.node = data
        else:
            data.next = self.node
            self.node = data
        self.length += 1
