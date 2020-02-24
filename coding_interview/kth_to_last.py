import os
import sys
from shared.linked_list import Node, LinkedList


def kth_to_last(linked, k):
    result = None
    current = linked.node
    count = 0
    while current.next is not None:
        if count == k:
            return current
        current = current.next
        count += 1


linked = LinkedList()
linked.add(Node(1))
linked.add(Node(2))
linked.add(Node(10))
linked.add(Node(12))
linked.add(Node(22))
linked.add(Node(0))
linked.add(Node(3))
linked.add(Node(4))
linked.add(Node(5))
linked.add(Node(1))
linked.add(Node(6))
linked.add(Node(7))
print('k=0', kth_to_last(linked, 0))
print('k=22', kth_to_last(linked, 22))
print('k=5', kth_to_last(linked, 5))
