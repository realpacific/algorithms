from shared.linked_list import LinkedList
from shared.node import Node


def sum_list(l1, l2, carry):
    """ 7 -> 1 -> 6 + 5 -> 9 -> 2 = 617+295 = 2 -> 1 -> 9 """
    if l1 is None and l2 is None and carry == 0:
        return

    value = carry
    if l1 is not None:
        value += l1.data
    if l2 is not None:
        value += l2.data

    # Store the Digit at one
    node = Node(value % 10)

    if l1 is not None or l2 is not None:
        more = sum_list(l1.next if l1 is not None else None,
                        l2.next if l2 is not None else None,
                        1 if value >= 10 else 0)
        if more is not None:
            node.next = more if more is not None else None

    return node


if __name__ == "__main__":
    l1 = LinkedList()
    l1.add(Node(7))
    l1.add(Node(1))
    l1.add(Node(6))
    for i in l1:
        print(i)

    l2 = LinkedList()
    l2.add(Node(5))
    l2.add(Node(9))
    l2.add(Node(2))
    for i in l2:
        print(i)

    print(sum_list(l1.node, l2.node, 0))
