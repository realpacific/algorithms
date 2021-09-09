from typing import Tuple

from shared.linked_list import LinkedList
from shared.base_linked_list import BaseLinkedList
from shared.node import Node
from shared.reversed_linked_list import RLinkedList


def pad_zero(l_, times):
    result = RLinkedList()
    result = l_
    for i in range(0, times):
        result.add(Node(0))
    return result


def add(l1_: Node, l2_: Node) -> Tuple[int, Node]:
    # Use recursion to calculate the sum of one's digit first then pass carry & Node value to higher digits
    if l1_.next is None and l2_.next is None:
        sum_of_data = (l1_.data + l2_.data)
        return 1 if sum_of_data > 9 else 0, Node(sum_of_data % 10)

    # Recurse here
    carry, node = add(l1_.next, l2_.next)
    value = carry
    if l1_ is not None:
        value += l1_.data
    if l2_ is not None:
        value += l2_.data
    new_node = Node(value % 10)
    new_node.next = node

    return 1 if value > 9 else 0, new_node


def forward_sum_of_nodes_of_linked_list(l1_: BaseLinkedList, l2_: BaseLinkedList):
    """
     (6 -> 1 -> 7) + (2 -> 9). That is 617 + 029 = 6 -> 4 -> 6
     """
    new_l1 = None
    new_l2 = None
    if len(l1_) < len(l2_):
        new_l1 = pad_zero(l1_, len(l2_) - len(l1_))
        new_l2 = l2_
    elif len(l2_) < len(l1_):
        new_l2 = pad_zero(l2_, len(l1_) - len(l2_))
        new_l1 = l1_
    print('l1', new_l1)
    print('l2', new_l2)
    return add(l1_.node, l2_.node)


def __collect_node_value(node: Node) -> int:
    linked_list = LinkedList.create(node)
    value = ""
    for i in linked_list:
        value += str(i.data)
    return int(value)


if __name__ == "__main__":
    l1 = RLinkedList()
    l1.add(Node(7))
    l1.add(Node(1))
    l1.add(Node(6))

    l2 = RLinkedList()
    l2.add(Node(9))
    l2.add(Node(2))
    print('__________')

    assert __collect_node_value(forward_sum_of_nodes_of_linked_list(l1, l2)[1]) == 646
