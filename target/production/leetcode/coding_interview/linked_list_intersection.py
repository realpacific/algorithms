from shared import Node, LinkedList

def _traverse_till_end(l):
    current = l.node
    while current is not None:
        current = current.next
    return current
    

def has_intersection(l1, l2):
    l1_end = _traverse_till_end(l1)
    l2_end = _traverse_till_end(l2)
    if l1_end is l2_end:
        return True
    else:
        return False


def _ltrim_by(l1, trim_amount):    
    assert isinstance(l1, LinkedList)
    assert trim_amount >= 0
    current = l1.node 
    for i in range(0, trim_amount):
        current  = current.next
        
    return current

def find_intersection(l1, l2):
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
    


# ------------------------------------
intersection_node = Node(4)
intersection_node.next = Node(5)
intersection_node.next.next = Node(6)

l1 = LinkedList()
l1.add(Node(0))
l1.add(Node(1))
l1.add(Node(2))
l1.add(Node(3))
l1.add(intersection_node)


l2 = LinkedList()
l2.add(Node(11))
l2.add(Node(12))
l2.add(intersection_node)

assert find_intersection(l1 , l2) is intersection_node


l3 = LinkedList()
l4 = LinkedList()
assert find_intersection(l3, l4) is None


l3.add(Node(5))
l3.add(Node(6))
l3.add(Node(7))
l4.add(Node(7))
l4.add(Node(7))
l4.add(Node(7))
assert find_intersection(l3, l4) is None
