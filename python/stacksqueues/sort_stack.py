from .stack import Stack

class SortedStack:
    def __init__(self):
        self.stack = Stack()
        self._temp = Stack()

    def push(self, value):
        # Pop out all the smaller values and store it into temp stack 
        while not self.stack.isempty() and self.stack.peek() > value:
            top_value = self.stack.pop()
            self._temp.push(top_value)
        # Push the current value
        self.stack.push(value)
        # Move all the values from temp stack to current stack
        while not self._temp.isempty():
            self.stack.push(self._temp.pop())

sorted_stack = SortedStack()
sorted_stack.push(1)
assert sorted_stack.stack.peek() == 1
sorted_stack.push(2)
assert sorted_stack.stack.peek() == 2
sorted_stack.push(10)
assert sorted_stack.stack.peek() == 10
sorted_stack.push(5)
assert sorted_stack.stack.peek() != 5
assert sorted_stack.stack.data[-2] == 5
sorted_stack.push(0)
assert sorted_stack.stack.data[0] == 0
sorted_stack.push(3)
assert sorted_stack.stack.data[3] == 3
