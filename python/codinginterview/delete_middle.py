from shared.linked_list import Node, LinkedList


def delete_middle(linked_: LinkedList, k: int):
    """
    Delete kth node from linked list

    Parameters:
        linked_ (LinkedList): a Linked List
        k (int): the kth position

    """
    if k > len(linked_):
        return
    count = 0
    current = linked_.node
    while current.next is not None:
        if (k - 1) == count:
            prev = current
            prev.next = current.next.next
        current = current.next
        count += 1


if __name__ == "__main__":
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

    original_length = len(linked)
    delete_middle(linked, 4)

    assert original_length - 1 == len(linked)

    expected_data_ = [i for i in range(0, 10) if i != 4]
    for [node, num] in zip(linked, expected_data_):
        assert node.data == num
