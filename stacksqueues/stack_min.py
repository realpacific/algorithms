class NodeWithMin:
    def __init__(self, value, min):
        self.data = value
        self.min = min

    def __str__(self):
        return f"NodeWithMin({self.data}, {self.min})"

    def __repr__(self):
        return str(self)

class MinStack:
    """Stack has a function min, which returns the minimum element. Push, pop and min should all operate in 0(1) time"""
    def __init__(self):
       self.stack = []

    def peek(self):
        if len(self) > 0:
            return self.stack[-1]
        else:
            return None

    def push(self, value):
        last_item = self.peek()
        # The current entry in the stack will hold the lowest element in the stack after it is pushed
        if last_item is None:
            new_entry = NodeWithMin(value, value)
        elif last_item.min >= value:
            new_entry = NodeWithMin(value, value)
        else:
            new_entry = NodeWithMin(value, last_item.min)
        self.stack.append(new_entry)

    def pop(self):
        if len(self) <= 0:
            raise Exception("Stack is empty.")
        poped_element = self.stack.pop()
        # When popped, peeking into the stack will return the next lowest element
    
    def min(self):
        element = self.peek()
        return element.min if element is not None else None

    def __len__(self):
        return len(self.stack)

    def __str__(self):
        return f"MinStack({self.stack})"

    def __repr__(self):
        return str(self)

st = MinStack() 
assert st.min() == None
st.push(5)
st.push(6)
assert st.min() == 5
st.push(7)
st.push(2)
assert st.min() == 2
st.push(10)
st.push(0)
assert st.min() == 0
st.push(100)
st.pop()
st.pop()
assert st.min() == 2
st.pop()
st.pop()
st.pop()
st.push(-1)
st.push(25)
assert st.min() == -1
st.pop()
st.pop()
assert st.min() == 5