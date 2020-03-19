from codinginterview.shared.circular_linked_list import CircularLinkedList
from codinginterview.shared.node import Node


def is_linked_list_circular(l1):
    current = l1.node
    registry = []
    while current is not None:
        # The linked list is circular if one of its element points to previous element
        if current in registry:
            return True
        registry.append(current)
        current= current.next
    return False



# ------------------------------------

l1 = CircularLinkedList()
l1.add(Node(1))
l1.add(Node(2))
l1.add(Node(3))
l1.add(Node(4), join=True)

assert is_linked_list_circular(l1)



l2 = CircularLinkedList()
l2.add(Node(1))
l2.add(Node(2))
l2.add(Node(3))
l2.add(Node(4))

assert not is_linked_list_circular(l2)
