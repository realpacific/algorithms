from stack import Stack

class QueueViaStack:
    def __init__(self):
        self._backup_stack = Stack()
        self.stack = Stack()
    
    def first_element(self):
        if self.stack.isempty():
            return None
        return self.stack.data[0]

    def __len__(self):
        return len(self.stack)

    def enqueue(self, value):
        self.stack.push(value)

    def dequeue(self):
        if self.stack.isempty():
            return None
        # Backup every element except the last element in the stack
        while len(self.stack) != 1:
            self._backup_stack.push(self.stack.pop())
        # Pop the last element out to return it
        popped_value = self.stack.pop()
        # Re-fill the popped elements back
        while not self._backup_stack.isempty():
            self.stack.push(self._backup_stack.pop())
        return popped_value

    
    def __str__(self):
        return f"QueueViaStack({self.stack})"

    def __repr__(self):
        return str(self)

queue = QueueViaStack()
assert queue.dequeue() is None
queue.enqueue(1)
queue.enqueue(2)
queue.enqueue(3)
assert queue.stack.data[-1] == 3
assert queue.stack.data[0] == 1
queue.enqueue(4)
assert queue.stack.data[-1] == 4
queue.enqueue(5)
assert queue.stack.data[-1] == 5
queue.dequeue()
assert queue.stack.data[0] == 2
queue.dequeue()
queue.dequeue()
assert queue.stack.data[0] == 4