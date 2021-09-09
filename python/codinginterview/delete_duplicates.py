from shared.linked_list import LinkedList
from shared.node import Node


def delete_duplicates(linked_list: LinkedList):
    """
    Delete duplicate node from a Linked List

    Parameters:
        linked_list: The list to be deleted from
    """
    current = linked_list.node
    if current is None:
        return
    record = set()
    record.add(current.data)
    while current.next is not None:
        # Duplicate if data is present in [record]
        if current.next.data in record:
            current.next = current.next.next
        else:
            record.add(current.next.data)
            current = current.next


if __name__ == "__main__":
    linked = LinkedList()
    linked.add(Node(1))
    linked.add(Node(2))
    linked.add(Node(2))
    linked.add(Node(2))
    linked.add(Node(3))
    linked.add(Node(4))
    linked.add(Node(5))
    linked.add(Node(1))
    linked.add(Node(6))
    linked.add(Node(7))
    delete_duplicates(linked)
    linked.print()
    assert len(linked) == 7
    # Linked list must contain 1 to 7 in order
    for [node, num] in zip(linked, range(1, 8)):
        assert node.data == num
