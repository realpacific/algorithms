class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

    def __str__(self):
        l = ""
        head = self
        while head is not None:
            l += str(head.val)
            head = head.next

        return l


class Solution:
    def addTwoNumbers(self, l1, l2):
        p = l1
        q = l2
        dummy = ListNode(0)
        head = dummy
        carry = 0
        while p is not None or q is not None:
            sum = (0 if p is None else p.val) + (0 if q is None else q.val) + carry
            carry = int(sum / 10)
            head.next = ListNode(sum % 10)
            head = head.next
            print(head.val, carry)
            if p is not None:
                p = p.next
            if q is not None:
                q = q.next

        if carry > 0:
            head.next = ListNode(carry)
        return dummy.next


def traverse(l1):
    res = ""
    head = l1

    condition = True
    while condition:
        res += str(head.val)
        head = head.next
        condition = head is not None
    return res


l1 = ListNode(1)
l2 = ListNode(2)
l3 = ListNode(3)

l1.next = l2
l2.next = l3

l3 = ListNode(7)
l4 = ListNode(7)
# l5 = ListNode(8)

l3.next = l4
# l4.next = l5

print(l1)
print(l3)
s = Solution()
print('sum', s.addTwoNumbers(l1, l3))
