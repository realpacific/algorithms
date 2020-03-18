import os
import sys
from shared.linked_list import Node, LinkedList


def delete_middle(linked, k):
    count = 0
    current = linked.node
    while current.next is not None:
        if (k - 1) == count:
            prev = current
            to_be_deleted = current.next
            joined = current.next.next
            prev.next = joined

        current = current.next
        count += 1

linked = LinkedList()
linked.add(Node(0))
linked.add(Node(1))
linked.add(Node(2))
linked.add(Node(3))
linked.add(Node(4))
linked.add(Node(5))
linked.add(Node(6))
linked.add(Node(7))
linked.add(Node(8))
linked.add(Node(9))
delete_middle(linked, 4)
linked.print()
