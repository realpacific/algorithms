from shared.circular_linked_list import CircularLinkedList
from shared.node import Node


def is_linked_list_circular(linked_list_):
    """
    Detect if a Linked List is circular
    """

    current = linked_list_.node
    registry = set()
    while current is not None:
        # The linked list is circular if one of its element points to previous element
        if current in registry:
            return True
        registry.add(current)
        current = current.next
    return False


if __name__ == "__main__":
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
