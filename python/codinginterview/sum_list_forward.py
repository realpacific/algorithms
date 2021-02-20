from .shared.node import Node
from .shared.reversed_linked_list import RLinkedList


def pad_zero(l, times):
    result = RLinkedList()
    result = l
    for i in range(0, times):
        result.add(Node(0))
    return result


def add(l1, l2):
    """ (6 -> 1 -> 7) + (2 -> 9). That is 617 + 029 = 6 -> 4 -> 6 """
    # Use recursion to calculate the sum of one's digit first then pass carry & Node value to higher digits
    if l1.next is None and l2.next is None:
        sum_of_data = (l1.data + l2.data)
        return 1 if sum_of_data > 9 else 0, Node(sum_of_data % 10)

    # Recurse here
    carry, node = add(l1.next, l2.next)
    value = carry
    if l1 is not None:
        value += l1.data
    if l2 is not None:
        value += l2.data
    new_node = Node(value % 10)
    new_node.next = node

    return 1 if value > 9 else 0, new_node


def sum_list_forward(l1, l2):
    new_l1 = None
    new_l2 = None
    if len(l1) < len(l2):
        new_l1 = pad_zero(l1, len(l2) - len(l1))
        new_l2 = l2
    elif len(l2) < len(l1):
        new_l2 = pad_zero(l2, len(l1) - len(l2))
        new_l1 = l1
    print('l1', new_l1)
    print('l2', new_l2)
    return add(l1.node, l2.node)


if __name__ == "__main__":
    l1 = RLinkedList()
    l1.add(Node(7))
    l1.add(Node(1))
    l1.add(Node(6))

    l2 = RLinkedList()
    l2.add(Node(9))
    l2.add(Node(2))
    print('__________')

    print(sum_list_forward(l1, l2))
