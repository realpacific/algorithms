import os
import sys
from shared.linked_list import Node


class RLinkedList:
    def __init__(self):
        self.node = None
        self.length = 0

    def add(self, data):
        if not isinstance(data, Node):
            raise TypeError()

        if self.node is None:
            self.node = data
        else:
            data.next = self.node
            self.node = data
        self.length += 1

    def __iter__(self):
        for each in self.__dict__.keys():
            yield self.__getattribute__(each)

    def __str__(self):
        return f"{self.node}"

    def __len__(self):
        return self.length

    def print(self):
        current = self.node
        while current is not None:
            print(current.data)
            current = current.next


def pad_zero(l, times):
    result = RLinkedList()
    result = l
    for i in range(0, times):
        result.add(Node(0))
    return result


def add(l1, l2):
    ''' (6 -> 1 -> 7) + (2 -> 9). That is 617 + 029 = 6 -> 4 -> 6 '''
    # Use recursion to calculate the sum of one's digit first then pass carry & Node value to higher digits
    if l1.next is None and l2.next is None:
        sum = (l1.data + l2.data)
        return (1 if sum > 9 else 0, Node(sum % 10))
    
     # Recurse here
    carry, node = add(l1.next, l2.next)
    value = carry
    if l1 is not None:
        value += l1.data
    if l2 is not None:
        value += l2.data
    new_node = Node(value % 10)
    new_node.next = node

    return (1 if value > 9 else 0, new_node)


def sum_list_forward(l1, l2):
    if len(l1) < len(l2):
        new_l1 = pad_zero(l1, len(l2) - len(l1))
        new_l2 = l2
    elif len(l2) < len(l1):
        new_l2 = pad_zero(l2, len(l1) - len(l2))
        new_l1 = l1
    print('l1', new_l1)
    print('l2', new_l2)
    return add(l1.node, l2.node)


l1 = RLinkedList()
l1.add(Node(7))
l1.add(Node(1))
l1.add(Node(6))

l2 = RLinkedList()
l2.add(Node(9))
l2.add(Node(2))
print('__________')

print(sum_list_forward(l1, l2))
