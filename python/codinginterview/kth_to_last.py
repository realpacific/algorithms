from shared.linked_list import Node, LinkedList


def kth_to_last(linked_list_: LinkedList, k):
    """
    Get all nodes from kth position to the end
    """
    result = None
    current = linked_list_.node
    count = 0
    while current.next is not None:
        if count == k:
            return current
        current = current.next
        count += 1


if __name__ == "__main__":
    linked = LinkedList()
    first_node = Node(1)
    linked.add(first_node)
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
    assert kth_to_last(linked, 22) is None
    assert kth_to_last(linked, 5).data == 0
    for [node, num] in zip(LinkedList.create(kth_to_last(linked, 9)), [1, 6, 7]):
        assert node.data == num
    for [node, expected_node] in zip(LinkedList.create(kth_to_last(linked, 0)), LinkedList.create(first_node)):
        assert node.data == expected_node.data
