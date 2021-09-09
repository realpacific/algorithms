from shared.linked_list import LinkedList
from shared.node import Node


def sum_of_nodes_of_linked_list(l1_: Node, l2_: Node, carry: int = 0):
    """
    Sum value of nodes
    7 -> 1 -> 6 + 5 -> 9 -> 2 = 617+295 = 2 -> 1 -> 9
    """
    if l1_ is None and l2_ is None and carry == 0:
        return

    value = carry
    if l1_ is not None:
        value += l1_.data
    if l2_ is not None:
        value += l2_.data

    # Store the Digit at one
    node = Node(value % 10)

    if l1_ is not None or l2_ is not None:
        more = sum_of_nodes_of_linked_list(l1_.next if l1_ is not None else None,
                                           l2_.next if l2_ is not None else None,
                                           1 if value >= 10 else 0)
        if more is not None:
            node.next = more if more is not None else None

    return node


def __collect_node_value(node: Node):
    linked_list = LinkedList.create(node)
    value = ""
    for i in linked_list:
        value += str(i.data)
    return int(value)


if __name__ == "__main__":
    l1 = LinkedList()
    l1.add(Node(7))
    l1.add(Node(1))
    l1.add(Node(6))

    l2 = LinkedList()
    l2.add(Node(5))
    l2.add(Node(9))
    l2.add(Node(2))

    assert __collect_node_value(sum_of_nodes_of_linked_list(l1.node, l2.node)) == 219
