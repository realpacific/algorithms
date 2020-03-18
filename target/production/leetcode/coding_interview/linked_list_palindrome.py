from shared import Node, LinkedList, RLinkedList

def is_linked_list_palindrome(l1):
    if l1 is None:
        return True
    current = l1.node
    r_linked_list = RLinkedList()
    while current is not None:
        r_linked_list.add(Node(current.data))
        current = current.next
    
    for i,j in zip(r_linked_list, l1):
        if i.data != j.data:
            return False
    return True


l1 = LinkedList()
l1.add(Node(1))
l1.add(Node(0))
l1.add(Node(5))
l1.add(Node(0))
l1.add(Node(1))
assert is_linked_list_palindrome(l1)



l2 = LinkedList()
l2.add(Node(1))
assert is_linked_list_palindrome(l2)


l3 = LinkedList()
l3.add(Node(1))
l3.add(Node(2))
l3.add(Node(3))
assert is_linked_list_palindrome(l3) == False