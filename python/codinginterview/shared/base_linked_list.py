from .node import Node


class BaseLinkedList:

    def __init__(self):
        self.node = None
        # length will not consider explicit deletes
        self.length = 0

    def __iter__(self):
        current = self.node
        while current is not None:
            yield current
            current = current.next

    def __str__(self):
        return f"{self.node}"

    def __len__(self):
        length = 0
        runner: Node = self.node
        while runner is not None:
            length += 1
            runner = runner.next
        return length

    def print(self):
        current = self.node
        while current is not None:
            print(current.data)
            current = current.next
