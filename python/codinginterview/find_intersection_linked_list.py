from shared.node import Node
from shared.linked_list import LinkedList


def _traverse_till_end(list_: LinkedList):
    current = list_.node
    while current is not None:
        current = current.next
    return current


def has_intersection(list_: LinkedList, l2):
    l1_end = _traverse_till_end(list_)
    l2_end = _traverse_till_end(l2)
    if l1_end is l2_end:
        return True
    else:
        return False


def _ltrim_by(list_: LinkedList, trim_amount):
    assert isinstance(list_, LinkedList)
    assert trim_amount >= 0
    current = list_.node
    for i in range(0, trim_amount):
        current = current.next

    return current


def find_intersection_linked_list(l1: LinkedList, l2: LinkedList):
    """
    Find intersection between two linked list
    """

    # Last element must be same in order for two list to intersect
    if not has_intersection(l1, l2):
        return None

    diff_l1 = l1.node
    diff_l2 = l2.node

    # Trim out the largest list to match the length of smaller one
    diff = len(l1) - len(l2)
    remaining = len(l2) if diff > 0 else len(l1)
    if diff > 0:
        diff_l1 = _ltrim_by(l1, abs(diff))
        diff_l2 = l2.node
    elif diff < 0:
        diff_l1 = l1.node
        diff_l2 = _ltrim_by(l2, abs(diff))

    node_1 = diff_l1
    node_2 = diff_l2
    for i in range(0, remaining):
        # Check for referential equality
        if node_1 is node_2:
            return node_1
        node_1 = node_1.next
        node_2 = node_2.next
    return None


if __name__ == "__main__":
    intersection_node = Node(4)
    intersection_node.next = Node(5)
    intersection_node.next.next = Node(6)

    list_1 = LinkedList()
    list_1.add(Node(0))
    list_1.add(Node(1))
    list_1.add(Node(2))
    list_1.add(Node(3))
    list_1.add(intersection_node)

    list_2 = LinkedList()
    list_2.add(Node(11))
    list_2.add(Node(12))
    list_2.add(intersection_node)

    assert find_intersection_linked_list(list_1, list_2) is intersection_node

    l3 = LinkedList()
    l4 = LinkedList()
    assert find_intersection_linked_list(l3, l4) is None

    l3.add(Node(5))
    l3.add(Node(6))
    l3.add(Node(7))
    l4.add(Node(7))
    l4.add(Node(7))
    l4.add(Node(7))
    assert find_intersection_linked_list(l3, l4) is None
