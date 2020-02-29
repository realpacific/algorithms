class StackWithThreshold:
    def __init__(self):
        self.stack = []
        self.threshold = 6

    def push(self, data):
        self.stack.append(data)

    def pop(self):
        if len(self.stack) > 0:
            return self.stack.pop()
        return None

    def __len__(self):
        return len(self.stack)

    def __str__(self):
        return f"StackWithThreshold({self.stack})"

    def __repr__(self):
        return str(self)

class StackOfPlates:
    def __init__(self):
        self.stack_with_plates = []
    
    def push(self, data):
        latest_stack = None
        if len(self.stack_with_plates) > 0:
            latest_stack = self.peek()
        if latest_stack is None or len(latest_stack) >= latest_stack.threshold:
            self.stack_with_plates.append(StackWithThreshold())
        self.peek().push(data)

    def pop(self):
        if len(self) == 0:
            return None
        latest_stack = self.peek()
        latest_stack.pop()
        if len(self.peek()) == 0:
            self.stack_with_plates.pop()

    def peek(self):
        return self.stack_with_plates[-1]

    def __len__(self):
        return len(self.stack_with_plates)

    def __str__(self):
        return f"StackOfPlates({self.stack_with_plates})"

    def __repr__(self):
        return str(self)


sop = StackOfPlates()
for i in range(1, 20):
    sop.push(i)
print(sop)
print('popping-----')
for i in range(0, 10):
    sop.pop()
sop.pop()
sop.pop()
sop.pop()
sop.pop()
print(sop)