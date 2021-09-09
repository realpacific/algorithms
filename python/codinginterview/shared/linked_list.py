from .base_linked_list import BaseLinkedList
from .node import Node


class LinkedList(BaseLinkedList):
    def __init__(self):
        super(LinkedList, self).__init__()

    def add(self, data: Node):
        if not isinstance(data, Node):
            raise TypeError()

        if self.node is None:
            self.node: Node = data
        else:
            current = self.node
            while current.next is not None:
                current = current.next
            current.next = data
        self.length += 1

    @classmethod
    def create(cls, node: Node) -> 'LinkedList':
        linked_list = LinkedList()
        if node is None:
            return linked_list
        current = node
        while current is not None:
            linked_list.add(Node(current.data))
            current = current.next
        return linked_list
